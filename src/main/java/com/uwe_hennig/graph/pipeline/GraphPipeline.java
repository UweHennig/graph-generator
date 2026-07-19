/**
 * @(#)GraphPipeline.java
 * Copyright (c) 2026 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.graph.pipeline;

import java.util.ArrayList;
import java.util.List;

import com.uwe_hennig.graph.generator.contracts.Edge;
import com.uwe_hennig.graph.generator.contracts.Graph;
import com.uwe_hennig.graph.generator.contracts.GraphContext;
import com.uwe_hennig.graph.generator.contracts.GraphGenerator;

/**
 * GraphPipeline
 *
 * @author Uwe Hennig
 */
public class GraphPipeline {
    private final GraphContext context;
    private final Graph        masterGraph;

    private List<Graph> currentLayers = new ArrayList<>();
    private int         order         = -1;

    private GraphPipeline() {
        context = new GraphContext();
        masterGraph = new Graph(context, new ArrayList<>());
    }

    public static GraphPipeline start(GraphGenerator initializer) {
        GraphPipeline pipeline = new GraphPipeline();

        pipeline.currentLayers = initializer.generate(Graph.of(pipeline.context));
        pipeline.integrate(pipeline.currentLayers);
        pipeline.order = 0;
        return pipeline;
    }

    public GraphPipeline generate(int depth, GraphGenerator subGraph) {
        if (order != 0) {
            throw new IllegalStateException("invalid calling order");
        }

        order = 1;
        List<Graph> totalGraphs = new ArrayList<>();
        for (Graph graph : currentLayers) {
            List<Graph> currentGeneratedGraphs = subGraph.generate(graph);
            totalGraphs.addAll(currentGeneratedGraphs);
        }

        integrate(totalGraphs);
        currentLayers = totalGraphs;
        return this;
    }

    public GraphPipeline finalize(GraphGenerator finalizer) {
        if (order != 1) {
            throw new IllegalStateException("invalid calling order");
        }

        order = 2;

        List<Edge> resultEdges = new ArrayList<>();
        for (Graph layerGraph: currentLayers) {
            resultEdges.addAll(layerGraph.edges());
        }
        integrate(finalizer.generate(new Graph(this.context, resultEdges)));

        return this;
    }

    public Graph build() {
        return masterGraph;
    }

    private void integrate(List<Graph> layers) {
        for (Graph g : layers) {
            masterGraph.addAllEdges(g.edges());
        }
    }

}
