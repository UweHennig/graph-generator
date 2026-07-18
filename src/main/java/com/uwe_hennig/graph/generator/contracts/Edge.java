/**
 * @(#)Edge.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator.contracts;

/**
 * Edge
 *
 * @author Uwe Hennig
 */
public record Edge(int edgeId, int nodeFromId, int nodeToId) {
    @Override
    public final String toString() {
        return String.format("%2d -> %2d", nodeFromId, nodeToId);
    }
}
