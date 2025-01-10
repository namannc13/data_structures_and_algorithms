import java.util.Arrays;
import java.util.PriorityQueue;

// TC: O(N^2(log(N))) ( O(log V) => O(log N^2) => O(N^2(log(N^2))) => O(N^2(log(N))) inserting/removing from pq for at most N^2 nodes + O(N^2) (neighbour checking))
// SC: O(N^2) ( pq + costArray )

public class MinimumCostToMakeAtLeastOneValidPath {
    static class Pair {
        int cost;
        int row;
        int col;

        public Pair(int cost, int row, int col) {
            this.cost = cost;
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        int[][] grid = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 1, 1, 1, 1 }, { 2, 2, 2, 2 } };

        System.out.println();
        System.out.println(minCost(grid));
        System.out.println();
    }

    public static int minCost(int[][] grid) {
        PriorityQueue<Pair> q = new PriorityQueue<>((p1, p2) -> {
            if (p1.cost == p2.cost) {
                if (p1.row == p2.row) {
                    return Integer.compare(p2.col, p1.col);
                }
                return Integer.compare(p2.row, p1.row);
            }
            return Integer.compare(p1.cost, p2.cost);
        });
        q.add(new Pair(0, 0, 0));

        int[][] costArray = new int[grid.length][grid[0].length];
        for (int[] arr : costArray) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        costArray[0][0] = 0;

        djikstra(grid, q, costArray);
        return costArray[grid.length-1][grid[0].length-1];
    }

    public static void djikstra(int[][] grid, PriorityQueue<Pair> q, int[][] costArray) {
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            if (pair.row == grid.length - 1 && pair.col == grid[0].length - 1)
                return;

            int[] rowDir = { 0, 0, 1, -1 };
            int[] colDir = { 1, -1, 0, 0 };
            int direction = grid[pair.row][pair.col];

            for (int d = 0; d < 4; d++) {
                int rowInd = pair.row + rowDir[d];
                int colInd = pair.col + colDir[d];
                if (rowInd >= 0 && rowInd < grid.length && colInd >= 0 && colInd < grid[0].length) {
                    if(d+1 == direction && costArray[rowInd][colInd] > pair.cost){
                        costArray[rowInd][colInd] = pair.cost;
                        q.add(new Pair(pair.cost, rowInd, colInd));
                    }
                    else if(costArray[rowInd][colInd] > pair.cost + 1) {
                        costArray[rowInd][colInd] = pair.cost + 1;
                        q.add(new Pair(pair.cost + 1, rowInd, colInd));
                    }
                }
            }
        }
    }
}
