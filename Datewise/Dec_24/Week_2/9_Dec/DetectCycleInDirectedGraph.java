import java.util.ArrayList;

// TC: O(V+E) ( 'V' dfs calls and further child node calls in each of the dfs calls )
// SC: O(V) ( vis[] arr O(N) + recursion stack space O(N) + pathVis[] arr O(N) )

public class DetectCycleInDirectedGraph {
    public static void main(String[] args) {
        int V = 10;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(4);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(3).add(7);
        adj.get(7).add(5);
        adj.get(8).add(2);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);

        boolean[] vis = new boolean[V + 1];
        boolean[] pathVis = new boolean[V + 1];
        boolean ans = true;

        for(int i = 1; i < vis.length; i++){
            if(!vis[i]) {
                ans = dfs(i, vis, pathVis, adj);
            }
        }

        System.out.println();
        System.out.println(ans);
        System.out.println();
    }

    public static boolean dfs(int node, boolean vis[], boolean pathVis[], ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        pathVis[node] = true;
        boolean bool = false;

        for (Integer it : adj.get(node)) {
            if (vis[it] == false) {
                bool = dfs(it, vis, pathVis, adj);
            }else{
                if(pathVis[it] == true){
                    bool = true;
                    break;
                }
            }
        }

        pathVis[node] = false;
        return bool;
    }
}
