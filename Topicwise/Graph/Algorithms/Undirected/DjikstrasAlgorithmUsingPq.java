import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// Same as BFS
// TC: O(N) ( all nodes ) + O(2E) ( the inner for loop will run for all the node's neighbours => total degree => 2*Edges )
// SC: O(V) ( bfs + vis[] arr + pq + dist)

public class DjikstrasAlgorithmUsingPq {
    public static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        int V = 6;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(new Pair(1, 4));
        adj.get(0).add(new Pair(2, 4));
        adj.get(1).add(new Pair(0, 4));
        adj.get(1).add(new Pair(2, 2));
        adj.get(2).add(new Pair(0, 4));
        adj.get(2).add(new Pair(1, 2));
        adj.get(2).add(new Pair(3, 3));
        adj.get(2).add(new Pair(4, 1));
        adj.get(2).add(new Pair(5, 6));
        adj.get(3).add(new Pair(2, 3));
        adj.get(3).add(new Pair(5, 2));
        adj.get(4).add(new Pair(2, 1));
        adj.get(4).add(new Pair(5, 3));
        adj.get(5).add(new Pair(2, 6));
        adj.get(5).add(new Pair(3, 2));
        adj.get(5).add(new Pair(4, 3));

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> {
            if (p1.a != p2.a) {
                return Integer.compare(p1.a, p2.a);
            }
            return Integer.compare(p1.b, p2.b);
        });
        int srcNode = 0;
        minHeap.add(new Pair(srcNode, 0));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[srcNode] = 0;
        boolean[] vis = new boolean[V];
        vis[srcNode] = true;
        bfs(minHeap, adj, dist, vis);

        System.out.println();
        System.out.println(Arrays.toString(dist));
        System.out.println();
    }

    public static void bfs(PriorityQueue<Pair> q, ArrayList<ArrayList<Pair>> adj, int[] dist, boolean[] vis) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (Pair p2 : adj.get(p.a)) {
                if (!vis[p2.a])
                    q.add(new Pair(p2.a, p.b + p2.b));
                dist[p2.a] = Math.min(dist[p2.a], p.b + p2.b);
                vis[p2.a] = true;
            }
        }
    }
}
