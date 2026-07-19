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
    private final GraphContext context     = new GraphContext();
    private final Graph        masterGraph = new Graph(context, new ArrayList<>());

    private List<Graph> currentLayers = new ArrayList<>();

    private GraphPipeline() {
    }

    // --- Interface pipeline steps ---
    public interface StageAfterStart {
        StageAfterGenerate generate(GraphGenerator subGraph);
    }

    public interface StageAfterGenerate {
        StageAfterGenerate generate(GraphGenerator subGraph);
        StageAfterFinalize finalizeStep(GraphGenerator finalizer);
        Graph build();
    }

    public interface StageAfterFinalize {
        Graph build();
    }

    // --- step implementations ---
    // Entry point
    public static StageAfterStart start(GraphGenerator initializer) {
        GraphPipeline pipeline = new GraphPipeline();
        pipeline.currentLayers = initializer.generate(Graph.of(pipeline.context));
        pipeline.integrate(pipeline.currentLayers);
        return pipeline.new PipelineSteps();
    }

    private class PipelineSteps implements StageAfterStart, StageAfterGenerate, StageAfterFinalize {
        @Override
        public StageAfterGenerate generate(GraphGenerator subGraph) {
            currentLayers = currentLayers.stream()
                    .flatMap(g -> subGraph.generate(g).stream())
                    .toList();
            integrate(currentLayers);
            return this;
        }

        @Override
        public StageAfterFinalize finalizeStep(GraphGenerator finalizer) {
            List<Edge> allEdgesFromLayers = currentLayers.stream()
                .flatMap(g -> g.edges().stream())
                .distinct()
                .toList();

            List<Graph> finalResult = finalizer.generate(new Graph(context, allEdgesFromLayers));
            integrate(finalResult);
            return this;
        }

        @Override
        public Graph build() {
            return masterGraph;
        }
    }

    private void integrate(List<Graph> layers) {
        layers.forEach(g -> masterGraph.addAllEdges(g.edges()));
    }

}
