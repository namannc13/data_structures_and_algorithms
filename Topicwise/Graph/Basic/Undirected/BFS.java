package Topicwise.Graph.Basic.Undirected;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// TC: O(N) ( all nodes ) + O(2E) ( the inner for loop will run for all the node's neighbours => total degree => 2*Edges )
// SC: O(N) ( bfs + vis[] arr + queue)

public class BFS {
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        addEdge(list, 1, 2);
        addEdge(list, 1, 3);
        addEdge(list, 2, 4);
        addEdge(list, 2, 5);

        ArrayList<Integer> ans = bfs(V, list);

        System.out.println(ans);
    }

    public static ArrayList<Integer> bfs(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] vis = new boolean[V + 1];
        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        vis[1] = true;

        while (!q.isEmpty()) {
            Integer node = q.poll();
            bfs.add(node);

            for (Integer it : adj.get(node)) {
                if (!vis[it]) {
                    vis[it] = true;
                    q.add(it);
                }
            }
        }

        return bfs;
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
