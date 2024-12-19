import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Djikstras was O(E(log V)) -> removed pq therefore -> O(E)
// TC: O(E) (iterate over flights array) + O(E) (bfs) => O(E)
// SC: O(n + E) (adj) + O(V) (dist array) 

public class CheapestFlightsWithinKStops {
    static class Pair {
        int node;
        int weight;
        int k;

        public Pair(int weight, int node) {
            this.node = node;
            this.weight = weight;
        }

        public Pair(int weight, int node, int k) {
            this.node = node;
            this.weight = weight;
            this.k = k;
        }
    }

    public static void main(String[] args) {
        int n = 5, src = 0, dst = 2, k = 2;
        int[][] flights = { { 0, 1, 5 }, { 1, 2, 5 }, { 0, 3, 2 }, { 3, 1, 2 }, { 1, 4, 1 }, { 4, 2, 1 } };

        ArrayList<ArrayList<Pair>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < flights.length; i++) {
            list.get(flights[i][0]).add(new Pair(flights[i][2], flights[i][1]));
        }

        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(0, src, k));

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        bfs(q, list, dist, dst);

        System.out.println();
        System.out.println(dist[dst]);
        System.out.println();
    }

    public static void bfs(Queue<Pair> q, ArrayList<ArrayList<Pair>> list, int[] dist, int dst) {
        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (Pair pair : list.get(p.node)) {
                if (p.weight + pair.weight <= dist[pair.node]) {
                    if(pair.node == dst){
                        if(p.k >= 0){
                            dist[pair.node] = p.weight + pair.weight;
                        }
                    }else{
                        dist[pair.node] = p.weight + pair.weight;
                        q.add(new Pair(p.weight + pair.weight, pair.node, p.k - 1));
                    }
                }
            }
        }
    }
}
