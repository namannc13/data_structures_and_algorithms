package BFS_or_DFS;

import java.util.ArrayList;
import java.util.Arrays;

// Same as DFS
// TC: O(V+E) ( 'V' dfs calls and further child node calls in each of the dfs calls )
// SC: O(V) ( vis[] arr O(N) + recursion stack space O(N) )

public class IsGraphBipartiteDFS {
    public static void main(String[] args) {
        int V = 8;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        addEdge(list, 1, 2);
        addEdge(list, 2, 3);
        addEdge(list, 3, 4);
        addEdge(list, 5, 4);
        addEdge(list, 5, 6);
        addEdge(list, 2, 6);
        addEdge(list, 7, 4);
        addEdge(list, 7, 8);

        boolean isBi = true;
        int[] vis = new int[V + 1];
        Arrays.fill(vis, -1);

        for (int i = 1; i <= V; i++) {
            if (vis[i] == -1) {
                if (!isBipartite(i, vis, list, 0)) {
                    isBi = false;
                    break;
                }
            }
        }

        System.out.println();
        System.out.println(isBi);
        System.out.println();
    }

    public static boolean isBipartite(int node, int[] vis, ArrayList<ArrayList<Integer>> adj, int color) {
        vis[node] = color;
        boolean bool = true;

        for (Integer it : adj.get(node)) {
            if (vis[it] != -1) {
                if (vis[it] == vis[node]) {
                    bool = false;
                    break;
                }
            } else {
                int c = color == 1 ? 0 : 1;
                bool = isBipartite(it, vis, adj, c);
            }
        }
        return bool;
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
