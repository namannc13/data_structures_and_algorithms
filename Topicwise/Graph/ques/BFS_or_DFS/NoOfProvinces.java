package BFS_or_DFS;

import java.util.ArrayList;

// TC: O(N) ( dfs recursion call runs for every node ) + O(V+2E) ( each dfs call TC )
// SC: O(N) ( vis[] ) + O(N) ( recursion worst case ( skewed tree ) )

public class NoOfProvinces {
    public static void main(String[] args) {
        // int[][] isConnected = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
        int[][] isConnected = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };

        System.out.println();
        System.out.println(findCircleNum(isConnected));
        System.out.println();
    }

    public static int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= isConnected.length; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if (i != j && isConnected[i][j] != 0) {
                    adj.get(i + 1).add(j + 1);
                    adj.get(j + 1).add(i + 1);
                }
            }
        }

        boolean[] vis = new boolean[isConnected.length + 1];
        ArrayList<Integer> list = new ArrayList<>();
        int cnt = 0;

        for (int i = 1; i < vis.length; i++) {
            if (vis[i] == false) {
                dfs(i, vis, adj, list);
                cnt++;
            }
        }

        return cnt;
    }

    public static void dfs(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> list) {
        vis[node] = true;
        list.add(node);

        for (Integer it : adj.get(node)) {
            if (vis[it] == false) {
                dfs(it, vis, adj, list);
            }
        }
    }
}
