/**
 * @(#)SelectionRule.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator.contracts;

import java.util.List;

/**
 * SelectionRule
 *
 * @author Uwe Hennig
 */
public interface SelectionRule {
    List<Edge> apply(List<Edge> edges);
}
