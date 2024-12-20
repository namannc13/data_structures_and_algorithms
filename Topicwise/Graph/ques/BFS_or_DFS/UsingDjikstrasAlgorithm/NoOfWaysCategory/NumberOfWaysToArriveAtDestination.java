import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// TC: O(E log V)
// SC: O(V) ( bfs + minHeap + dist + occurence)

public class NumberOfWaysToArriveAtDestination {
    public static class Pair {
        long weight;
        int node;

        public Pair(long weight, int node) {
            this.weight = weight;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        int[][] roads = { { 0, 6, 7 }, { 0, 1, 2 }, { 1, 2, 3 }, { 1, 3, 3 }, { 6, 3, 3 }, { 3, 5, 1 }, { 6, 5, 1 },
                { 2, 5, 1 }, { 0, 4, 5 }, { 4, 6, 2 } };
        int n = 7;

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < roads.length; i++) {
            adj.get(roads[i][0]).add(new Pair(roads[i][2], roads[i][1]));
            adj.get(roads[i][1]).add(new Pair(roads[i][2], roads[i][0]));
        }

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> {
            if (p1.weight == p2.weight) {
                return Integer.compare(p1.node, p2.node);
            }
            return Long.compare(p1.weight, p2.weight);
        });

        int srcNode = 0;
        minHeap.add(new Pair(0, srcNode));

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[srcNode] = 0;

        long[] occurence = new long[n];
        Arrays.fill(occurence, 0);
        occurence[srcNode] = 1;

        long mod = 1_000_000_007;

        bfs(minHeap, adj, dist, occurence, mod);

        int ans = (int) (occurence[n - 1] % mod);
        System.out.println();
        System.out.println(ans);
        System.out.println();
    }

    public static void bfs(PriorityQueue<Pair> q, ArrayList<ArrayList<Pair>> adj, long[] dist, long[] occurence,
            long mod) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (Pair pair : adj.get(p.node)) {
                if (dist[pair.node] > p.weight + pair.weight) {
                    dist[pair.node] = p.weight + pair.weight;
                    q.add(new Pair(p.weight + pair.weight, pair.node));
                    occurence[pair.node] = occurence[p.node];
                } else if (dist[pair.node] == p.weight + pair.weight) {
                    occurence[pair.node] = (occurence[pair.node] + occurence[p.node]) % mod;
                }
            }
        }
    }
}