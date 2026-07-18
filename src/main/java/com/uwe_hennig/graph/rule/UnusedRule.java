/**
 * @(#)UnusedRule.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.rule;

import java.util.ArrayList;
import java.util.List;

import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.generator.contracts.SelectionRule;

/**
 * UnusedRule
 *
 * @author Uwe Hennig
 */
public class UnusedRule implements SelectionRule {
    private GraphContext context;

    public UnusedRule(GraphContext context) {
        this.context = context;
    }

    @Override
    public List<Edge> apply(List<Edge> edges) {
        List<Edge> result = new ArrayList<>();
        for(Edge edge : edges) {
            if (!context.isUsedEdge(edge.edgeId())) {
                result.add(edge);
            }
        }

        return result;
    }
}
