/**
 * @(#)Graph.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator.contracts;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph
 *
 * @author Uwe Hennig
 */
public record Graph(GraphContext context, List<Edge> edges) {
    public static Graph of(GraphContext context) {
        return new Graph(context, new ArrayList<>());
    }

    public Graph addEdge(Edge edge) {
        edges.add(edge);
        return this;
    }

    public Graph addAllEdges(List<Edge> inEdges) {
        edges.addAll(inEdges);
        return this;
    }

    @Override
    public final String toString() {
        if (edges != null && !edges.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (int i=0;i<edges.size();i++) {
                builder.append(edges.get(i).toString()).append("\n");
            }
            return builder.toString();
        }
        return "graph empty";
    }
}
