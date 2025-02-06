import java.util.Arrays;

public class Candy {
    // Naive Approach
    // TC: O(n+n+n) = O(3n) = O(n) // SC: O(n+n) = O(2n) = O(n)
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] leftToRight = new int[n];
        int[] rightToLeft = new int[n];

        Arrays.fill(leftToRight, 1);
        Arrays.fill(rightToLeft, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                leftToRight[i] = leftToRight[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rightToLeft[i] = rightToLeft[i + 1] + 1;
            }
        }

        int totalCandies = 0;
        for (int i = 0; i < n; i++) {
            totalCandies += Math.max(leftToRight[i], rightToLeft[i]);
        }
        return totalCandies;
    }

    // Better Approach
    // TC: O(n+n) = O(2n) = O(n) // SC: O(n)
    public static int candy2(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        int totalCandies = candies[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            totalCandies += candies[i];
        }
        return totalCandies;
    }

    public static void main(String[] args) {
        int[] ratings = { 1, 0, 2 };
        System.out.println(candy(ratings));
    }
}
