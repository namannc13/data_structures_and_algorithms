import java.util.ArrayList;
import java.util.List;

// Using Tarjan's time in and low time algorithm
// TC: O(V + E)
// SC: O(V + E) (adj) + O(V) (tin + low + vis) + O(E) (bridges)

public class CriticalConnectionsInNetwork {
    static int timer = 1;
    public static void main(String[] args) {
        int n = 4;
        int[][] connections = {{0,1},{1,2},{2,0},{1,3}};
         
        System.out.println();
        System.out.println(criticalConnections(n, connections));
        System.out.println();
    }
    public static List<List<Integer>> criticalConnections(int n, int[][] connections) {
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
        List<List<Integer>> bridges = new ArrayList<>();

        dfs(0, -1, vis, adj, tin, low, bridges);
        return bridges;
    }
    public static void dfs(int node, int parent, int[] vis, ArrayList<ArrayList<Integer>> adj, int[] tin, int[] low, List<List<Integer>> bridges){
        vis[node] = 1;
        tin[node] = low[node] = timer;
        timer++;

        for(Integer it: adj.get(node)){
            if(it == parent) continue;
            if(vis[it] == 0){
                dfs(it, node, vis, adj, tin, low, bridges);
                low[node] = Math.min(low[node], low[it]);
                if(low[it] > tin[node]){
                    List<Integer> bridge = new ArrayList<>();
                    bridge.add(it);
                    bridge.add(node);
                    bridges.add(new ArrayList<>(bridge));
                }
            }else{
                low[node] = Math.min(low[node], low[it]);
            }
        }
    }
}
