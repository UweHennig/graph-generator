/**
 * @(#)AlternatingGraphGenerator.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphGenerator;

/**
 * AlternatingGraphGenerator
 *
 * @author Uwe Hennig
 */
public class AlternatingGraphGenerator implements GraphGenerator {
    private final GraphGenerator generatorA;
    private final GraphGenerator generatorB;

    private Stack<Graph> stackA = new Stack<>();
    private Stack<Graph> stackB = new Stack<>();

    private final int iterations;

    public AlternatingGraphGenerator(int iterations, GraphGenerator generatorA, GraphGenerator generatorB) {
        this.generatorA = generatorA;
        this.generatorB = generatorB;
        this.iterations = iterations;
    }

    @Override
    public List<Graph> generate(Graph initialGraph) {
        stackB.push(initialGraph);
        return generate();
    }

    private List<Graph> generate() {
        List<Graph> resultingGraphList = new ArrayList<>();

        // Main loop
        for (int i = 0; i < iterations; i++) {
            // Phase A -> B
            while (!stackA.isEmpty()) {
                Graph currentLayer = stackA.pop();
                List<Graph> nextLayers = generatorB.generate(currentLayer);

                if (nextLayers != null) {
                    resultingGraphList.addAll(nextLayers);
                    for (Graph graph : nextLayers) {
                        stackB.push(graph);
                    }

                }
            }

            // Phase B -> A
            while (!stackB.isEmpty()) {
                Graph currentLayer = stackB.pop();
                List<Graph> nextLayers = generatorA.generate(currentLayer);

                if (nextLayers != null) {
                    resultingGraphList.addAll(nextLayers);
                    for (Graph graph : nextLayers) {
                        stackA.push(graph);
                    }
                }
            }
        }

        return resultingGraphList;
    }

}
