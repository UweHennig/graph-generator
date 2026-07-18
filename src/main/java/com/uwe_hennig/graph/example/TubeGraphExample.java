/**
 * @(#)TubeGraphExample.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.example;

import com.uwe_hennig.graph.generator.FanInGraphGenerator;
import com.uwe_hennig.graph.generator.FanOutGraphGenerator;
import com.uwe_hennig.graph.generator.LineGraphGenerator;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.pipeline.GraphPipeline;
import com.uwe_hennig.graph.rule.UnusedRule;
import com.uwe_hennig.graph.util.GraphviConsolePrinter;

/**
 * TubeGraphExample
 *
 * @author Uwe Hennig
 */
public class TubeGraphExample {
    public static void main(String[] args) {
        GraphContext context = new GraphContext();
        UnusedRule unusedRule = new UnusedRule(context);

        LineGraphGenerator lineGenerator = new LineGraphGenerator(context, unusedRule);
        FanOutGraphGenerator initGenerator = new FanOutGraphGenerator(context, unusedRule, 2);

        FanInGraphGenerator finalizerGenerator = new FanInGraphGenerator(context, unusedRule);

        GraphPipeline pipeline = GraphPipeline.start(initGenerator);
        pipeline.generate(5, lineGenerator);
        pipeline.finalize(finalizerGenerator);
        Graph tubeGraph = pipeline.build();

        GraphviConsolePrinter.printToConsole(context, tubeGraph);
    }
}
