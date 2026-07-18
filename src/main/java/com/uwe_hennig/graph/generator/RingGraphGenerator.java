/**
 * @(#)RingGraphGenerator.java
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
 * RingGraphGenerator
 *
 * @author Uwe Hennig
 */
public class RingGraphGenerator implements GraphGenerator {
    private final GraphContext  context;
    private final SelectionRule selectionRule;
    private final boolean clockwise;

    public RingGraphGenerator(GraphContext context, boolean clockwise, SelectionRule selectionRule) {
        this.context = context;
        this.selectionRule = selectionRule;
        this.clockwise = clockwise;
    }

    @Override
    public List<Graph> generate(int interations, Graph initialGraph) {
        List<Graph> result = new ArrayList<>();

        List<Edge> filteredEdges = selectionRule != null ? selectionRule.apply(initialGraph.edges()) : initialGraph.edges();
        List<Edge> resultingEdges = new ArrayList<>();

        for (Edge edge : filteredEdges) {
            if (context.isUsedEdge(edge.edgeId())) {
                continue;
            }

            Graph resultingGraph = Graph.of(context);

            Edge currentEdge = edge;
            context.setUsedEdge(currentEdge.edgeId());

            int startNodeId = clockwise ? currentEdge.nodeToId() : currentEdge.nodeFromId();
            int endNodeId = clockwise ? currentEdge.nodeFromId() : currentEdge.nodeToId();

            int currentNodeId = startNodeId;

            for (int i = 0; i < interations - 1; i++) {
                resultingEdges.add(createEdge(context, currentNodeId, currentNodeId = context.nextNodeId()));
            }
            resultingEdges.add(createEdge(context, currentNodeId, endNodeId));
            resultingGraph.addAllEdges(resultingEdges);

            result.add(resultingGraph);
        }


        return result;
    }

    private Edge createEdge(GraphContext context, int startNode, int endNode) {
        Edge newEdge = new Edge(context.nextEdgeId(), startNode, endNode);
        return newEdge;
    }

}
