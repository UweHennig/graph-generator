/**
 * @(#)FanOutGraphGenerator.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.generator.contracts.GraphGenerator;
import com.uwe_hennig.graph.generator.contracts.SelectionRule;

/**
 * FanOutGraphGenerator
 *
 * @author Uwe Hennig
 */
public class FanOutGraphGenerator  implements GraphGenerator {
    private final GraphContext context;
    private final int outNodeSize;
    private final SelectionRule selectionRule;

    public FanOutGraphGenerator(GraphContext context, SelectionRule selectionRule, int outNodeSize) {
        this.context = context;
        this.outNodeSize = outNodeSize;
        this.selectionRule = selectionRule;
    }

    @Override
    public List<Graph> generate(Graph initialGraph) {
        List<Graph> result = new ArrayList<>();

        if (initialGraph == null || initialGraph.edges().isEmpty()) {
            int startNode = context.nextNodeId();
            Graph graph = Graph.of(context);
            for (int i = 0; i < outNodeSize; i++) {
                Edge newEdge = new Edge(context.nextEdgeId(), startNode, context.nextNodeId());
                graph.addEdge(newEdge);
            }
            result.add(graph);
            return result;
        }

        List<Edge> filteredEdges = selectionRule != null ? selectionRule.apply(initialGraph.edges()) : initialGraph.edges();
        Set<Integer> usedNodes = new HashSet<>();

        for (Edge edge : filteredEdges) {
            context.setUsedEdge(edge.edgeId());

            int startNode = edge.nodeToId();
            if (usedNodes.contains(startNode)) {
                continue;
            }

            usedNodes.add(startNode);

            Graph graph = Graph.of(context);
            for (int i = 0; i < outNodeSize; i++) {
                Edge newEdge = new Edge(context.nextEdgeId(), startNode, context.nextNodeId());
                graph.addEdge(newEdge);
            }
            result.add(graph);
        }

        return result;
    }

}
