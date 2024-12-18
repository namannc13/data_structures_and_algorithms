import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

// Same as Djikstra
// TC: TC: E(log V) + O(N) (backtracking memo array)
// SC: O(V) ( bfs + pq + dist + memo)

public class PrintShortestPath {
    public static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(new Pair(4, 1));
        adj.get(1).add(new Pair(2, 2));
        adj.get(2).add(new Pair(5, 5));
        adj.get(2).add(new Pair(3, 4));
        adj.get(2).add(new Pair(1, 2));
        adj.get(3).add(new Pair(4, 3));
        adj.get(3).add(new Pair(2, 4));
        adj.get(3).add(new Pair(5, 1));
        adj.get(4).add(new Pair(3, 3));
        adj.get(4).add(new Pair(1, 1));
        adj.get(5).add(new Pair(2, 5));
        adj.get(5).add(new Pair(3, 1));

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> {
            if (p1.a == p2.a) {
                return Integer.compare(p1.b, p2.b);
            }
            return Integer.compare(p1.a, p2.a);
        });
        int srcNode = 1;
        int destination = 5;
        minHeap.add(new Pair(0, srcNode));
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[srcNode] = 0;
        int[] memo = new int[V + 1];
        Arrays.fill(memo, 0);
        bfs(minHeap, adj, dist, memo);

        System.out.println();
        System.out.println(Arrays.toString(dist));
        System.out.println();
        System.out.println(Arrays.toString(memo));
        System.out.println();

        ArrayList<Integer> list = new ArrayList<>();
        while (destination != srcNode) {
            list.add(destination);
            destination = memo[destination];
        }
        list.add(srcNode);
        Collections.reverse(list);
        System.out.println(list);
    }

    public static void bfs(PriorityQueue<Pair> q, ArrayList<ArrayList<Pair>> adj, int[] dist, int[] memo) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            System.out.println("A: " + p.a + " B: " + p.b);
            for (Pair p2 : adj.get(p.b)) {
                if (dist[p2.a] > p.a + p2.b) {
                    dist[p2.a] = p.a + p2.b;
                    q.add(new Pair(p.a + p2.b, p2.a));
                    memo[p2.a] = p.b;
                }
            }
        }
    }
}
