
import java.util.Arrays;

public class UniquePaths {
    // TC: O(2^N+M) // SC: O(N+M) ( recursion stack space )
    public static int uniquePathsRecursion(int row, int col) {
        if (row < 0 || col < 0)
            return 0;
        if (row == 0 || col == 0)
            return 1;

        return uniquePathsRecursion(row - 1, col) + uniquePathsRecursion(row, col - 1);
    }

    // TC: O(N*M) // SC: O(N*M) ( dp array ) + O(N+M) ( recursion stack space )
    public static int uniquePathsRecursionDP(int row, int col, int[][] dp) {
        if (row < 0 || col < 0)
            return 0;
        if (row == 0 || col == 0)
            return 1;

        if (dp[row][col] != -1)
            return dp[row][col];

        return dp[row][col] = uniquePathsRecursionDP(row - 1, col, dp) + uniquePathsRecursionDP(row, col - 1, dp);
    }

    // TC: O(N*M) // SC: O(N*M) ( dp array )
    public static int uniquePathsTabulation(int row, int col, int[][] dp) {
        Arrays.fill(dp[0], 1);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0)
                    continue;
                int up = 0, left = 0;
                if (i > 0)
                    up = dp[i - 1][j];
                if (j > 0)
                    left = dp[i][j - 1];
                dp[i][j] = up + left;
            }
        }
        return dp[row - 1][col - 1];
    }

    // TC: O(N*M) // SC: O(M) ( prev array )
    public static int uniquePathsOptimal(int row, int col) {
        int[] prev = new int[col];
        Arrays.fill(prev, 1);
        for (int i = 1; i < row; i++) {
            int[] curr = new int[col];
            for (int j = 0; j < col; j++) {
                int up = 0;
                int left = 0;
                if (i > 0)
                    up = prev[j];
                if (j > 0)
                    left = curr[j - 1];

                curr[j] = up + left;
            }
            prev = Arrays.copyOf(curr, col);
        }
        return prev[col - 1];
    }

    public static void main(String[] args) {
        int row = 3, col = 7;
        System.out.println(uniquePathsRecursion(row - 1, col - 1));

        int[][] dp = new int[row][col];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(uniquePathsRecursionDP(row - 1, col - 1, dp));

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 0);
        }
        System.out.println(uniquePathsTabulation(row, col, dp));

        System.out.println(uniquePathsOptimal(row, col));
    }
}
