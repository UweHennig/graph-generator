/**
 * @(#)SelectionRule.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator.contracts;

import java.util.ArrayList;
import java.util.List;

/**
 * SelectionRule
 *
 * @author Uwe Hennig
 */
@FunctionalInterface
public interface SelectionRule {
    List<Edge> apply(List<Edge> edges);

    SelectionRule ALL_RULE = edges -> edges;

    static SelectionRule nthRule(int step) {
        return edges -> {
            List<Edge> result = new ArrayList<>();
            for (int i = 0; i < edges.size(); i += step) {
                result.add(edges.get(i));
            }
            return result;
        };
    }

    static SelectionRule unusedRule(GraphContext context) {
        return edges -> {
            List<Edge> result = new ArrayList<>();
            for (Edge edge : edges) {
                if (!context.isUsedEdge(edge.edgeId())) {
                    result.add(edge);
                }
            }
            return result;
        };
    }
}
