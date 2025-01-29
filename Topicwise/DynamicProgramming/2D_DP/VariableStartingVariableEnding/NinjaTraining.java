import java.util.Arrays;

public class NinjaTraining {
    // TC: O((N-1)^N) // SC: O(N) ( recursive stack space )
    private static int ninjaTrainingRecursion(int[][] arr, int lastTask, int n) {
        if (n == 0) {
            int maxi = Integer.MIN_VALUE;
            for (int i = 0; i < arr[0].length; i++) {
                if (i != lastTask) {
                    maxi = Math.max(maxi, arr[0][i]);
                }
            }
            return maxi;
        }

        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < arr[n].length; i++) {
            if (i != lastTask) {
                int points = arr[n][i] + ninjaTrainingRecursion(arr, i, n - 1);
                maxi = Math.max(maxi, points);
            }
        }

        return maxi;
    }

    // TC: O(N*M*M) (for each N = number of days and each M = number of tasks, we are doing M operations ) // SC: O(N * M) ( dp array ) + O(N) ( recursive stack space )
    private static int ninjaTrainingRecursionDP(int[][] arr, int lastTask, int n, int[][] dp) {
        if (n == 0) {
            int maxi = Integer.MIN_VALUE;
            for (int i = 0; i < arr[0].length; i++) {
                if (i != lastTask) {
                    maxi = Math.max(maxi, arr[0][i]);
                }
            }
            return maxi;
        }

        if (dp[n][lastTask] != -1)
            return dp[n][lastTask];

        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i != lastTask) {
                int points = arr[n][i] + ninjaTrainingRecursionDP(arr, i, n - 1, dp);
                maxi = Math.max(maxi, points);
            }
        }

        return dp[n][lastTask] = maxi;
    }

    // TC: O(N*M) // SC: O(N*M) ( dp array )
    private static int ninjaTrainingTabulation(int[][] arr, int n, int[][] dp) {
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(Math.max(arr[0][0], arr[0][1]), arr[0][2]);

        for (int i = 1; i <= n; i++) {
            for (int last = 0; last < 4; last++) {
                dp[i][last] = 0;

                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int points = arr[i][task] + dp[i - 1][task];
                        dp[i][task] = Math.max(dp[i][task], points);
                    }
                }
            }
        }

        int ans = dp[n][0];
        for (int i = 0; i < dp[n].length; i++) {
            ans = Math.max(ans, dp[n][i]);
        }

        return ans;
    }

    // TC: O(N*M) // SC: O(M) ( prev array )
    private static int ninjaTrainingOptimal(int[][] arr, int n, int[] prev) {
        prev[0] = Math.max(arr[0][1], arr[0][2]);
        prev[1] = Math.max(arr[0][0], arr[0][2]);
        prev[2] = Math.max(arr[0][0], arr[0][1]);
        prev[3] = Math.max(Math.max(arr[0][0], arr[0][1]), arr[0][2]);

        for (int i = 1; i <= n; i++) {
            int[] temp = new int[prev.length];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0;

                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        temp[task] = Math.max(temp[task], arr[i][task] + prev[task]);
                    }
                }
            }
            prev = temp;
        }

        int ans = prev[0];
        for (int i = 0; i < prev.length; i++) {
            ans = Math.max(ans, prev[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] arr = {
                { 1, 2, 5 },
                { 3, 1, 1 },
                { 3, 3, 3 }
        };

        int maxEnergy = ninjaTrainingRecursion(arr, 3, arr.length - 1);

        System.out.println();
        System.out.println(maxEnergy);
        System.out.println();

        int[][] dp = new int[3][4];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println();
        System.out.println(ninjaTrainingRecursionDP(arr, 3, arr.length - 1, dp));
        System.out.println();

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println();
        System.out.println(ninjaTrainingTabulation(arr, arr.length - 1, dp));
        System.out.println();

        int[] dp2 = new int[4];
        Arrays.fill(dp2, -1);

        System.out.println();
        System.out.println(ninjaTrainingOptimal(arr, arr.length - 1, dp2));
        System.out.println();
    }
}