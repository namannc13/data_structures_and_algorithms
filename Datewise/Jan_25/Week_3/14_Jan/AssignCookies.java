import java.util.Arrays;

// TC: O(nlogn) + O(mlogm) + O(n+m)
// SC: O(1)

public class AssignCookies {
    public static void main(String[] args) {
        int[] humans = { 1, 2, 3 };
        int[] cookies = { 1, 1 };

        System.out.println();
        System.out.println(findContentChildren(humans, cookies));
        System.out.println();
    }

    public static int findContentChildren(int[] humans, int[] cookies) {
        int n = humans.length, m = cookies.length;
        Arrays.sort(humans);
        Arrays.sort(cookies);
        int it = 0;
        for (int j = 0; it < n && j < m; j++) {
            if (humans[it] <= cookies[j])
                it++;
        }
        return it;
    }
}