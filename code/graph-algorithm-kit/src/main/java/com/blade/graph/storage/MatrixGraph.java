package com.blade.graph.storage;

import com.blade.graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class MatrixGraph implements Graph {

    private final int V;
    private int E;
    private final Integer[][] adj;

    public MatrixGraph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        } else {
            // 初始化计数器
            this.V = v;
            this.E = 0;

            // 初始化邻接表，顺序存储+链式存储
            this.adj = new Integer[v][v];
            // System.out.println(233);
        }
    }

    // 子图读取

    @Override
    public int V() {
        return this.V;
    }

    @Override
    public int E() {
        return this.E;
    }

    @Override
    public void addEdge(int v, int w) {

        GraphUtils.validateVertex(v, this.V);
        GraphUtils.validateVertex(v, this.V);

        if (GraphUtils.checkSimpleGraph(this, v, w)) {
            this.adj[v][w] = 1;
            this.adj[w][v] = 1;
            this.E++;
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {

        List<Integer> list = new ArrayList<>(this.V());

        for (int i = 0; i < adj.length; i++) {
            if (adj[v][i] != null && adj[v][i] == 1) {
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public String toString() {

        return GraphUtils.printAdjacencyList(this);
    }
}
