/**
 * @(#)LineGraphGenerator.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator;

import java.util.ArrayList;
import java.util.List;

import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.generator.contracts.GraphGenerator;
import com.uwe_hennig.graph.generator.contracts.SelectionRule;

/**
 * LineGraphGenerator
 *
 * @author Uwe Hennig
 */
public class LineGraphGenerator implements GraphGenerator {
    private final GraphContext  context;
    private final SelectionRule selectionRule;

    public LineGraphGenerator(GraphContext context, SelectionRule selectionRule) {
        this.context = context;
        this.selectionRule = selectionRule;
    }

    @Override
    public List<Graph> generate(int iterations, Graph initialGraph) {
        List<Graph> result = new ArrayList<>();

        if (initialGraph == null || initialGraph.edges().isEmpty()) {
            initialGraph = Graph.of(context);
            Edge edge = new Edge(context.nextEdgeId(), context.nextNodeId(), context.nextNodeId());
            initialGraph.addEdge(edge);
            if (iterations == 1) {
                result.add(initialGraph);
                return result;
            }
            iterations--;
        }

        List<Edge> filteredEdges = selectionRule != null ? selectionRule.apply(initialGraph.edges()) : initialGraph.edges();
        for (Edge edge : filteredEdges) {
            Graph resultGraph = Graph.of(context);
            Edge currentEdge = edge;

            int fromNodeId = edge.nodeToId();
            for (int i = 0; i < iterations; i++) {
                context.setUsedEdge(currentEdge.edgeId());
                int toNodeId = context.nextNodeId();
                currentEdge = new Edge(context.nextEdgeId(), fromNodeId, toNodeId);
                resultGraph.addEdge(currentEdge);
                fromNodeId = toNodeId;
            }
            result.add(resultGraph);
        }

        return result;
    }

}
