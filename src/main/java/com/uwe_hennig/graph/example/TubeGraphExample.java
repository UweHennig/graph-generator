/**
 * @(#)TubeGraphExample.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.example;

import static com.uwe_hennig.graph.generator.contracts.SelectionRule.unusedRule;

import com.uwe_hennig.graph.generator.FanInGraphGenerator;
import com.uwe_hennig.graph.generator.FanOutGraphGenerator;
import com.uwe_hennig.graph.generator.LineGraphGenerator;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.pipeline.GraphPipeline;
import com.uwe_hennig.graph.util.GraphvizConsolePrinter;

/**
 * TubeGraphExample
 *
 * @author Uwe Hennig
 */
public class TubeGraphExample {
    public static void main(String[] args) {
        GraphContext context = new GraphContext();

        LineGraphGenerator lineGenerator = new LineGraphGenerator(3, context, unusedRule(context));
        FanOutGraphGenerator initGenerator = new FanOutGraphGenerator(context, unusedRule(context), 2);

        FanInGraphGenerator finalizerGenerator = new FanInGraphGenerator(context, unusedRule(context));

        Graph graph = GraphPipeline.start(initGenerator)
            .generate(lineGenerator)
            .finalizeStep(finalizerGenerator)
            .build();

        GraphvizConsolePrinter.printToConsole(context, graph);
    }
}
