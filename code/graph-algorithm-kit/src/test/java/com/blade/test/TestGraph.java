package com.blade.test;


import com.blade.graph.Graph;
import com.blade.graph.storage.ListGraph;
import org.junit.Test;

public class TestGraph {

    static void add(Graph g) {
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 4);
        g.addEdge(1, 4);
        g.addEdge(1, 3);
        g.addEdge(4, 3);
        g.addEdge(3, 2);
        g.addEdge(3, 2);    // 陷阱1
        g.addEdge(3, 3);    // 陷阱2
    }

    @Test
    public void test() {

        Graph g = new ListGraph(5);
        add(g);
        System.out.println(g.toString());
    }
}
