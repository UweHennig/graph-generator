/**
 * @(#)GraphviConsolePrinter.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.util;

import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.GraphContext;

/**
 * GraphviConsolePrinter
 *
 * @author Uwe Hennig
 */
public class GraphviConsolePrinter {

    public static void printToConsole(GraphContext context, Graph graph) {
        StringBuilder builder = new StringBuilder();
        builder.append("digraph SNN {\n")
            .append("node [shape=none];\n")
            .append("layout = neato;\n")
            .append("edge [arrowhead=empty color=red];\n");

        for (Edge edge : graph.edges()) {
            if (context.isUsedEdge(edge.edgeId())) {
                builder.append(edge).append("\n");
            } else {
                builder.append(edge).append(" [color=green]")
                .append("\n");
            }
        }

        builder.append("}\n");
        System.out.println(builder.toString());
    }
}
