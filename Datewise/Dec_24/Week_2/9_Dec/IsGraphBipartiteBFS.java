import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Same as BFS
// TC: O(N) ( all nodes ) + O(2E) ( the inner for loop will run for all the node's neighbours => total degree => 2*Edges )
// SC: O(N) ( bfs + vis[] arr + queue)

public class IsGraphBipartiteBFS {
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
                if (!isBipartite(i, vis, list)){
                    isBi = false;
                    break;
                }
            }
        }


        System.out.println();
        System.out.println(isBi);
        System.out.println();
    }

    public static boolean isBipartite(int i, int[] vis, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();

        q.add(i);
        vis[i] = 0;

        while (!q.isEmpty()) {
            Integer node = q.poll();

            for (Integer it : adj.get(node)) {
                if (vis[it] != -1) {
                    if (vis[it] == vis[node])
                        return false;
                } else {
                    vis[it] = 1 - vis[node];
                    q.add(it);
                }
            }
        }

        return true;
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}