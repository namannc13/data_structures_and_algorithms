
import java.util.Arrays;

public class MaximumFallingPathSum {

    private static int maxFallingPathSumRecursionHelper(int[][] grid, int row, int col) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= col; i++) {
            max = Math.max(max, maxFallingPathSumRecursion(grid, row, i));
        }
        return max;
    }

    // TC: O(3^n) // SC: O(n)
    private static int maxFallingPathSumRecursion(int[][] grid, int row, int col) {
        if (col < 0 || col > grid.length - 1)
            return Integer.MIN_VALUE + 1000;
        if (row == 0)
            return grid[row][col];

        int up = grid[row][col] + maxFallingPathSumRecursion(grid, row - 1, col);
        int diagonalRight = grid[row][col] + maxFallingPathSumRecursion(grid, row - 1, col + 1);
        int diagonalLeft = grid[row][col] + maxFallingPathSumRecursion(grid, row - 1, col - 1);

        return Math.max(up, Math.max(diagonalLeft, diagonalRight));
    }

    private static int maxFallingPathSumRecursionDPHelper(int[][] grid, int row, int col, int[][] dp) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= col; i++) {
            max = Math.max(max, maxFallingPathSumRecursionDP(grid, row, i, dp));
        }
        return max;
    }

    // TC: O(n^2) // SC: O(n*m) + O(n)
    private static int maxFallingPathSumRecursionDP(int[][] grid, int row, int col, int[][] dp) {
        if (col < 0 || col > grid.length - 1)
            return Integer.MIN_VALUE + 1000;
        if (row == 0)
            return grid[row][col];

        if (dp[row][col] != -1)
            return dp[row][col];

        int up = grid[row][col] + maxFallingPathSumRecursionDP(grid, row - 1, col, dp);
        int diagonalRight = grid[row][col] + maxFallingPathSumRecursionDP(grid, row - 1, col + 1, dp);
        int diagonalLeft = grid[row][col] + maxFallingPathSumRecursionDP(grid, row - 1, col - 1, dp);

        return dp[row][col] = Math.max(up, Math.max(diagonalLeft, diagonalRight));
    }

    private static int maxFallingPathSumTabulationHelper(int[][] grid, int row, int col, int[][] dp) {
        int max = Integer.MIN_VALUE;
        maxFallingPathSumTabulation(grid, dp);
        for (int i = 0; i <= col; i++) {
            max = Math.max(max, dp[grid.length - 1][i]);
        }
        return max;
    }

    // TC: O(n^2) // SC: O(n*m)
    private static void maxFallingPathSumTabulation(int[][] grid, int[][] dp) {
        int noOfColumnsInGridFirstRow = grid[0].length;
        for (int i = 0; i < noOfColumnsInGridFirstRow; i++) {
            dp[0][i] = grid[0][i];
        }

        for (int i = 1; i <= grid.length - 1; i++) {
            for (int j = 0; j <= grid[i].length - 1; j++) {
                int up = grid[i][j];
                int diagonalRight = grid[i][j];
                int diagonalLeft = grid[i][j];

                if (i > 0)
                    up += dp[i - 1][j];
                else
                    up += Integer.MIN_VALUE + 1000;
                if (i > 0 && j < grid.length - 1)
                    diagonalRight += dp[i - 1][j + 1];
                else
                    diagonalRight += Integer.MIN_VALUE + 1000;
                if (i > 0 && j > 0)
                    diagonalLeft += dp[i - 1][j - 1];
                else
                    diagonalLeft += Integer.MIN_VALUE + 1000;

                dp[i][j] = Math.max(up, Math.max(diagonalLeft, diagonalRight));
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 1, 2, 3, 4 },
                { 2, 3, 4, 3 },
                { 3, 1, 1, 4 },
                { 4, 3, 1, 1 }
        };

        System.out.println(
                "Using Recursion: " + maxFallingPathSumRecursionHelper(grid, grid.length - 1, grid.length - 1));

        int[][] dp = new int[grid.length][grid.length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println("Using Recursion with DP: "
                + maxFallingPathSumRecursionDPHelper(grid, grid.length - 1, grid.length - 1, dp));

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 0);
        }

        System.out.println(
                "Using Tabulation: " + maxFallingPathSumTabulationHelper(grid, grid.length - 1, grid.length - 1, dp));

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }
}