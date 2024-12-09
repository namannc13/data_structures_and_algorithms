package BFS_or_DFS;

// TC: O(M*N) (iteration through all cells + dfs visits each cell once)
// SC: O(M*N) (for the visited array + recursion stack)

public class NoOfIslands {
    public static void main(String[] args) {
        char[][] grid = {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' }
        };

        System.out.println();
        System.out.println(numIslands(grid));
        System.out.println();
    }

    public static int numIslands(char[][] grid) {
        boolean[][] vis = new boolean[grid.length][grid[0].length];
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (vis[i][j] == false && grid[i][j] == '1') {
                    dfs(i, j, vis, grid);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void dfs(int i, int j, boolean vis[][], char[][] grid) {
        vis[i][j] = true;

        int[] rowDir = { -1, 1, 0, 0 };
        int[] colDir = { 0, 0, -1, 1 };

        for (int d = 0; d < 4; d++) {
            int ind1 = i + rowDir[d];
            int ind2 = j + colDir[d];
            if (ind1 >= 0 && ind1 < vis.length && ind2 >= 0 && ind2 < vis[0].length) {
                if (vis[ind1][ind2] == false && grid[ind1][ind2] == '1') {
                    dfs(ind1, ind2, vis, grid);
                }
            }
        }
    }
}
