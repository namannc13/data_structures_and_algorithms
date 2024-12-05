package Topicwise.Graph.Basic;

import java.util.ArrayList;

// TC: O(N+2E) (dfs traversal) + O(N) (for loop for connected components)
// SC: O(N) (vis[] array) + O(N) (recursion stack space) => O(N)

public class CycleInGraphDFS {
    public static void main(String[] args) {
        int V = 9;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        // Connected components graph

        addEdge(list, 1, 2);
        addEdge(list, 1, 3);
        addEdge(list, 2, 4);
        addEdge(list, 5, 6);
        addEdge(list, 7, 8);
        addEdge(list, 7, 9);
        addEdge(list, 8, 9);

        boolean ans = false;
        boolean[] vis = new boolean[list.size()];

        for (int i = 1; i < list.size(); i++) {
            if (vis[i] == false) {
                ans = detectCycleDFS(list, vis, i, -1);
            }
        }

        System.out.println();
        System.out.println("ans: " + ans);
        System.out.println();
    }

    public static boolean detectCycleDFS(ArrayList<ArrayList<Integer>> list, boolean[] vis, int currInd, int lind) {
        vis[currInd] = true;
        boolean bool = false;

        for (Integer i : list.get(currInd)) {
            if (i != lind) {
                if (vis[i] == false) {
                    bool = detectCycleDFS(list, vis, i, currInd);
                } else {
                    bool = true;
                    break;
                }
            }
        }

        return bool || false;
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
