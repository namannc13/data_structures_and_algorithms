import java.util.ArrayList;

// TC: O(N * 4 * alpha ( 4*alpha or alpha*N is same and constant)) ( inserting and checking in disjoint set ) + O( N*4*alpha ) ( finding parent ) = O(N * 4 * alpha)
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

public class NoOfOperationsToMakeNetworkConnected {
    public static void main(String[] args) {
        int n = 4;
        int[][] connections = { { 0, 1 }, { 0, 2 }, { 1, 2 } };

        System.out.println();
        System.out.println(makeConnected(n, connections));
        System.out.println();
    }

    public static int makeConnected(int n, int[][] connections) {
        DisjointUnionSet dsu = new DisjointUnionSet(n);

        int extraEdges = 0;
        for (int[] connection : connections) {
            if (dsu.isConnected(connection[0], connection[1])) {
                extraEdges++;
            } else {
                dsu.unionBySize(connection[0], connection[1]);
            }
        }

        int components = 0;
        for (int i = 0; i < n; i++) {
            if (dsu.findParent(i) == i) {
                components++;
            }
        }

        return extraEdges >= components - 1 ? components - 1 : -1;
    }
}
