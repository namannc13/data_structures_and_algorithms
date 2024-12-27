package Topicwise.Graph.DetectCycle.Undirected;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// TC: O(N + 2E) (We are traversing all the nodes and for each of the node, we are traversing all its adjacent nodes again in inner loop) (means we will traverse through the total degree of the graph which is 2*E in an undirected graph) + O(N) (for loop in main() for connected components) => O(N + 2E) + O(N)
// SC: O(N) (queue) + O(N) (vis[] array) => O(N)

public class CycleInGraphBFS {
    public static void main(String[] args) {
        int V = 9;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        addEdge(list, 1, 2);
        addEdge(list, 1, 3);
        addEdge(list, 2, 1);
        addEdge(list, 2, 4);
        addEdge(list, 3, 1);
        addEdge(list, 4, 2);
        addEdge(list, 5, 6);
        addEdge(list, 6, 5);
        addEdge(list, 7, 8);
        addEdge(list, 7, 9);
        addEdge(list, 8, 7);
        addEdge(list, 8, 9);
        addEdge(list, 9, 7);
        addEdge(list, 9, 8);

        // BFS - Connected components

        boolean ans = false;
        boolean[] vis = new boolean[list.size()];

        for (int i = 1; i < list.size(); i++) {
            if (vis[i] == false) {
                Queue<Pair> q = new LinkedList<>();
                vis[i] = true;

                for (Integer ind : list.get(i)) {
                    q.add(new Pair(ind, i));
                }
                ans = detectCycleBFS(list, vis, q);
            }
        }

        System.out.println();
        System.out.println(ans);
        System.out.println();
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static boolean detectCycleBFS(ArrayList<ArrayList<Integer>> list, boolean[] vis, Queue<Pair> q) {
        while (!q.isEmpty()) {
            Pair p = q.poll();

            vis[p.a] = true;

            for (Integer i : list.get(p.a)) {
                if (i != p.b) {
                    if (vis[i] == false) {
                        q.add(new Pair(i, p.a));
                    } else
                        return true;
                }
            }
        }
        return false;
    }

}
