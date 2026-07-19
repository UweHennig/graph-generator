/**
 * @(#)FanInGraphGenerator.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator;

import java.util.List;

import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.generator.contracts.GraphGenerator;
import com.uwe_hennig.graph.generator.contracts.SelectionRule;

/**
 * FanInGraphGenerator
 *
 * @author Uwe Hennig
 */
public class FanInGraphGenerator implements GraphGenerator {
    private final GraphContext context;
    private final SelectionRule selectionRule;

    public FanInGraphGenerator(GraphContext context, SelectionRule selectionRule) {
        this.context = context;
        this.selectionRule = selectionRule;
    }

    @Override
    public List<Graph> generate(Graph initialGraph) {
        if (initialGraph == null) {
            return List.of();
        }

        List<Edge> filteredEdges = selectionRule != null ? selectionRule.apply(initialGraph.edges()) : initialGraph.edges();
        if (filteredEdges.isEmpty()) {
            return List.of();
        }

        Graph graph = Graph.of(context);
        int endNodeId = context.nextNodeId();

        for (Edge edge : initialGraph.edges()) {
            if (!context.isUsedEdge(edge.edgeId())) {
                int node = edge.nodeToId();
                Edge newEdge = new Edge(context.nextEdgeId(), node, endNodeId);
                graph.addEdge(newEdge);
                context.setUsedEdge(edge.edgeId());
            }
        }

        return List.of(graph);
    }
}
