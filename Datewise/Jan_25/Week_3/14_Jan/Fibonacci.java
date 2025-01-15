import java.util.Arrays;

public class Fibonacci {
    public static int fibonacciNumberRecursion(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciNumberRecursion(n - 1) + fibonacciNumberRecursion(n - 2);
    }

    // O(n) TC and O(n)+O(n) SC ( SC -> recursion stack memory and dp array )
    public static int fibonacciNumberDPMemoization(int n, int[] dp) {
        if (n <= 1)
            return n;
        if (dp[n] != -1)
            return dp[n];

        return dp[n] = fibonacciNumberDPMemoization(n - 1, dp) + fibonacciNumberDPMemoization(n - 2, dp);
    }

    // O(n) TC and O(n) SC
    public static int fibonacciNumberDPTabulation(int n, int[] dp) {
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // O(n) TC and O(1) SC
    public static int fibonacciNumberDPEvenBetter(int n) {
        int prev2 = 0, prev1 = 1, curi = prev1 + prev2;

        for (int i = 2; i <= n; i++) {
            curi = prev1 + prev2;
            prev2 = prev1;
            prev1 = curi;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println(fibonacciNumberRecursion(n));

        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        
        System.out.println(fibonacciNumberDPMemoization(n, dp));
        
        Arrays.fill(dp, 0);
        System.out.println(fibonacciNumberDPTabulation(n, dp));

        System.out.println(fibonacciNumberDPEvenBetter(n));
    }
}
