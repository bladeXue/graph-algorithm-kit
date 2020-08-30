package com.blade.graph;

// 一个抽象的无向图
public interface Graph {

    // 返回G=(V,E)
    int V();

    int E();

    // 新增一条边
    void addEdge(int v, int w);

    // 获取某个顶点的所有邻接点
    Iterable<Integer> adj(int v);

    // 静态内部类，封装工具函数
    static class GraphUtils {
        public static void validateVertex(int v, int V) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
            }
        }

        // 将图以字符串的形式打印
        public static String printAdjacencyList(Graph g) {
            StringBuilder sb = new StringBuilder("the graph of " + g.getClass().getSimpleName() + " : ");
            sb.append(g.V()).append(" vertices, ").append(g.E()).append(" edges \n");
            for (int i = 0; i < g.V(); i++) {
                sb.append("[").append(i).append("]");
                for (Integer integer : g.adj(i)) {
                    sb.append("->").append(integer);
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        public static String printAdjacencyMatrix(Graph g) {
            StringBuilder sb = new StringBuilder("the graph of " + g.getClass().getSimpleName() + " : ");
            sb.append(g.V()).append(" vertices, ").append(g.E()).append(" edges \n");

            int V = g.V();
            int[][] matrix = new int[V][V];
            for (int v = 0; v < V; v++) {
                for (Integer w : g.adj(v)) {
                    matrix[v][w] = 1;
                }
            }

            sb.append("   ");
            for (int i = 0; i < V; i++) {
                sb.append(i).append(" ");
            }
            sb.append("\n");

            // row i
            for (int i = 0; i < V; i++) {
                sb.append(i).append(": ");
                for (int j = 0; j < V; j++) {
                    sb.append(matrix[i][j]).append(" ");
                }
                sb.append("\n");
            }

            return sb.toString();
        }

        // true if the graph is simple
        public static boolean checkSimpleGraph(Graph graph, int v, int w) {
            for (Integer i : graph.adj(v)) {
                // 自环或平行边
                if (v == w || i.equals(w))
                    return false;
            }
            //throw new RuntimeException("edge has exited: " + v + " -> " + w);
            return true;
        }

        public static boolean checkSimpleGraph(Graph graph, int v, int w, boolean throwE) {
            for (Integer i : graph.adj(v)) {
                // 自环或平行边
                if (v == w || i.equals(w))
                    return false;
            }
            if (throwE) {
                throw new RuntimeException("edge has exited: " + v + " -> " + w);
            }
            return true;
        }

        // 平行边
        public static boolean hasParallel(Graph graph, int v, int w) {
            for (Integer i : graph.adj(v)) {
                if (i.equals(w))
                    return true;
            }
            return false;
        }

        // 自环
        public static boolean isLoop(Graph graph, int v, int w) {
            return v == w;
        }
    }
}
