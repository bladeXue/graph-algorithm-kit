package com.blade.test;


import com.blade.graph.Graph;
import com.blade.graph.storage.ListDigraph;

public class TestSearch {

    public static void main(String[] args) {

        Graph g =new ListDigraph(8);

        g.addEdge(1, 5);
        g.addEdge(1, 2);
        g.addEdge(5, 2);
        g.addEdge(2, 6);
        g.addEdge(3, 6);
        g.addEdge(3, 7);

        for (int i = 0; i < 8; i++) {
            for (Integer n:g.adj(i))
                System.out.print(n+" ");

            System.out.println("");
        }
    }
}
