import java.util.Arrays;
import java.util.PriorityQueue;

// TC: O(N^2(log(N))) ( O(log V) => O(log N^2) => O(N^2(log(N^2))) => O(N^2(log(N))) inserting/removing from pq for at most N^2 nodes + O(N^2) (neighbour checking))
// SC: O(N^2) ( pq + dist )

public class ShortestPathInBinaryMatrix {
    public static class Pair {
        int ind1;
        int ind2;
        int steps;

        public Pair(int steps, int ind1, int ind2) {
            this.ind1 = ind1;
            this.ind2 = ind2;
            this.steps = steps;
        }

        public Pair(int ind1, int ind2) {
            this.ind1 = ind1;
            this.ind2 = ind2;
            this.steps = -1;
        }
    }

    public static void main(String[] args) {
        int[][] graph = { { 0, 1 }, { 1, 0 } };
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> {
            if (p1.steps == p2.steps) {
                if (p1.ind1 == p2.ind1) {
                    return Integer.compare(p1.ind2, p2.ind2);
                }
                return Integer.compare(p1.ind1, p2.ind1);
            }
            return Integer.compare(p1.steps, p2.steps);
        });
        minHeap.add(new Pair(0, graph.length - 1, graph[0].length - 1));
        int[][] dist = new int[graph.length][graph[0].length];
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[graph.length - 1][graph[0].length - 1] = 0;
        bfs(minHeap, graph, dist, 0, 0);

        System.out.println();
        if (dist[0][0] < 0 || dist[0][0] == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(dist[0][0] + 1);
        System.out.println();
    }

    public static void bfs(PriorityQueue<Pair> q, int[][] graph, int[][] dist, int desI, int desJ) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if(p.ind1 == desI && p.ind2 == desJ) return;
            System.out.println("steps: " + p.steps + " ind1: " + p.ind1 + " ind2: " + p.ind2);
            int[] rowDir = { -1, 1, 0, 0, -1, -1, 1, 1 };
            int[] colDir = { 0, 0, -1, 1, -1, 1, -1, 1 };

            for (int d = 0; d < 8; d++) {
                int ind1 = p.ind1 + rowDir[d];
                int ind2 = p.ind2 + colDir[d];
                if (ind1 >= 0 && ind1 < graph.length && ind2 >= 0 && ind2 < graph[0].length) {
                    if (graph[ind1][ind2] == 1)
                        continue;
                    if (dist[ind1][ind2] > p.steps + 1) {
                        dist[ind1][ind2] = p.steps + 1;
                        q.add(new Pair(p.steps + 1, ind1, ind2));
                    }
                }
            }
        }
    }
}
