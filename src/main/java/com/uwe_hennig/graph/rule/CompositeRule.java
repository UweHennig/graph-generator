/**
 * @(#)CompositeRule.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.rule;

import java.util.List;
import java.util.stream.Stream;

import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.SelectionRule;

/**
 * CompositeRule
 *
 * @author Uwe Hennig
 */
public class CompositeRule implements SelectionRule {
    public enum Type { AND, OR, XOR }

    private final SelectionRule left;
    private final SelectionRule right;
    private final Type type;

    public CompositeRule(SelectionRule left, SelectionRule right, Type type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }

    @Override
    public List<Edge> apply(List<Edge> edges) {
        List<Edge> a = left.apply(edges);
        List<Edge> b = right.apply(edges);

        return switch (type) {
            case AND -> a.stream().filter(b::contains).toList();
            case OR  -> Stream.concat(a.stream(), b.stream()).distinct().toList();
            case XOR -> Stream.concat(a.stream(), b.stream())
                              .filter(e -> !(a.contains(e) && b.contains(e)))
                              .distinct()
                              .toList();
        };
    }
}
