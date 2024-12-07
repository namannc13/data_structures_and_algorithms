
import java.util.Arrays;

// TC: O(N*M + 4(N*M)) => O(N*M) (checking every element in matrix in worst case and running a for loop ( 4 ) for each in dfs)
// SC: O(N*M) (if using other ans array) + O(N*M) (worst case recursion stack space) => O(N*M)

public class FloodFill {
    public static void main(String[] args) {
        int[][] image = {{1,1,1},{2,2,0},{2,2,2}};
        int sr = 2, sc = 0, color = 3;
        int[][] ans = floodFill(image, sr, sc, color);
        
        for(int[] arr: ans){
            System.out.println(Arrays.toString(arr));
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color)
            return image;
        dfs(sr, sc, image, image[sr][sc], color);
        return image;
    }

    public static void dfs(int i, int j, int[][] image, int target, int color) {
        image[i][j] = color;
        int[] rowDir = { -1, 1, 0, 0 };
        int[] colDir = { 0, 0, -1, 1 };

        for (int d = 0; d < 4; d++) {
            int ind1 = i + rowDir[d];
            int ind2 = j + colDir[d];
            if (ind1 >= 0 && ind1 < image.length && ind2 >= 0 && ind2 < image[0].length) {
                if (image[ind1][ind2] == target) {
                    dfs(ind1, ind2, image, target, color);
                }
            }
        }
    }
}
