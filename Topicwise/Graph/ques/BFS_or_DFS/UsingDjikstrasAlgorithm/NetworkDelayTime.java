import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// TC: E(log V) (Djikstras)
// SC: O(V) (pq + delay)

public class NetworkDelayTime {
    static class Pair{
        int node;
        int time;
        public Pair(int node, int time){
            this.node = node;
            this.time = time;
        }
    }
    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4, k = 2;

        System.out.println();
        System.out.println(networkDelayTime(times, n, k));
        System.out.println();
    }
    public static int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] time: times){
            adj.get(time[0]).add(new Pair(time[1], time[2]));
        }

        PriorityQueue<Pair> q = new PriorityQueue<>((p1, p2) -> {
            if (p1.time == p2.time) {
                return Integer.compare(p1.node, p2.node);
            }
            return Integer.compare(p1.time, p2.time);
        });
        q.add(new Pair(k, 0));

        int[] delay = new int[n+1];
        Arrays.fill(delay, Integer.MAX_VALUE);
        delay[k] = 0;

        djikstra(adj, q, delay);

        int max = Integer.MIN_VALUE;
        for(int i = 1; i < delay.length; i++){
            if(delay[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, delay[i]);
        }
        return max;
    }
    public static void djikstra(ArrayList<ArrayList<Pair>> adj, PriorityQueue<Pair> q, int[] delay){
        while(!q.isEmpty()){
            Pair pair = q.poll();
            for(Pair p: adj.get(pair.node)){
                if(delay[p.node] > delay[pair.node] + p.time){
                    delay[p.node] = delay[pair.node] + p.time;
                    q.add(new Pair(p.node, delay[pair.node] + p.time));
                }
            }
            System.out.println();
        }
    }
}
