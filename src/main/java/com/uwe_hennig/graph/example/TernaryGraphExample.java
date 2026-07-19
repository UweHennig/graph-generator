/**
 * @(#)TernaryGraphExample.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.example;

import com.uwe_hennig.graph.generator.EmptyGraphGenerator;
import com.uwe_hennig.graph.generator.FanOutGraphGenerator;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.pipeline.GraphPipeline;
import com.uwe_hennig.graph.rule.AllRule;
import com.uwe_hennig.graph.util.GraphvizConsolePrinter;

/**
 * TernaryGraphExample
 *
 * @author Uwe Hennig
 */
public class TernaryGraphExample {
    public static void main(String[] args) {
        GraphContext context = new GraphContext();
        AllRule all = new AllRule();
        FanOutGraphGenerator fanOut = new FanOutGraphGenerator(context, all, 3);

        GraphPipeline pipeline = GraphPipeline.start(fanOut);

        pipeline.generate(0, fanOut);
        pipeline.finalize(new EmptyGraphGenerator());
        Graph graph = pipeline.build();

        GraphvizConsolePrinter.printToConsole(context, graph);
    }
}
