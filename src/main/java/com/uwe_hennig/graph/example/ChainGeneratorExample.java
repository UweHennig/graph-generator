/**
 * @(#)ChainGeneratorExample.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.example;

import static com.uwe_hennig.graph.generator.contracts.SelectionRule.unusedRule;

import com.uwe_hennig.graph.generator.FanInGraphGenerator;
import com.uwe_hennig.graph.generator.FanOutGraphGenerator;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.pipeline.GraphPipeline;
import com.uwe_hennig.graph.util.GraphvizConsolePrinter;

/**
 * ChainGeneratorExample
 *
 * @author Uwe Hennig
 */
public class ChainGeneratorExample {

    public static void main(String[] args) {
        GraphContext context = new GraphContext();

        FanOutGraphGenerator fanOut = new FanOutGraphGenerator(context, unusedRule(context), 2);
        FanInGraphGenerator fanIn = new FanInGraphGenerator(context, unusedRule(context));

        Graph graph = GraphPipeline.start(fanOut)
            .generate(fanIn)
            .generate(fanOut)
            .generate(fanIn)
            .generate(fanOut)
            .finalizeStep(fanIn)
            .build();

        GraphvizConsolePrinter.printToConsole(context, graph);
    }

}
