package Topicwise.Graph.DisjointUnionSet;

import java.util.ArrayList;

// TC: O(4(alpha)) => O(1) ( unionByRank() || unionBySize() )
// SC: O(n) ( parent + rank or size )

public class DisjointUnionSet {
    ArrayList<Integer> parent;
    ArrayList<Integer> rank;
    ArrayList<Integer> size;
    int n;

    public DisjointUnionSet(int n) {
        parent = new ArrayList<>(n+1);
        rank = new ArrayList<>(n+1);
        size = new ArrayList<>(n+1);
        this.n = n;
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            rank.add(0);
            size.add(1);
        }
    }

    public int findParent(int x) {
        if (parent.get(x) != x) {
            parent.set(x, findParent(parent.get(x)));
        }
        return parent.get(x);
    }

    public void unionByRank(int x, int y) {
        int xParent = findParent(x);
        int yParent = findParent(y);
        if (xParent != yParent) {
            if (rank.get(xParent) < rank.get(yParent)) {
                parent.set(xParent, yParent);
            } else if (rank.get(xParent) > rank.get(yParent)) {
                parent.set(yParent, xParent);
            } else {
                parent.set(yParent, xParent);
                rank.set(xParent, rank.get(xParent) + 1);
            }
        }
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

    public static void main(String[] args) {
        DisjointUnionSet dus = new DisjointUnionSet(7);
        dus.unionBySize(1, 2);
        dus.unionBySize(2, 3);
        dus.unionBySize(4, 5);
        dus.unionBySize(5, 6);
        dus.unionBySize(6, 7);

        System.out.println();
        System.out.println(dus.isConnected(3, 7));

        dus.unionBySize(3, 7);
        System.out.println(dus.isConnected(3, 7));
        System.out.println();
    }
}
