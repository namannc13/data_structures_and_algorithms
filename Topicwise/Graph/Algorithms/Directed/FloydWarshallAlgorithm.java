import java.util.Arrays;

// TC: O(V^3)
// SC: O(V^2)

public class FloydWarshallAlgorithm {
    public static void main(String[] args) {
        int[][] array = { { 0, 5, Integer.MAX_VALUE, 10 },
                { Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0 } };

        int[][] dist = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                dist[i][j] = array[i][j];
            }
        }
        for (int via = 0; via < array.length; via++) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (dist[i][via] != Integer.MAX_VALUE && dist[via][j] != Integer.MAX_VALUE && dist[i][via] + dist[via][j] < dist[i][j]) {
                        dist[i][j] = dist[i][via] + dist[via][j];
                    }
                }
            }
        }
        System.out.println();
        for (int i = 0; i < dist.length; i++) {
            System.out.println(Arrays.toString(dist[i]));
        }
        System.out.println();

        for (int i = 0; i < array.length; i++) {
            if (dist[i][i] < 0) {
                System.out.println("Negative Cycle");
                return;
            }
        }
    }
}
