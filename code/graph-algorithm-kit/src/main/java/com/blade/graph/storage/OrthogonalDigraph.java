package com.blade.graph.storage;

import com.blade.graph.Digraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrthogonalDigraph implements Digraph {

    private final int V;
    private int E;
    private final List<VexNode> adj;
    private final Set<ArcNode> arcs;

    public OrthogonalDigraph(int v) {

        if (v < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        } else {
            // 初始化计数器
            this.V = v;
            this.E = 0;

            // 初始化邻接表主体，也就是顶点数组
            this.adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++) {
                this.adj.add(i, new VexNode(i));
            }

            // 初始化边池，方便读取边数据
            this.arcs = new HashSet<>();
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

    /*
     * 注意这个图是有向的
     * v->w tail->head
     */
    @Override
    public void addEdge(int v, int w) {
        // 越界检查和简单图检验
        GraphUtils.validateVertex(v, this.V);
        GraphUtils.validateVertex(w, this.V);
        if (!GraphUtils.checkSimpleGraph(this, v, w)) return;

        VexNode t = null;
        VexNode h = null;

        for (VexNode vex : this.adj) {
            if (vex.id == v) {
                t = vex;
            }
            if (vex.id == w) {
                h = vex;
            }
        }

        for (ArcNode arc : arcs) {
            if (arc.tail == t && arc.head == h) {
                return;
            }
        }

        ArcNode arc = new ArcNode();
        arc.tail = t != null ? t : findVex(v);
        arc.head = h != null ? h : findVex(w);

        this.adj.get(v).addArc(arc);
        this.E++;
    }

    private VexNode findVex(int id) {
        for (VexNode vex : this.adj) {
            if (vex.id == id) return vex;
        }
        return null;
    }

    @Override
    public Iterable<Integer> adj(int v) {

        List<Integer> list = new ArrayList<>();
        VexNode vex = findVex(v);

       if (vex!=null) {
           ArcNode current = vex.out;
           while (current != null) {
               list.add(current.head.id);
               current = current.tlink;
           }
       }

        return list;
    }

    @Override
    public Digraph reverse() {

        Digraph digraph = new OrthogonalDigraph(this.V);

        for (int v = 0; v < this.V; v++) {
            for (Integer w : this.adj(v)) {
                digraph.addEdge(w, v);
            }
        }

        return digraph;
    }

    @Override
    public String toString() {

        return GraphUtils.printAdjacencyList(this);
    }

    private static class ArcNode {
        // tail->head
        VexNode tail;
        VexNode head;

        ArcNode hlink;  // head's brother
        ArcNode tlink;  // tail's brother
    }

    private static class VexNode {
        int id;
        ArcNode in;
        ArcNode out;

        public VexNode(int id) {
            this.id = id;
        }

        public void addArc(ArcNode arc) {

            arc.tlink = out;
            out = arc;

            VexNode headVex = arc.head;
            arc.hlink = headVex.in;
            headVex.in = arc;

        }
    }

    public static void main(String[] args) {

        Digraph g = new OrthogonalDigraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        g.addEdge(3, 1);
        g.addEdge(3, 1);    // 陷阱1
        g.addEdge(3, 3);    // 陷阱2
        g.addEdge(3, 2);
        System.out.println(g.toString());
        System.out.println(g.reverse().toString());
    }
}
