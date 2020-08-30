package com.blade.graph;


public abstract class Graphs {

    public static int degree(Graph graph, int v) {

        int degree = 0;
        for (int i : graph.adj(v)) {
            degree++;
        }

        return degree;
    }

    public static int maxDegree(Graph graph) {

        int max = 0;
        for (int i = 0; i < graph.V(); i++) {
            int de = degree(graph, i);
            if (de > max) {
                max = de;
            }
        }

        return max;
    }
}
