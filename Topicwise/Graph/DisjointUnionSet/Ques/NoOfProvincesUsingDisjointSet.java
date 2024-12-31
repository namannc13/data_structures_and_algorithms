import java.util.ArrayList;

// TC: O(N^2 * 4 * alpha ( 4*alpha or alpha*N is same and constant)) ( inserting in disjoint set) + O(N*alpha*N) ( finding parent ) = O(N^2 * alpha*N)
// SC: O(N) + O(N) = O(N)

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

public class NoOfProvincesUsingDisjointSet {
    public static void main(String[] args) {
        int[][] isConnected = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };

        System.out.println();
        System.out.println(findCircleNum(isConnected));
        System.out.println();
    }

    public static int findCircleNum(int[][] isConnected) {
            DisjointUnionSet dsu = new DisjointUnionSet(isConnected.length);

            for (int i = 0; i < isConnected.length; i++) {
                for (int j = 0; j < isConnected[i].length; j++) {
                    if (i != j && isConnected[i][j] != 0) {
                        dsu.unionBySize(i, j);
                    }
                }
            }

            int cnt = 0;
            for (int i = 0; i < isConnected.length; i++) {
                if (dsu.findParent(i) == i) {
                    cnt++;
                }
            }
            return cnt;
    }
}
