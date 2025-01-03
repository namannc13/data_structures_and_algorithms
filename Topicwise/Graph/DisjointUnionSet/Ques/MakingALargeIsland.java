import java.util.ArrayList;
import java.util.HashSet;

// TC: O(N * M * 4 * alpha * 4 * 4 * alpha ) ( creating the dsu ) + O(N * M * 4 * 4 * alpha) ( finding the largest island ) => O(N * M)
// SC: O(N * M) ( grid ) + O ( N * M) (parent and size array) => O(N * M)

class DisjointUnionSet {
    ArrayList<Integer> parent;
    ArrayList<Integer> size;
    int n;

    public DisjointUnionSet(int n) {
        parent = new ArrayList<>(n + 1);
        size = new ArrayList<>(n + 1);
        this.n = n;
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findParent(int x) {
        if (parent.get(x) != x) {
            parent.set(x, findParent(parent.get(x)));
        }
        return parent.get(x);
    }

    public void unionBySize(int x, int y) {
        int xParent = findParent(x);
        int yParent = findParent(y);
        if (xParent != yParent) {
            if (size.get(xParent) < size.get(yParent)) {
                parent.set(xParent, yParent);
                size.set(yParent, size.get(xParent) + size.get(yParent));
            } else {
                parent.set(yParent, xParent);
                size.set(xParent, size.get(xParent) + size.get(yParent));
            }
        }
    }

    public boolean isConnected(int x, int y) {
        return findParent(x) == findParent(y);
    }
}

public class MakingALargeIsland {
    public static void main(String[] args) {
        int[][] grid = { { 1, 0 }, { 0, 1 } };

        System.out.println();
        System.out.println(largestIsland(grid));
        System.out.println();

    }

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        DisjointUnionSet dsu = new DisjointUnionSet(n * m);

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1) {
                    int index = row * m + col;
                    dsu.unionBySize(index, index);
                    int[] r = { -1, 0, 1, 0 };
                    int[] c = { 0, 1, 0, -1 };
                    for (int i = 0; i < 4; i++) {
                        int newX = row + r[i];
                        int newY = col + c[i];
                        if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 1) {
                            int newIndex = newX * m + newY;
                            if (!dsu.isConnected(index, newIndex)) {
                                dsu.unionBySize(index, newIndex);
                            }
                        }
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){
                if(grid[row][col] == 0){
                    int[] r = { -1, 0, 1, 0 };
                    int[] c = { 0, 1, 0, -1 };
                    int total = 0;
                    HashSet<Integer> parents = new HashSet<>();
                    for (int i = 0; i < 4; i++) {
                        int newX = row + r[i];
                        int newY = col + c[i];
                        if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 1) {
                            int newIndex = newX * m + newY;
                            int newParent = dsu.findParent(newIndex);
                            if(parents.contains(newParent)){
                                continue;
                            }
                            parents.add(newParent);
                            total += dsu.size.get(newParent);
                        }
                    }
                    max = Math.max(max, total + 1);
                }
            }
        }

        int alternate = Integer.MIN_VALUE;
        ArrayList<Integer> sizes = new ArrayList<>(dsu.size);
        for(int i = 0; i < sizes.size(); i++){
            alternate = Math.max(alternate, sizes.get(i));
        }
        return max == Integer.MIN_VALUE ? alternate : max;
    }
}
