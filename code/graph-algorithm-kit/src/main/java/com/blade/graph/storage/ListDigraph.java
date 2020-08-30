package com.blade.graph.storage;


import com.blade.graph.Digraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDigraph implements Digraph {

    private final int V;  // 顶点计数
    private int E;  // 边计数
    private final List<List<Integer>> adj;    // 邻接表，使用的双重表，原本使用的是泛型类数组，但是会有一个小问题https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createArrays

    public ListDigraph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        } else {
            // 初始化计数器
            this.V = v;
            this.E = 0;

            // 初始化邻接表，顺序存储+链式存储
            this.adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++) {
                this.adj.add(i, new LinkedList<>()); // 其实你用Vector也是没有任何问题的
            }
        }
    }

    // 子图构造器

    @Override
    public int V() {
        return this.V;
    }

    @Override
    public int E() {
        return this.E;
    }

    /*
     * 小心，有向图的方向感不一样
     */
    @Override
    public void addEdge(int v, int w) {
        // 越界检查
        GraphUtils.validateVertex(v, this.V);
        GraphUtils.validateVertex(w, this.V);

        // 简单图，拒绝自环和平行
        if (GraphUtils.checkSimpleGraph(this, v, w)) {
            this.adj.get(v).add(w);
            this.E++;
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return this.adj.get(v);
    }

    @Override
    public String toString() {

        return GraphUtils.printAdjacencyList(this);
    }

    @Override
    public Digraph reverse() {

        Digraph digraph = new ListDigraph(this.V);

        for (int v = 0; v < this.V; v++) {
            for (Integer i : this.adj(v)) {
                digraph.addEdge(i, v);
            }
        }
        return digraph;
    }
}
