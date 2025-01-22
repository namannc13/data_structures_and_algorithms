public class HouseRobberII {
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

    private static int houseRobber2(int[] arr) {
        if (arr.length == 1)
            return arr[0];

        int[] arr2 = new int[arr.length - 1];
        int[] arr3 = new int[arr.length - 1];

        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr[i];
        }

        for (int i = 1; i <= arr2.length; i++) {
            arr3[i - 1] = arr[i];
        }

        int answer1 = maxSumWithNoAdjacentOptimal(arr2, arr2.length - 1);
        int answer2 = maxSumWithNoAdjacentOptimal(arr3, arr3.length - 1);

        return Math.max(answer1, answer2);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3 };
        int answer = houseRobber2(arr);
        System.out.println(answer);
    }
}
