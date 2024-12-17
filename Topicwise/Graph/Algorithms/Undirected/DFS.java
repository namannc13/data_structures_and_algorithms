
import java.util.ArrayList;

// TC: O(V+E) ( 'V' dfs calls and further child node calls in each of the dfs calls )
// SC: O(V) ( vis[] arr O(N) + recursion stack space O(N) + ans list O(N) )

public class DFS {
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 4);
        addEdge(adj, 2, 5);

        boolean[] vis = new boolean[V + 1];

        ArrayList<Integer> list = new ArrayList<>();
        dfs(1, vis, adj, list);

        System.out.println(list);
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

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
