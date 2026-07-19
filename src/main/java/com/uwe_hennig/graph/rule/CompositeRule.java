/**
 * @(#)CompositeRule.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.SelectionRule;

/**
 * CompositeRule
 *
 * @author Uwe Hennig
 */
public record CompositeRule(SelectionRule left, SelectionRule right, Type type) implements SelectionRule {
    public enum Type { AND, OR, XOR }

    @Override
    public List<Edge> apply(List<Edge> edges) {
        List<Edge> a = left.apply(edges);
        List<Edge> b = right.apply(edges);

        return switch (type) {
            case AND -> {
                Set<Edge> setB = new HashSet<>(b);
                yield a.stream().filter(setB::contains).toList();
            }
            case OR -> Stream.concat(a.stream(), b.stream())
                            .distinct()
                            .toList();
            case XOR -> {
                Set<Edge> setA = new HashSet<>(a);
                Set<Edge> setB = new HashSet<>(b);
                yield Stream.concat(
                        a.stream().filter(e -> !setB.contains(e)),
                        b.stream().filter(e -> !setA.contains(e))
                ).distinct().toList();
            }
        };
    }
}
