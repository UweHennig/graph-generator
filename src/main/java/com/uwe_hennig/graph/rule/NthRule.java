/**
 * @(#)NthRule.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.rule;

import java.util.ArrayList;
import java.util.List;

import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.SelectionRule;

/**
 * NthRule
 *
 * @author Uwe Hennig
 */
public class NthRule implements SelectionRule {
    private final int step;

    public NthRule(int step) {
        this.step = step;
    }

    @Override
    public List<Edge> apply(List<Edge> edges) {
        List<Edge> result = new ArrayList<>();
        for (int i = 0; i < edges.size(); i += step) {
            result.add(edges.get(i));
        }
        return result;
    }

}
