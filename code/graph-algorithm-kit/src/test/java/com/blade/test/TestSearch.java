package com.blade.test;

import com.blade.graph.Digraph;
import com.blade.graph.Graph;
import com.blade.graph.Graphs;
import com.blade.graph.storage.ListDigraph;
import com.blade.graph.storage.ListGraph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestSearch {

    @Test
    public void testGraphBFS() {

        Graph graph = new ListGraph(10);

        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(6, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 6);
        graph.addEdge(3, 2);
        graph.addEdge(3, 7);
        graph.addEdge(6, 7);
        graph.addEdge(9, 8);

        System.out.println(graph);

        // 从顶点0出发
        List<Integer[]> res = Graphs.breadthFirstTraverse(graph, 1);
        System.out.println("the forest from 1: ");
        for (Integer[] is : res) {
            System.out.println(Arrays.toString(is));
        }
        // 从顶点4出发
        List<Integer[]> res1 = Graphs.breadthFirstTraverse(graph, 2);
        System.out.println("the forest from 2: ");
        for (Integer[] is : res1) {
            System.out.println(Arrays.toString(is));
        }
        // 验证可达路径<1,5>
        System.out.println("the path 4->5: " + Graphs.breadthFirstSearch(graph, 4, 5));
        // 验证可达路径<2,4>
        System.out.println("the path 1->9: " + Graphs.breadthFirstSearch(graph, 1, 9));
    }

    @Test
    public void testDigraphBFS() {

        Digraph digraph = new ListDigraph(8);

//        digraph.addEdge(0, 4);
        digraph.addEdge(4, 0);
        digraph.addEdge(1, 2);
        digraph.addEdge(1, 5);
        digraph.addEdge(5, 2);
        digraph.addEdge(2, 6);
        digraph.addEdge(3, 6);
        digraph.addEdge(3, 7);

        System.out.println(digraph);

        // 从顶点0出发
        List<Integer[]> res = Graphs.breadthFirstTraverse(digraph, 0);
        System.out.println("the forest from 0: ");
        for (Integer[] is : res) {
            System.out.println(Arrays.toString(is));
        }
        // 从顶点1出发
        List<Integer[]> res1 = Graphs.breadthFirstTraverse(digraph, 1);
        System.out.println("the forest from 1: ");
        for (Integer[] is : res1) {
            System.out.println(Arrays.toString(is));
        }
        // 验证可达路径<1,6>
        System.out.println("the path 1->6: " + Graphs.breadthFirstSearch(digraph, 1, 6));
        // 验证可达路径<1,7>
        System.out.println("the path 1->7: " + Graphs.breadthFirstSearch(digraph, 1, 7));
    }


    @Test
    public void testGraphDFS() {

        Graph graph = new ListGraph(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 3);
        graph.addEdge(3, 5);
        graph.addEdge(1, 2);

        System.out.println(graph);

        // 从顶点0出发
        List<Integer[]> res = Graphs.depthFirstTraverse(graph, 0);
        System.out.println("the forest from 0: ");
        for (Integer[] is : res) {
            System.out.println(Arrays.toString(is));
        }
        // 从顶点4出发
        List<Integer[]> res1 = Graphs.depthFirstTraverse(graph, 4);
        System.out.println("the forest from 4: ");
        for (Integer[] is : res1) {
            System.out.println(Arrays.toString(is));
        }
        // 验证可达路径<1,5>
        System.out.println("the path 1->5: " + Graphs.depthFirstSearch(graph, 1, 5));
        // 验证可达路径<2,4>
        System.out.println("the path 2->4: " + Graphs.depthFirstSearch(graph, 2, 4));
    }

    @Test
    public void testDigraphDFS() {

        Digraph digraph = new ListDigraph(8);

//        digraph.addEdge(0, 4);
        digraph.addEdge(4, 0);
        digraph.addEdge(1, 2);
        digraph.addEdge(1, 5);
        digraph.addEdge(5, 2);
        digraph.addEdge(2, 6);
        digraph.addEdge(3, 6);
        digraph.addEdge(3, 7);

        System.out.println(digraph);

        // 从顶点0出发
        List<Integer[]> res = Graphs.depthFirstTraverse(digraph, 0);
        System.out.println("the forest from 0: ");
        for (Integer[] is : res) {
            System.out.println(Arrays.toString(is));
        }
        // 从顶点1出发
        List<Integer[]> res1 = Graphs.depthFirstTraverse(digraph, 1);
        System.out.println("the forest from 1: ");
        for (Integer[] is : res1) {
            System.out.println(Arrays.toString(is));
        }
        // 验证可达路径<1,6>
        System.out.println("the path 1->6: " + Graphs.depthFirstSearch(digraph, 1, 6));
        // 验证可达路径<1,7>
        System.out.println("the path 1->7: " + Graphs.depthFirstSearch(digraph, 1, 7));
    }
}
