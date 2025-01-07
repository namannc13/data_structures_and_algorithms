import java.util.ArrayList;
import java.util.List;

// Using Tarjan's time in and low time algorithm
// TC: O(V + E)
// SC: O(V + E) (adj) + O(V) (tin + low + vis + mark) + O(E) (articulation point)

public class ArticulationPointInGraph {
    static int timer = 1;
    public static void main(String[] args) {
        int n = 4;
        int[][] connections = {{0,1},{1,2},{2,0},{1,3}};
         
        System.out.println();
        System.out.println(criticalConnections(n, connections));
        System.out.println();
    }
    public static List<Integer> criticalConnections(int n, int[][] connections) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i =0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] connection: connections){
            adj.get(connection[0]).add(connection[1]);
            adj.get(connection[1]).add(connection[0]);
        }

        int[] tin = new int[n];
        int[] low = new int[n];
        int[] vis = new int[n];
        int[] mark = new int[n];

        dfs(0, -1, vis, adj, tin, low, mark);
        List<Integer> articulationPoint = new ArrayList<>();
        for(int i = 0; i < mark.length; i++){
            if(mark[i] == 1) articulationPoint.add(i);
        }
        return articulationPoint;
    }
    public static void dfs(int node, int parent, int[] vis, ArrayList<ArrayList<Integer>> adj, int[] tin, int[] low, int[] mark){
        vis[node] = 1;
        tin[node] = low[node] = timer;
        timer++;

        for(Integer it: adj.get(node)){
            if(it == parent) continue;
            if(vis[it] == 0){
                dfs(it, node, vis, adj, tin, low, mark);
                low[node] = Math.min(low[node], low[it]);
                if(low[it] >= tin[node] && parent != -1){
                    mark[node] = 1;
                }
            }else{
                low[node] = Math.min(low[node], tin[it]);
            }
        }
    }
}
