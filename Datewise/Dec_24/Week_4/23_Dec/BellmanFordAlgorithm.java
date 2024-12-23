import java.util.ArrayList;
import java.util.Arrays;

// TC: O(V*E) 
// SC: O(V)

public class BellmanFordAlgorithm {
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
        adj.get(0).add(new Pair(1, 5));
        adj.get(1).add(new Pair(5, -3));
        adj.get(1).add(new Pair(2, -2));
        adj.get(2).add(new Pair(4, 3));
        adj.get(3).add(new Pair(2, 6));
        adj.get(3).add(new Pair(4, -2));
        adj.get(5).add(new Pair(3, 1));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < V; j++) {
                for (Pair p : adj.get(j)) {
                    if (dist[j] != Integer.MAX_VALUE && dist[j] + p.b < dist[p.a]) {
                        dist[p.a] = dist[j] + p.b;
                    }
                }
            }
        }
        boolean flag = false;
        for (int j = 0; j < V; j++) {
            for (Pair p : adj.get(j)) {
                if (dist[j] + p.b < dist[p.a]) {
                    dist[p.a] = dist[j] + p.b;
                    flag = true;
                }
            }
        }

        System.out.println();
        if (flag)
            System.out.println("Negative Cycle");
        else
            System.out.println(Arrays.toString(dist));
        System.out.println();
    }
}
