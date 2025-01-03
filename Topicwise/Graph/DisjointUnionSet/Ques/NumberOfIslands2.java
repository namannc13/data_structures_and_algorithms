import java.util.ArrayList;

// TC: O(n*m*4*alpha*4*4*alpha) = O(n*m)
// SC: O(n*m)

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

public class NumberOfIslands2 {
    public static void main(String[] args) {
        int[][] positions = { { 0, 0 }, { 0, 0 }, { 1, 1 }, { 1, 0 }, { 0, 1 }, { 0, 3 }, { 1, 3 }, { 0, 4 }, { 3, 2 }, { 2, 2 }, { 1, 2 }, { 0, 2 } };
        int n = 4;
        int m = 5;

        System.out.println();
        System.out.println(numIslands(n, m, positions));
        System.out.println();
    }

    public static int numIslands(int n, int m, int[][] positions) {
        DisjointUnionSet dsu = new DisjointUnionSet(n * m);
        int[][] grid = new int[n][m];
        int count = 0;
        for (int[] position : positions) {
            int x = position[0];
            int y = position[1];
            int index = x * m + y;
            if (grid[x][y] == 1) {
                System.out.println("Already visited");
                continue;
            }
            grid[x][y] = 1;
            dsu.unionBySize(index, index);
            count++;
            int[] row = { -1, 0, 1, 0 };
            int[] col = { 0, 1, 0, -1 };
            for (int i = 0; i < 4; i++) {
                int newX = x + row[i];
                int newY = y + col[i];
                int newIndex = newX * m + newY;
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 1) {
                    if (!dsu.isConnected(index, newIndex)) {
                        dsu.unionBySize(index, newIndex);
                        count--;
                    }
                }
            }
            System.out.println(count);
        }
        return count;
    }
}

