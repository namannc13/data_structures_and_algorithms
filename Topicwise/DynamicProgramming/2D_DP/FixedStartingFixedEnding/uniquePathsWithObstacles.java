import java.util.Arrays;

public class uniquePathsWithObstacles {
    // TC: O(2^N+M) // SC: O(N+M) ( recursion stack space )
    private static int uniquePathsWithObstaclesRecursion(int[][] grid, int row, int col) {
        if (row < 0 || col < 0) return 0;
        if (grid[row][col] == -1) return 0; 
        if (row == 0 && col == 0) return 1;

        int up = uniquePathsWithObstaclesRecursion(grid, row - 1, col);
        int left = uniquePathsWithObstaclesRecursion(grid, row, col - 1);

        return up + left;
    }

    // TC: O(N*M) // SC: O(N*M) ( dp array ) + O(N+M) ( recursion stack space )
    private static int uniquePathsWithObstaclesDP(int[][] grid, int row, int col, int[][] dp) {
        if (row < 0 || col < 0) return 0;
        if (grid[row][col] == -1) return 0; 
        if (row == 0 && col == 0) return 1;

        if (dp[row][col] != -1) return dp[row][col];

        int up = uniquePathsWithObstaclesDP(grid, row - 1, col, dp);
        int left = uniquePathsWithObstaclesDP(grid, row, col - 1, dp);

        return dp[row][col] = up + left;
    }

    // TC: O(N*M) // SC: O(N*M) ( dp array )
    private static int uniquePathsWithObstaclesTabulation(int[][] grid, int row, int col, int[][] dp) {
        // conditions for leetcode
        // case for 1 length grid
        if (grid.length == 1) {
            for (int i = 0; i < col; i++) {
                if (grid[0][i] == 1)
                    return 0;
            }
            return 1;
        }
        // case for when the starting index has the obstacle
        if (grid[0][0] == 1)
            return 0;
        // base case
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < row; i++) { 
            for (int j = 0; j < col; j++) { 

                if (i == 0 && j == 0) {
                    continue; 
                } else if (grid[i][j] == -1) { 
                    dp[i][j] = 0;
                } else {
                    int up = 0;
                    int left = 0;
                    if (i > 0)
                        up = dp[i - 1][j];
                    if (j > 0)
                        left = dp[i][j - 1];

                    dp[i][j] = up + left;
                }
            }
        }
        return dp[row-1][col-1];
    }

    public static void main(String[] args) {
        int row = 3;
        int col = 3;
        int[][] grid = new int[row][col];

        grid[1][1] = -1;
        System.out.println("Using Recursion: " + uniquePathsWithObstaclesRecursion(grid, row - 1, col - 1));

        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println("Using Recursion with DP: " + uniquePathsWithObstaclesDP(grid, row - 1, col - 1, dp));

        for (int i = 0; i < row; i++) {
            Arrays.fill(dp[i], 0);
        }

        System.out.println("Using Tabulation: " + uniquePathsWithObstaclesTabulation(grid, row, col, dp));
    }

}