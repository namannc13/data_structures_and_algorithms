package BFS_or_DFS.UsingTopoSortOrKahnAlgo;

import java.util.ArrayList;

// Using Topological Sort Algorithm
// TC: O( V + E ) ( 'V' dfs calls and 'E' Each edge in the graph is traversed once during DFS ) + O(E) (iterating through prerequisites[] array to find the maxNode) => O(V+E)
// SC: O(V + V) (adj list) + O(V) (vis[] array) + O(V) (pathVis[] arr) + O(V) (recursion stack space) => O(V)

public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0},{0,1}};
        System.out.println(canFinish(numCourses, prerequisites));
    }
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int maxNode = 0;
        for (int[] prerequisite : prerequisites) {
            maxNode = Math.max(maxNode, Math.max(prerequisite[0], prerequisite[1]));
        }
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= maxNode; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] arr : prerequisites) {
            adj.get(arr[0]).add(arr[1]);
        }
        boolean[] vis = new boolean[maxNode + 1];
        boolean[] pathVis = new boolean[maxNode + 1];
        boolean ans = true;
        for (int i = 0; i < adj.size(); i++) {
            if (!vis[i]) {
                if (dfs(i, vis, pathVis, adj) == true) {
                    ans = false;
                    break;
                }
            }
        }
        return ans;
    }

    public static boolean dfs(int node, boolean vis[], boolean[] pathVis, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        pathVis[node] = true;
        boolean bool = false;
        for (Integer it : adj.get(node)) {
            if (vis[it] == false) {
                bool = dfs(it, vis, pathVis, adj);
                if(bool == true) break;
            } else {
                if (pathVis[it] == true) {
                    bool = true;
                    break;
                }
            }
        }
        pathVis[node] = false;
        return bool;
    }
}
