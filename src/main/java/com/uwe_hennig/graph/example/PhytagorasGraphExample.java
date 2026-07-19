/**
 * @(#)PhytagorasGraphExample.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.example;

import static com.uwe_hennig.graph.generator.contracts.SelectionRule.nthRule;
import static com.uwe_hennig.graph.generator.contracts.SelectionRule.unusedRule;

import com.uwe_hennig.graph.generator.AlternatingGraphGenerator;
import com.uwe_hennig.graph.generator.EmptyGraphGenerator;
import com.uwe_hennig.graph.generator.LineGraphGenerator;
import com.uwe_hennig.graph.generator.RingGraphGenerator;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.pipeline.GraphPipeline;
import com.uwe_hennig.graph.util.GraphvizConsolePrinter;

/**
 * PhytagorasGraphExample
 *
 * @author Uwe Hennig
 */
public class PhytagorasGraphExample {

    public static void main(String[] args) {
        GraphContext context = new GraphContext();

        LineGraphGenerator initialGraphGenerator = new LineGraphGenerator(1, context, unusedRule(context));

        RingGraphGenerator squareGenerator = new RingGraphGenerator(3, context, unusedRule(context));
        RingGraphGenerator triangleGenerator = new RingGraphGenerator(2, context, nthRule(2));

        AlternatingGraphGenerator graphGnerator = new AlternatingGraphGenerator(2, squareGenerator,  triangleGenerator);

        EmptyGraphGenerator finalGenerator = new EmptyGraphGenerator();

        Graph graph = GraphPipeline.start(initialGraphGenerator)
            .generate(graphGnerator)
            .finalizeStep(finalGenerator)
            .build();

        GraphvizConsolePrinter.printToConsole(context, graph);
    }

}
