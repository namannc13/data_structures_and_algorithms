import java.util.ArrayList;

// TC: O(stones.length) (getting row and col) + O(stones.length*(4*alpha)) (connecting rows/cols) + O(n+m) ( getting number of components ) => O(stones.length + n + m)
// SC: O(n+m) (parent and size)

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

public class MostStonesRemovedWithSameRowOrCol {
    public static void main(String[] args) {
        int[][] stones = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 } };
        int maxRow = 0, maxCol = 0;

        for (int[] stone : stones) {
            maxRow = Math.max(stone[0], maxRow);
            maxCol = Math.max(stone[1], maxCol);
        }

        int ans = StonesRemoved(stones, maxRow + 1, maxCol + 1);

        System.out.println();
        System.out.println(ans);
        System.out.println();
    }

    public static int StonesRemoved(int[][] stones, int n, int m) {
        DisjointUnionSet dsu = new DisjointUnionSet(n + m);

        for (int[] stone : stones) {
            int row = stone[0];
            int col = stone[1] + m + 1;

            if (!dsu.isConnected(row, col)) {
                dsu.unionBySize(row, col);
            }
        }

        ArrayList<Integer> list = new ArrayList<>(dsu.parent);
        ArrayList<Integer> list2 = new ArrayList<>(dsu.size);
        int components = 0;
        for(int i = 0; i < list.size(); i++){
            int num = list.get(i);
            if(num == i && list2.get(i) > 1) components++;
        }

        return stones.length - components;
    }
}