package com.blade.test;


import com.blade.graph.Digraph;
import com.blade.graph.storage.ListDigraph;
import com.blade.graph.storage.MatrixDigraph;
import com.blade.graph.storage.SetDigraph;
import org.junit.Test;

public class TestDigraph {

    static void add(Digraph g) {
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 4);
        g.addEdge(3, 1);
        g.addEdge(3, 1);    // 陷阱1
        g.addEdge(3, 3);    // 陷阱2
        g.addEdge(4, 3);
    }

    @Test
    public void test() {

        Digraph g = new ListDigraph(6);
        add(g);
        System.out.println(g.toString());
        System.out.println(g.reverse().toString());

        g = new MatrixDigraph(6);
        add(g);
        System.out.println(g.toString());
        System.out.println(g.reverse().toString());

        g = new SetDigraph(6);
        add(g);
        System.out.println(g.toString());
        System.out.println(g.reverse().toString());
    }
}
