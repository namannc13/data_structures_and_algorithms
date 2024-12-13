package BFS_or_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Same as BFS
// TC: O(N) ( all nodes ) + O(2E) ( the inner for loop will run for all the node's neighbours => total degree => 2*Edges )
// SC: O(V) ( bfs + vis[] arr + queue + dist)

public class ShortestPathInUndirectedGraph {
    public static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        int V = 9;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }

        addEdge(list, 0, 1);
        addEdge(list, 0, 3);
        addEdge(list, 1, 3);
        addEdge(list, 1, 2);
        addEdge(list, 3, 4);
        addEdge(list, 4, 5);
        addEdge(list, 5, 6);
        addEdge(list, 2, 6);
        addEdge(list, 6, 7);
        addEdge(list, 7, 8);
        addEdge(list, 6, 8);

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] vis = new boolean[V];
        bfs(q, list, dist, vis);

        System.out.println();
        System.out.println(Arrays.toString(dist));
        System.out.println();
    }

    public static void bfs(Queue<Pair> q, ArrayList<ArrayList<Integer>> adj, int[] dist, boolean[] vis) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (Integer it : adj.get(p.a)) {
                if (!vis[it])
                    q.add(new Pair(it, p.b + 1));
                dist[it] = Math.min(dist[it], p.b + 1);
                vis[it] = true;
            }
        }
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
