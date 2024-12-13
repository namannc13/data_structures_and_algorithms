import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

// TC: O(V+E) ( 'V' dfs calls and further child node calls in each of the dfs calls ) + O(V+E) (removing from stack and further traverse through all edges) + O(V) (to check the dist[] array) = O(V + E)
// SC: O(V) ( vis[] arr O(N) + recursion stack space O(N) + stack O(N) + dist[] array O(N) )

public class ShortestPathInDirectedAcyclicGraph {
    public static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(new Pair(1, 2));
        adj.get(1).add(new Pair(3, 1));
        adj.get(2).add(new Pair(3, 3));
        adj.get(4).add(new Pair(0, 3));
        adj.get(4).add(new Pair(2, 1));
        adj.get(5).add(new Pair(4, 1));
        adj.get(6).add(new Pair(4, 2));
        adj.get(6).add(new Pair(5, 3));

        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i])
                dfs(i, vis, adj, st);
        }

        int startInd = 6;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startInd] = 0;
        while (!st.isEmpty()) {
            int node = st.peek();

            for (Pair p : adj.get(node)) {
                if (dist[node] != Integer.MAX_VALUE && dist[node] + p.b < dist[p.a]) {
                    dist[p.a] = dist[node] + p.b;
                }
            }
            st.pop();
        }

        for(int i = 0; i < dist.length; i++){
            if(dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        System.out.println();
        System.out.println(Arrays.toString(dist));
        System.out.println();
    }

    public static void dfs(int node, boolean vis[], ArrayList<ArrayList<Pair>> adj, Stack<Integer> st) {
        vis[node] = true;
        for (Pair p : adj.get(node)) {
            if (vis[p.a] == false) {
                dfs(p.a, vis, adj, st);
            }
        }
        st.push(node);
    }
}
