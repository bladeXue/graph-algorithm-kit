package com.blade.graph.storage;


import com.blade.graph.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultiListGraph implements Graph {

    private final int V;
    private int E;  //  这个属性其实是卖萌的
    private final List<VexNode> adj;
    private final Set<EdgeNode> edges;

    public MultiListGraph(int v) {

        if (v < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        } else {
            // 初始化计数器
            this.V = v;
            this.E = 0;

            // 初始化邻接表，顺序存储+链式存储
            this.adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++) {
                this.adj.add(i, new VexNode(i));
            }
            edges = new HashSet<>();
        }
    }

    @Override
    public int V() {
        return this.V;
    }

    @Override
    public int E() {
        return this.edges.size();
    }

    @Override
    public void addEdge(int v, int w) {

        GraphUtils.validateVertex(v, this.V);
        GraphUtils.validateVertex(w, this.V);
        if (GraphUtils.checkSimpleGraph(this, v, w)) {
            EdgeNode edge = new EdgeNode();
            edge.vexA = findVex(v);
            edge.vexB = findVex(w);

            if (edge.vexA != null && edge.vexB != null) {
                edge.vexA.addEdge(edge);
                edges.add(edge);
                this.E++;
            }
        }
    }

    private VexNode findVex(int id) {
        for (VexNode vex : adj) {
            if (vex.id == id) return vex;
        }
        return null;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        List<Integer> list = new ArrayList<>();
        VexNode vex = findVex(v);

        if (vex != null) {
            EdgeNode current = vex.first;
            // 左右端点很可能是不对称的
            while (current != null && current.vexA != null && current.vexB != null) {
                if (current.vexA.id == v) {
                    list.add(current.vexB.id);
                    current = current.alink;
                    continue;
                }
                if (current.vexB.id == v) {
                    list.add(current.vexA.id);
                    current = current.blink;
                }
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return GraphUtils.printAdjacencyList(this);
    }

    private static class EdgeNode {
        VexNode vexA;
        VexNode vexB;
        EdgeNode alink;
        EdgeNode blink;
    }

    private static class VexNode {
        int id;
        EdgeNode first;

        public VexNode(int id) {
            this.id = id;
        }

        public void addEdge(EdgeNode edge) {
            // 只管为a->b或者a<->b
            VexNode vexA = edge.vexA;
            edge.alink = vexA.first;
            vexA.first = edge;

            VexNode vexB = edge.vexB;
            edge.blink = vexB.first;
            vexB.first = edge;
        }
    }

    public static void main(String[] args) {
        Graph g = new MultiListGraph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(2, 1);
        g.addEdge(4, 2);
        g.addEdge(4, 1);
        g.addEdge(3, 2);
        g.addEdge(3, 2);
        g.addEdge(3, 3);

        System.out.println(g.toString());
    }
}
