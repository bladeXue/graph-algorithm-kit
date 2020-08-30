package com.blade.graph.storage;

import com.blade.graph.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetGraph implements Graph {

    private final int V;
    private int E;
    private final List<Set<Integer>> adj;

    public SetGraph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        } else {
            // 初始化计数器
            this.V = v;
            this.E = 0;

            // 初始化邻接表，顺序存储+链式存储
            this.adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++) {
                this.adj.add(i, new HashSet<>()); // 屏蔽平行边
            }
        }
    }

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
        GraphUtils.validateVertex(w, this.V);

        // 此处代码和ListGraph一致，只是写法不同
        if (GraphUtils.checkSimpleGraph(this, v, w) && adj.get(v).add(w) && adj.get(w).add(v)) {
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

}
