import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// TC: E(log V) (Djikstras)
// SC: O(V) (pq + maxProb)

public class PathWithMaximumProbability{
    static class Pair{
        int node;
        double prob;
        public Pair(int node, double prob){
            this.node = node;
            this.prob = prob;
        }
    }
    public static void main(String[] args) {
        int n = 3, start = 0, end = 2;
        int[][] edges = {{0,1},{1,2},{0,2}};
        double[] succProb = {0.5,0.5,0.2};

        System.out.println();
        System.out.println(maxProbability(n, edges, succProb, start, end));
        System.out.println();
    }
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        int i = 0;
        for(int[] edge: edges){
            adj.get(edge[0]).add(new Pair(edge[1], succProb[i]));
            adj.get(edge[1]).add(new Pair(edge[0], succProb[i++]));
        }

        PriorityQueue<Pair> q = new PriorityQueue<>((p1, p2) -> {
            if (p1.prob == p2.prob) {
                return Integer.compare(p1.node, p2.node);
            }
            return Double.compare(p2.prob, p1.prob);
        });
        q.add(new Pair(start_node, 1.0));

        Double[] maxProb = new Double[n];
        Arrays.fill(maxProb, Double.MIN_VALUE);
        maxProb[start_node] = 1.0;

        djikstra(adj, q, maxProb);

        return maxProb[end_node];
    }
    public static void djikstra(ArrayList<ArrayList<Pair>> adj, PriorityQueue<Pair> q, Double[] maxProb){
        while(!q.isEmpty()){
            Pair pair = q.poll();
            for(Pair p: adj.get(pair.node)){
                if(maxProb[p.node] < maxProb[pair.node] * p.prob){
                    maxProb[p.node] = maxProb[pair.node] * p.prob;
                    q.add(new Pair(p.node, maxProb[pair.node] * p.prob));
                }
            }
        }
    }
}