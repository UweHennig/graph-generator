/**
 * @(#)GraphContext.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.generator.contracts;

import java.util.BitSet;

/**
 * GraphContext
 *
 * @author Uwe Hennig
 */
public class GraphContext {
    private BitSet usedEdges = new BitSet(100);

    private int nodeId = 0;
    private int edgeId = 0;

    public int nextNodeId() {
        return ++nodeId;
    }

    public int nextEdgeId() {
        return ++edgeId;
    }

    public boolean isUsedEdge(int edgeId) {
        return usedEdges.get(edgeId);
    }

    public void setUnuseEdge(int edgeId) {
        usedEdges.clear(edgeId);
    }

    public void setUsedEdge(int edgeId) {
        usedEdges.set(edgeId);
    }
}
