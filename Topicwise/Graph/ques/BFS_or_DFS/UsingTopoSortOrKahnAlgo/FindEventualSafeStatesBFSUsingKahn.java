package BFS_or_DFS.UsingTopoSortOrKahnAlgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// Same as Kahn Algo / Using Kahn Algo
// TC: O( V + E ) ( directed graph bfs ) + O ( V + E ) ( filling the indegree array ) + O( V + E ) (filling the newGraph)
// SC: O(V) (indegree[] array) + O(V) (queue) + O(V) (list) O(V + E) (newGraph) => O(V)

public class FindEventualSafeStatesBFSUsingKahn {
    public static void main(String[] args) {
        int[][] graph = { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };
        System.out.println();
        System.out.println(eventualSafeNodes(graph));
        System.out.println();
    }

    public static ArrayList<Integer> eventualSafeNodes(int[][] graph) {
        ArrayList<ArrayList<Integer>> newGraph = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            newGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < graph.length; i++) {
            for (int edge : graph[i]) {
                newGraph.get(edge).add(i);
            }
        }

        int[] indegree = new int[graph.length];
        for (ArrayList<Integer> list : newGraph) {
            for (Integer i : list) {
                indegree[i] += 1;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        ArrayList<Integer> list = new ArrayList<>();
        bfs(newGraph, indegree, list, q);

        Collections.sort(list);
        return list;
    }

    public static void bfs(ArrayList<ArrayList<Integer>> newGraph, int[] indegree, ArrayList<Integer> list,
            Queue<Integer> q) {
        while (!q.isEmpty()) {
            int node = q.poll();
            list.add(node);

            for (Integer it : newGraph.get(node)) {
                indegree[it] -= 1;
                if (indegree[it] == 0)
                    q.add(it);
            }
        }
    }
}
