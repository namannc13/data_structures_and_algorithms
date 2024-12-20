import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Was using PQ ( Djikstras ) but there is no need because the Queue ( steps parameter ) is already sorted
// TC: O(mod) (worst case every node in array will be accessed) + O(arr.length) (for each node, arr.length operations are done) 
// SC: O(mod) (q + dist)

public class MinimumMultiplicationsToReachEnd {
    static class Pair {
        int steps;
        int node;

        public Pair(int steps, int node) {
            this.node = node;
            this.steps = steps;
        }
    }

    public static void main(String[] args) {
        int arr[] = { 2 };
        int start = 1, end = 99999;
        int mod = 100000;

        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(0, start));

        int[] dist = new int[mod];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        bfs(q, arr, dist, end, mod);

        System.out.println();
        System.out.println(dist[end]);
        System.out.println();
    }

    public static void bfs(Queue<Pair> q, int[] arr, int[] dist, int end, int mod) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            System.out.println(p.steps + " " + p.node);
            if(p.node == end) return;

            for (int num : arr) {
                int multiplied = (p.node * num) % mod;
                if (p.steps + 1 < dist[multiplied]) {
                    dist[multiplied] = p.steps + 1;
                    q.add(new Pair(p.steps + 1, multiplied));
                }
            }
        }
    }
}