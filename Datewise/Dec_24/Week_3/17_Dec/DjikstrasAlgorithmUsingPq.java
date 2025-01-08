import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// TC: TC: E(log V) 
// ( O(V) pq while loop * ( log(heap) (pop()) + O(V-1) (inner loop worst case) + log(heap) (push()) )
// O(V) * ( log(heap)(1 + V - 1)
// V * log(heap)(v)
// V^2 * log(V^2)  (worst case log(heap)) ( pq will add V-1 nodes continuously till the end )
// V^2 * 2 log(V)
// V^2 * log(V) => E(log(V)) ( I don't know how V^2 == E) ( update = ok in undirected graph it makes sense that V^2 = E )
// SC: O(V) ( bfs + pq + dist)

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
        int V = 4;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(new Pair(1, 6));
        adj.get(0).add(new Pair(2, 5));
        adj.get(1).add(new Pair(2, -2));
        adj.get(2).add(new Pair(3, 2));

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> {
            if (p1.b == p2.b) {
                return Integer.compare(p1.a, p2.a);
            }
            return Integer.compare(p1.b, p2.b);
        });
        int srcNode = 0;
        minHeap.add(new Pair(srcNode, 0));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[srcNode] = 0;
        bfs(minHeap, adj, dist);

        System.out.println();
        System.out.println(Arrays.toString(dist));
        System.out.println();
    }

    public static void bfs(PriorityQueue<Pair> q, ArrayList<ArrayList<Pair>> adj, int[] dist) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            System.out.println("Parent: " + p.a + " " + p.b);
            for (Pair p2 : adj.get(p.a)) {
                System.out.println("Child: " + p2.a + " " + p2.b);
                if (dist[p2.a] > p.b + p2.b) {
                    dist[p2.a] = p.b + p2.b;
                    System.out.println("Dist: " + dist[p2.a]);
                    q.add(new Pair(p2.a, p.b + p2.b));
                }
            }
            System.out.println();
        }
    }
}
