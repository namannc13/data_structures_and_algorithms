import java.util.Arrays;

public class MinimumNoOfPlatformsRequired {
    public static void main(String[] args) {
        int[] arr = { 1,2,3,1 };
        int[] dep = { 2,3,4,3 };
        System.out.println();
        System.out.println(findPlatform(arr, dep, 4));
        System.out.println();
    }

    // TC: O(NlogN) + O(NlogN) + O(N)
    // SC: O(1)
    public static int findPlatform(int[] arr, int[] dep, int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0, j = 0;
        
        int platform = 0;
        int result = 0;
        while (i < n && j < n) {
            if (arr[i] < dep[j]) {
                platform++;
                i++;
            } else {
                platform--;
                j++;
            }
            result = Math.max(result, platform);
        }
        return result;
    }
}
