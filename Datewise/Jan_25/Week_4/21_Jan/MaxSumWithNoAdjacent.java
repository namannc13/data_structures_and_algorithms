// Also knows as the famous House Robber question

import java.util.Arrays;

public class MaxSumWithNoAdjacent {
    // TC - O(2^n) SC - O(n)
    public static int maxSumWithNoAdjacentRecursion(int[] arr, int n) {
        if (n == 0)
            return arr[n];
        if (n < 0)
            return 0;

        int pick = arr[n] + maxSumWithNoAdjacentRecursion(arr, n - 2);
        int notPick = 0 + maxSumWithNoAdjacentRecursion(arr, n - 1);

        return Math.max(pick, notPick);
    }

    // TC - O(n) SC - O(n) + O(n)
    public static int maxSumWithNoAdjacentDP(int[] arr, int n, int[] dp) {
        if (n == 0)
            return arr[n];
        if (n < 0)
            return 0;

        if (dp[n] != -1)
            return dp[n];

        int pick = arr[n] + maxSumWithNoAdjacentDP(arr, n - 2, dp);
        int notPick = 0 + maxSumWithNoAdjacentDP(arr, n - 1, dp);

        return dp[n] = Math.max(pick, notPick);
    }

    // TC - O(n) SC - O(n)
    public static int maxSumWithNoAdjacentTabulation(int[] arr, int n, int[] dp) {
        dp[0] = arr[0];

        for (int i = 1; i <= n; i++) {
            int notPick = 0 + dp[i - 1];
            int pick = 0;
            if ((i - 2) >= 0)
                pick = dp[i - 2] + arr[i];

            dp[i] = Math.max(pick, notPick);
        }

        return dp[n];
    }

    // TC - O(n) SC - O(1)
    public static int maxSumWithNoAdjacentOptimal(int[] arr, int n) {
        int prev2 = 0;
        int prev = arr[0];
        int curi = 0;

        for (int i = 1; i <= n; i++) {
            int notPick = 0 + prev;
            int pick = 0;
            if ((i - 2) >= 0)
                pick = prev2 + arr[i];

            curi = Math.max(pick, notPick);
            prev2 = prev;
            prev = curi;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 4, 9 };
        System.out.println(maxSumWithNoAdjacentRecursion(arr, arr.length - 1));
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, -1);
        System.out.println(maxSumWithNoAdjacentDP(arr, arr.length - 1, dp));
        Arrays.fill(dp, -1);
        System.out.println(maxSumWithNoAdjacentTabulation(arr, arr.length - 1, dp));
        System.out.println(maxSumWithNoAdjacentOptimal(arr, arr.length - 1));
    }
}
