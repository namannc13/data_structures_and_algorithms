import java.util.Arrays;
import java.util.PriorityQueue;

// TC: O(N^2(log(N))) ( O(log V) => O(log N^2) => O(N^2(log(N^2))) => O(N^2(log(N))) inserting/removing from pq for at most N^2 nodes + O(N^2) (neighbour checking))
// SC: O(N^2) ( pq + dist + vis )

public class PathWithMinimumEffort {
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
        int[][] heights = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,0,1},{1,1,1,0,1}};
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> {
            if (p1.steps == p2.steps) {
                if (p1.ind1 == p2.ind1) {
                    return Integer.compare(p1.ind2, p2.ind2);
                }
                return Integer.compare(p1.ind1, p2.ind1);
            }
            return Integer.compare(p1.steps, p2.steps);
        });
        minHeap.add(new Pair(0, heights.length - 1, heights[0].length - 1));
        int[][] dist = new int[heights.length][heights[0].length];
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[heights.length - 1][heights[0].length - 1] = 0;
        boolean[][] vis = new boolean[heights.length][heights[0].length];
        bfs(minHeap, heights, dist, 0, 0, vis);

        System.out.println();
        for(int[] h: heights){
            System.out.println(Arrays.toString(h));
        }
        System.out.println();
        for(int[] d: dist){
            System.out.println(Arrays.toString(d));
        }
        System.out.println();
    }

    public static void bfs(PriorityQueue<Pair> q, int[][] graph, int[][] dist, int desI, int desJ, boolean[][] vis) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            vis[p.ind1][p.ind2] = true;
            if (p.ind1 == desI && p.ind2 == desJ)
                return;
            int[] rowDir = { -1, 1, 0, 0 };
            int[] colDir = { 0, 0, -1, 1 };

            for (int d = 0; d < 4; d++) {
                int ind1 = p.ind1 + rowDir[d];
                int ind2 = p.ind2 + colDir[d];
                if (ind1 >= 0 && ind1 < graph.length && ind2 >= 0 && ind2 < graph[0].length) {
                    if (vis[ind1][ind2])
                        continue;
                    int diff = Math.abs(graph[p.ind1][p.ind2] - graph[ind1][ind2]);
                    int newEffort = Math.max(diff, p.steps);
                    if(newEffort < dist[ind1][ind2]){
                        dist[ind1][ind2] = newEffort;
                        q.add(new Pair(newEffort, ind1, ind2));
                    }
                }
            }
        }
    }
}
