package com.tcl.algorithm;

import java.util.Stack;

/**
 * @author https://github.com/103style
 * @date 2020/7/20 16:34
 */
public class TopologySort {

    static VertexNode[] adjList;
    Stack stack = new Stack();

    public static EdgeNode getAdjvex(VertexNode node) {
        EdgeNode e = node.firstedge;
        while (e != null) {
            if (e.next == null) {
                break;
            } else {
                e = e.next;
            }
        }
        return e;
    }

    public static void main(String[] args) {
        int[] ins = {0, 0, 2, 0, 2, 3, 1, 2, 2, 1, 1, 2, 1, 2};
        int[][] adjvexs = {
                {11, 5, 4},
                {8, 4, 2},
                {9, 6, 5},
                {13, 2},
                {7},
                {12, 8},
                {5},
                {},
                {7},
                {11, 10},
                {13},
                {},
                {9},
                {}
        };
        adjList = new VertexNode[ins.length];
        for (int i = 0; i < ins.length; i++) {
            adjList[i] = new VertexNode("V" + i, ins[i], null);
            if (adjvexs[i].length > 0) {
                for (int j = 0; j < adjvexs[i].length; j++) {
                    if (adjList[i].firstedge == null)
                        adjList[i].firstedge = new EdgeNode(adjvexs[i][j], null);
                    else {
                        getAdjvex(adjList[i]).next = new EdgeNode(adjvexs[i][j], null);
                    }
                }
            }
        }
        TopologySort t = new TopologySort();

        System.out.println(t.ToplogicalSort());

    }

    public String ToplogicalSort() {
        EdgeNode e;
        int k, gettop;
        int count = 0;
        for (int i = 0; i < adjList.length; i++) {
            if (adjList[i].in == 0) {
                stack.push(i);
            }
        }
        while (!stack.empty()) {
            gettop = (int) stack.pop();
            System.out.print(adjList[gettop].data + "->");
            count++;
            for (e = adjList[gettop].firstedge; e != null; e = e.next) {
                k = e.adjevex;
                if ((--adjList[k].in) == 0) {   //将其入度减少一位，目的是将顶点上的弧删除
                    stack.push(k);
                }
            }
        }
        System.out.println();
        return count < adjList.length ? (String) "ERROR" : (String) "OK";
    }

    static class VertexNode {

        int in;
        Object data;
        EdgeNode firstedge;

        public VertexNode(Object data, int in, EdgeNode firstedge) {
            this.data = data;
            this.in = in;
            this.firstedge = firstedge;
        }
    }

    static class EdgeNode {

        int adjevex;
        int weight;
        EdgeNode next;

        public EdgeNode(int adjevex, EdgeNode next) {
            this.adjevex = adjevex;
            this.next = next;
        }
    }
}
