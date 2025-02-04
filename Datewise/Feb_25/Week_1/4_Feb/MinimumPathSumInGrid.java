
import java.util.Arrays;

public class MinimumPathSumInGrid {
    // TC: O(2^n+m) // SC: O(n+m) ( recursion stack space )
    private static int minCostPathRecursion(int[][] grid, int row, int col) {
        if (row == 0 && col == 0)
            return grid[row][col];
        if (row < 0 || col < 0)
            return Integer.MAX_VALUE - 1000;

        int up = grid[row][col] + minCostPathRecursion(grid, row - 1, col);
        int left = grid[row][col] + minCostPathRecursion(grid, row, col - 1);


        return Math.min(up, left);
    }

    // TC: O(n*m) // SC: O(n*m) ( dp array ) + O(n+m) ( recursion stack space )
    private static int minCostPathRecursionDP(int[][] grid, int row, int col, int[][] dp) {
        if (row == 0 && col == 0)
            return grid[row][col];
        if (row < 0 || col < 0)
            return Integer.MAX_VALUE - 1000;

        if (dp[row][col] != -1)
            return dp[row][col];

        int up = grid[row][col] + minCostPathRecursionDP(grid, row - 1, col, dp);
        int left = grid[row][col] + minCostPathRecursionDP(grid, row, col - 1, dp);

        return dp[row][col] = Math.min(up, left);
    }

    // TC: O(n*m) // SC: O(n*m) ( dp array )
    private static int minCostPathTabulation(int[][] grid, int row, int col, int[][] dp) {
        dp[0][0] = grid[0][0];

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (i == 0 && j == 0)
                    continue;

                int up = grid[i][j];
                int left = grid[i][j];
                if (i > 0)
                    up += dp[i - 1][j];
                else
                    up += Integer.MAX_VALUE - 1000;
                if (j > 0)
                    left += dp[i][j - 1];
                else
                    left += Integer.MAX_VALUE - 1000;

                dp[i][j] = Math.min(up, left);
            }
        }
        return dp[row][col];
    }

    private static int minCostPathOptimal(int[][] grid, int row, int col) {
        int[] prev = new int[col];
        Arrays.fill(prev, 0);
        prev[0] = grid[0][0];

        for (int i = 0; i <= row; i++) {
            int[] temp = new int[col];
            for (int j = 0; j <= col; j++) {
                if (i == 0 && j == 0) {
                    temp[j] = grid[0][0];
                    continue;
                }
                int up = grid[i][j];
                int left = grid[i][j];
                if (i > 0)

                    up += prev[j];
                else
                    up += Integer.MAX_VALUE - 1000;
                if (j > 0)
                    left += temp[j - 1];
                else
                    left += Integer.MAX_VALUE - 1000;

                temp[j] = Math.min(up, left);
            }
            prev = Arrays.copyOf(temp, col);
        }
        return prev[col];
    }

    public static void main(String[] args) {
        int row = 3;
        int col = 3;

        int[][] grid = {
                { 4, 6, 8 },
                { 1, 2, 4 },
                { 8, 6, 3 }
        };

        System.out.println("Using Recursion: " + minCostPathRecursion(grid, row - 1, col - 1));

        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println("Using Recursion with DP: " + minCostPathRecursionDP(grid, row - 1, col - 1, dp));

        for (int i = 0; i < row; i++) {
            Arrays.fill(dp[i], 0);
        }

        System.out.println("Using Tabulation: " + minCostPathTabulation(grid, row - 1, col - 1, dp));

        System.out.println("Using Optimal: " + minCostPathOptimal(grid, row - 1, col - 1));
    }
}
