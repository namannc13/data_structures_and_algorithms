package BFS_or_DFS;
import java.util.ArrayList;
import java.util.HashSet;

// TC: O(N*M) (traversing through grid) + O(N*M) (total traversal by dfs) => O(N*M)
// SC: O(N*M) (vis[][] array) + O(N*M) (worst case recursion stack space) + O(N*M) (worst case hashset size) => O(N*M)

public class NoOfDistinctIslands {
    public static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public void Edit(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public void print(Pair p) {
            System.out.print("{a: " + p.a + ",b: " + p.b + "} ");
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                { '1', '1', '0', '1', '1' },
                { '1', '0', '0', '0', '0' },
                { '0', '0', '0', '1', '1' },
                { '1', '1', '0', '1', '0' }
        };

        System.out.println();
        System.out.println(numIslands(grid));
        System.out.println();
    }

    public static int numIslands(char[][] grid) {
        boolean[][] vis = new boolean[grid.length][grid[0].length];
        HashSet<ArrayList<String>> hs = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (vis[i][j] == false && grid[i][j] == '1') {
                    ArrayList<String> list = new ArrayList<>();
                    Pair base = new Pair(0, 0);
                    list.add(toString(base));
                    dfs(i, j, vis, grid, list, base);
                    hs.add(list);
                }
            }
        }

        System.out.println();

        return hs.size();
    }

    public static String toString(Pair p){
        return "a:" + p.a + ",b:" + p.b;
    }

    public static void dfs(int i, int j, boolean vis[][], char[][] grid, ArrayList<String> list, Pair base) {
        vis[i][j] = true;

        int[] rowDir = { -1, 1, 0, 0 };
        int[] colDir = { 0, 0, -1, 1 };

        for (int d = 0; d < 4; d++) {
            int ind1 = i + rowDir[d];
            int ind2 = j + colDir[d];
            if (ind1 >= 0 && ind1 < vis.length && ind2 >= 0 && ind2 < vis[0].length) {
                if (vis[ind1][ind2] == false && grid[ind1][ind2] == '1') {
                    Pair newbase = new Pair(base.a + rowDir[d], base.b + colDir[d]);
                    list.add(toString(newbase));
                    dfs(ind1, ind2, vis, grid, list, newbase);
                }
            }
        }
    }
}
