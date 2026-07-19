/**
 * @(#)PhytagorasGraphExample.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.example;

import com.uwe_hennig.graph.generator.AlternatingGraphGenerator;
import com.uwe_hennig.graph.generator.EmptyGraphGenerator;
import com.uwe_hennig.graph.generator.LineGraphGenerator;
import com.uwe_hennig.graph.generator.RingGraphGenerator;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.pipeline.GraphPipeline;
import com.uwe_hennig.graph.rule.NthRule;
import com.uwe_hennig.graph.rule.UnusedRule;
import com.uwe_hennig.graph.util.GraphviConsolePrinter;

/**
 * PhytagorasGraphExample
 *
 * @author Uwe Hennig
 */
public class PhytagorasGraphExample {

    public static void main(String[] args) {
        GraphContext context = new GraphContext();
        UnusedRule unusedRule = new UnusedRule(context);
        NthRule nthRule = new NthRule(2);

        LineGraphGenerator initialGraphGenerator = new LineGraphGenerator(1, context, unusedRule);

        RingGraphGenerator squareGenerator = new RingGraphGenerator(3, context, unusedRule);
        RingGraphGenerator triangleGenerator = new RingGraphGenerator(2, context, nthRule);

        AlternatingGraphGenerator graphGnerator = new AlternatingGraphGenerator(3, squareGenerator,  triangleGenerator);

        EmptyGraphGenerator finalGenerator = new EmptyGraphGenerator();

        GraphPipeline pipeline = GraphPipeline.start(initialGraphGenerator);
        pipeline.generate(5, graphGnerator);
        pipeline.finalize(finalGenerator);
        Graph pythagorasFractalGraph = pipeline.build();

        GraphviConsolePrinter.printToConsole(context, pythagorasFractalGraph);
    }

}
