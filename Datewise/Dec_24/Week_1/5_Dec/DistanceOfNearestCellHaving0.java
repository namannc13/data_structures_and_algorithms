import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// TC: O(N*M) (traversing mat[][] array) + O(N*M) (each cell in bfs is accessed once) => O(N*M)
// SC: O(N*M) (bool array) + O(N*M) (queue worst case) + O(N*M) (ans array) => O(N*M)

public class DistanceOfNearestCellHaving0 {
    public static void main(String[] args) {
        int[][] mat = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
        int[][] ans = updateMatrix(mat);
        for (int[] arr : ans) {
            System.out.println(Arrays.toString(arr));
        }

        int[][] mat2 = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
        int[][] ans2 = updateMatrix2(mat2);
        for (int[] arr : ans2) {
            System.out.println(Arrays.toString(arr));
        }
    }

    // Better Approach ( visit the cells having 0 and apply bfs in order to find
    // non-0 cells and return answer )
    public static class Pair {
        int a;
        int b;
        int c;

        public Pair(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static int[][] updateMatrix(int[][] mat) {
        int[][] ans = new int[mat.length][mat[0].length];
        boolean[][] bool = new boolean[mat.length][mat[0].length];
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                if (mat[i][j] == 0) {
                    q.add(new Pair(i, j, 0));
                    bool[i][j] = true;
                }
            }
        }
        bfs(mat, ans, bool, q);
        return ans;
    }

    public static void bfs(int[][] mat, int[][] ans, boolean[][] bool, Queue<Pair> q) {

        int[] rowDir = { -1, 1, 0, 0 };
        int[] colDir = { 0, 0, -1, 1 };

        while (!q.isEmpty()) {
            Pair p = q.poll();
            ans[p.a][p.b] = p.c;

            for (int d = 0; d < 4; d++) {
                int ind1 = p.a + rowDir[d];
                int ind2 = p.b + colDir[d];
                if (ind1 >= 0 && ind1 < mat.length && ind2 >= 0 && ind2 < mat[0].length && bool[ind1][ind2] == false) {
                    bool[ind1][ind2] = true;
                    q.add(new Pair(ind1, ind2, p.c + 1));
                }
            }
        }
    }

    // My Approach - TLE ( visit every cell and apply bfs to find the nearest cell
    // having 0 for each one )
    public static class Pair2 {
        int a;
        int b;

        public Pair2(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static int[][] updateMatrix2(int[][] mat) {
        int[][] ans = new int[mat.length][mat[0].length];
        for (int[] arr : ans) {
            Arrays.fill(arr, -1);
        }
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                bfs2(mat, i, j, ans);
            }
        }
        return ans;
    }

    public static void bfs2(int[][] mat, int i, int j, int[][] ans) {
        if (mat[i][j] == 0) {
            ans[i][j] = 0;
            return;
        }
        Queue<Pair2> q = new LinkedList<>();
        q.add(new Pair2(i, j));

        while (!q.isEmpty()) {
            Pair2 p = q.poll();

            int[] rowDir = { -1, 1, 0, 0 };
            int[] colDir = { 0, 0, -1, 1 };

            for (int d = 0; d < 4; d++) {
                int ind1 = p.a + rowDir[d];
                int ind2 = p.b + colDir[d];
                if (ind1 >= 0 && ind1 < mat.length && ind2 >= 0 && ind2 < mat[0].length) {
                    if (mat[ind1][ind2] == 0) {
                        ans[i][j] = Math.abs(i - ind1) + Math.abs(j - ind2);
                        return;
                    } else {
                        q.add(new Pair2(ind1, ind2));
                    }
                }
            }
        }
    }
}
