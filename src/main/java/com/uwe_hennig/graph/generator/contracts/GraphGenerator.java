/**
 * @(#)GraphGenerator.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator.contracts;

import java.util.List;

/**
 * GraphGenerator
 *
 * @author Uwe Hennig
 */
public interface GraphGenerator {
    List<Graph> generate(int iterations, Graph initialGraph);
}
