import java.util.Arrays;

// TC: O(NlogN)
// SC: O(1)

public class ShortestJobFirst {
    public static void main(String[] args) {
        int[] time = { 4, 3, 7, 1, 2 };
        System.out.println(shortestJobFirst(time));
    }
    public static int shortestJobFirst(int[] time) {
        Arrays.sort(time);
        int t = 0;
        int wait = 0;
        for (int i = 0; i < time.length; i++) {
            wait += t;
            t += time[i];
        }
        return wait;
    }
}
