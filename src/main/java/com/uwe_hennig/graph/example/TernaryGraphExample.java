/**
 * @(#)TernaryGraphExample.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.example;

import static com.uwe_hennig.graph.generator.contracts.SelectionRule.ALL_RULE;

import com.uwe_hennig.graph.generator.EmptyGraphGenerator;
import com.uwe_hennig.graph.generator.FanOutGraphGenerator;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.pipeline.GraphPipeline;
import com.uwe_hennig.graph.util.GraphvizConsolePrinter;

/**
 * TernaryGraphExample
 *
 * @author Uwe Hennig
 */
public class TernaryGraphExample {
    public static void main(String[] args) {
        GraphContext context = new GraphContext();
        FanOutGraphGenerator fanOut = new FanOutGraphGenerator(context, ALL_RULE, 3);

        Graph graph = GraphPipeline.start(fanOut)
            .generate(fanOut)
            .finalizeStep(new EmptyGraphGenerator())
            .build();

        GraphvizConsolePrinter.printToConsole(context, graph);
    }
}
