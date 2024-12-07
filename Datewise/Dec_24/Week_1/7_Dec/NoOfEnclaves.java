
// TC: O(2*N + 2*M) (traverse boundaries) + O(4(N*M)) (worst case dfs traversal, each element in matrix plus 4 operations) + O(N*M) (after dfs traversal to find whether any '1' in matrix is unvisited) => O(N*M)
// SC: O(N*M) (vis[][] array) + O(N*M) (recursion stack space) => O(N*M)

public class NoOfEnclaves {
    public static void main(String[] args) {
        int[][] grid = { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
        System.out.println();
        System.out.println(numEnclaves(grid));
        System.out.println();
    }

    public static int numEnclaves(int[][] grid) {
        return solve(grid);
    }

    public static int solve(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] vis = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            if (grid[i][0] == 1) {
                dfs(grid, vis, i, 0);
            }
            if (grid[i][col - 1] == 1) {
                dfs(grid, vis, i, col - 1);
            }
        }

        for (int j = 0; j < col; j++) {
            if (grid[0][j] == 1) {
                dfs(grid, vis, 0, j);
            }
            if (grid[row - 1][j] == 1) {
                dfs(grid, vis, row - 1, j);
            }
        }

        int cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && vis[i][j] == false) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void dfs(int[][] grid, boolean[][] vis, int i, int j) {
        if (vis[i][j] == true)
            return;
        vis[i][j] = true;

        int[] rowDir = { -1, 1, 0, 0 };
        int[] colDir = { 0, 0, -1, 1 };

        for (int d = 0; d < 4; d++) {
            int ind1 = i + rowDir[d];
            int ind2 = j + colDir[d];
            if (ind1 >= 0 && ind1 < grid.length && ind2 >= 0 && ind2 < grid[0].length) {
                if (grid[ind1][ind2] == 1) {
                    dfs(grid, vis, ind1, ind2);
                }
            }
        }
    }
}
