import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// TC: O(E) ( pq while loop ) * (log E) ( pq poll() ) + O(E log E) ( inner for loop worst case and pq push() ) => O(E log E)
// SC: O(E) ( visited + pq + mst )

public class PrimsAlgorithm {
    public static class Edge {
        int dest;
        int weight;
        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
    public static class Pair {
        int cost;
        int node;
        int parent;
        public Pair(int cost, int node, int parent) {
            this.cost = cost;
            this.node = node;
            this.parent = parent;
        }
        public Pair(int parent, int node){
            this.parent = parent;
            this.node = node;
        }
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        int n = 5;
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Edge(1, 2));
        adj.get(0).add(new Edge(2, 1));
        adj.get(1).add(new Edge(0, 2));
        adj.get(1).add(new Edge(2, 1));
        adj.get(2).add(new Edge(0, 1));
        adj.get(2).add(new Edge(1, 1));
        adj.get(2).add(new Edge(3, 2));
        adj.get(2).add(new Edge(4, 2));
        adj.get(3).add(new Edge(2, 2));
        adj.get(3).add(new Edge(4, 1));
        adj.get(4).add(new Edge(2, 2));
        adj.get(4).add(new Edge(3, 1));

        int[] visited = new int[n];
        Arrays.fill(visited, 0);
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Pair(0, 0, -1));
        ArrayList<Pair> mst = new ArrayList<>();

        int sum = bfs(adj, visited, pq, mst);
        System.out.println();
        System.out.println(sum);
        System.out.println();
    }
    public static int bfs(ArrayList<ArrayList<Edge>> adj, int[] visited, PriorityQueue<Pair> pq, ArrayList<Pair> mst){
        int sum = 0;
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            if (visited[curr.node] == 1) continue;
            visited[curr.node] = 1;
            sum += curr.cost;
            if (curr.parent != -1) mst.add(new Pair(curr.parent, curr.node));
            for (Edge e : adj.get(curr.node)) {
                if (visited[e.dest] == 0) {
                    pq.add(new Pair(e.weight, e.dest, curr.node));
                }
            }
        }
        return sum;
    }
}