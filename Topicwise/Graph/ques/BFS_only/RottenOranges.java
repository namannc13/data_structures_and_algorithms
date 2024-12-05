import java.util.LinkedList;
import java.util.Queue;

// BFS: We need to approach towards the neighbouring nodes simultaneously

// TC: O(N*M) (checking rotten oranges) + O(4*N*M) (queue worst case + 4 operations per element in q) => O(N*M)
// SC: O(N*M) (queue worst case) + O(N*M) (vis[][] array) => O(N*M)

public class RottenOranges {
    static class Pair {
        int i, j, time;

        public Pair(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        int[][] grid = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
        System.out.println();
        System.out.println(bfs(grid));
        System.out.println();
    }

    public static int bfs(int[][] grid) {
        int[][] vis = new int[grid.length][grid[0].length];
        Queue<Pair> q = new LinkedList<>();

        int cntfresh = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    vis[i][j] = 2;
                    q.add(new Pair(i, j, 0));
                } else if (grid[i][j] == 1)
                    cntfresh++;
            }
        }

        int ans = 0;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            ans = p.time;

            int[] rowDir = { -1, 1, 0, 0 };
            int[] colDir = { 0, 0, -1, 1 };

            for (int d = 0; d < 4; d++) {
                int ind1 = p.i + rowDir[d];
                int ind2 = p.j + colDir[d];
                if (ind1 >= 0 && ind1 < grid.length && ind2 >= 0 && ind2 < grid[0].length) {
                    if (vis[ind1][ind2] == 2)
                        continue;
                    if (grid[ind1][ind2] == 1) {
                        q.add(new Pair(ind1, ind2, p.time + 1));
                        cntfresh--;
                        vis[ind1][ind2] = 2;
                    }
                }
            }
        }

        return cntfresh == 0 ? ans : -1;
    }
}
