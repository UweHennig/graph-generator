/**
 * @(#)AllRule.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.rule;

import java.util.List;

import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.SelectionRule;

/**
 * AllRule
 *
 * @author Uwe Hennig
 */
public class AllRule implements SelectionRule {

    @Override
    public List<Edge> apply(List<Edge> edges) {
        return edges;
    }

}
