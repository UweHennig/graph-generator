/**
 * @(#)EmptyGraphGenerator.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator;

import java.util.List;

import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphGenerator;

/**
 * EmptyGraphGenerator
 *
 * @author Uwe Hennig
 */
public class EmptyGraphGenerator implements GraphGenerator {
    @Override
    public List<Graph> generate(int interations, Graph initialGraph) {
        return List.of();
    }
}
