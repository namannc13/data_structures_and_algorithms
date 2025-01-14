import java.util.Arrays;
import java.util.Scanner;

public class ClimbingStairs {
    // TC: O(n) and SC: O(n)+O(n) // SC -> recursion stack memory and dp array
    public static int climbStairs(int n, int[] dp) {
        if (n == 0 || n == 1) return 1;
        if (dp[n] != -1) return dp[n];

        return dp[n] = climbStairs(n - 1, dp) + climbStairs(n - 2, dp);
    }

    // TC: O(n) // SC: O(n)
    public static int climbStairsTabulation(int n, int[] dp) {
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // TC: O(n) // SC: O(1)
    public static int climbStairsOptimal(int n) {
        int prev2 = 1;
        int prev1 = 1;
        int curi = prev1 + prev2;

        for (int i = 2; i <= n; i++) {
            curi = prev1 + prev2;
            prev2 = prev1;
            prev1 = curi;
        }
        return prev1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println();
        System.out.println(climbStairs(n,dp));
        System.out.println(climbStairsTabulation(n, dp));
        System.out.println(climbStairsOptimal(n));
        System.out.println();
        sc.close();
    }
}