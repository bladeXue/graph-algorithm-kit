package com.blade.graph;


import java.util.*;

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

    public static boolean breadthFirstSearch(Graph graph, Integer src, Integer tar) {

        validateVertex(src, graph);
        validateVertex(tar, graph);

        List<Integer> marked = new ArrayList<>();
        bfs(graph, marked, src);

        return marked.contains(tar);
    }

    public static List<Integer[]> breadthFirstTraverse(Graph graph, Integer src) {

        validateVertex(src, graph);

        List<Integer[]> results = new ArrayList<>();
        List<Integer> marked = new ArrayList<>(graph.V());

        int first = Objects.requireNonNullElse(src, 0);

        for (int i = first; i < first + graph.V(); i++) {

            int n = i % graph.V();
            if (!marked.contains(n)) {

                int boundary = marked.size();
                bfs(graph, marked, n);

                Integer[] buf = new Integer[marked.size() - boundary];
                marked.subList(boundary, marked.size()).toArray(buf);
                results.add(buf);
            }
        }

        return results;
    }

    private static void bfs(Graph graph, List<Integer> marked, Integer src) {
        Queue<Integer> queue = new ArrayDeque<>();
        marked.add(src);
        queue.add(src);

        while (!queue.isEmpty()) {
            for (Integer i : graph.adj(queue.poll())) {
                if (!marked.contains(i)) {
                    marked.add(i);
                    queue.add(i);
                }
            }
        }
    }

    public static boolean depthFirstSearch(Graph graph, Integer src, Integer tar) {

        validateVertex(src, graph);
        validateVertex(tar, graph);

        List<Integer> marked = new ArrayList<>();
        dfs(graph, marked, src);

        return marked.contains(tar);
    }

    public static List<Integer[]> depthFirstTraverse(Graph graph, Integer src) {

        validateVertex(src, graph);
        int first = Objects.requireNonNullElse(src, 0);

        List<Integer[]> results = new ArrayList<>();
        List<Integer> marked = new ArrayList<>();

        for (int i = first; i < first + graph.V(); i++) {

            int n = i % graph.V();
            if (!marked.contains(n)) {

                int boundary = marked.size();
                dfs(graph, marked, n);

                Integer[] buf = new Integer[marked.size() - boundary];
                marked.subList(boundary, marked.size()).toArray(buf);
                results.add(buf);
            }
        }

        return results;
    }

    private static void dfs(Graph graph, List<Integer> marked, Integer v) {

        marked.add(v);

        for (Integer i : graph.adj(v))
            if (!marked.contains(i))
                dfs(graph, marked, i);
    }

    public static void validateVertex(int v, Graph graph) {
        if (v < 0 || v >= graph.V()) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (graph.V() - 1));
        }
    }
}
