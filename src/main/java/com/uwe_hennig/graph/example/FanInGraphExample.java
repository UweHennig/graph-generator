/**
 * @(#)FanInGraphExample.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.example;

import static com.uwe_hennig.graph.generator.contracts.SelectionRule.unusedRule;

import com.uwe_hennig.graph.generator.EmptyGraphGenerator;
import com.uwe_hennig.graph.generator.FanInGraphGenerator;
import com.uwe_hennig.graph.generator.FanOutGraphGenerator;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.pipeline.GraphPipeline;
import com.uwe_hennig.graph.util.GraphvizConsolePrinter;

/**
 * FanInGraphExample
 *
 * @author Uwe Hennig
 */
public class FanInGraphExample {
    public static void main(String[] args) {
        GraphContext context = new GraphContext();

        EmptyGraphGenerator empty = new EmptyGraphGenerator();

        FanOutGraphGenerator fanOut = new FanOutGraphGenerator(context, unusedRule(context), 5);
        FanInGraphGenerator fanIn = new FanInGraphGenerator(context, unusedRule(context));

        GraphPipeline pipeline = GraphPipeline.start(fanOut);

        pipeline.generate(1, fanIn);
        pipeline.finalize(empty);
        Graph graph = pipeline.build();

        GraphvizConsolePrinter.printToConsole(context, graph);
    }
}
