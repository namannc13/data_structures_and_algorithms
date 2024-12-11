package Topicwise.Graph.Algorithms.Directed;

import java.util.ArrayList;
import java.util.Stack;

// Same as DFS
// TC: O(V+E) ( 'V' dfs calls and further child node calls in each of the dfs calls )
// SC: O(V) ( vis[] arr O(N) + recursion stack space O(N) + ans list O(N) + stack O(N) )

public class TopologicalSort{
    public static void main(String[] args) {
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
            for(int i =0;  i < V; i++){
                if(!vis[i]) dfs(i, vis, adj, st);
            }

        for(int i = 0; i < V; i++){
            int n = st.peek();
            list.add(n);
            st.pop();
        }
        System.out.println(list);
    }

    public static void dfs(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = true;
        for (Integer it : adj.get(node)) {
            if (vis[it] == false) {
                dfs(it, vis, adj, st);
            }
        }
        st.push(node);
    }
}