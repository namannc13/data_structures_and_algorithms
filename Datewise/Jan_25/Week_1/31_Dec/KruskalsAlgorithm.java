import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// TC: O(V + 2E) ( Traversing the graph and adding edges to the list (2E because bidirectional graph)) + O(2E log 2E) ( Sorting the edges ) + O(2E * 4 * alpha) ( Disjoint Union Set operations ) => O(V + E + E log E + E * 4 * alpha)
// SC: O(2E) ( edges list ) + O(V) ( parent and size arrays ) => O(V + E)

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

public class KruskalsAlgorithm {
    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;

        Edge(int weight, int src, int dest) {
            this.weight = weight;
            this.src = src;
            this.dest = dest;
        }

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(new ArrayList<>(new ArrayList<>(Arrays.asList(1, 10))));
        adj.get(0).add(new ArrayList<>(new ArrayList<>(Arrays.asList(2, 6))));
        adj.get(0).add(new ArrayList<>(new ArrayList<>(Arrays.asList(3, 5))));
        adj.get(1).add(new ArrayList<>(new ArrayList<>(Arrays.asList(0, 10))));
        adj.get(1).add(new ArrayList<>(new ArrayList<>(Arrays.asList(3, 15))));
        adj.get(2).add(new ArrayList<>(new ArrayList<>(Arrays.asList(0, 6))));
        adj.get(2).add(new ArrayList<>(new ArrayList<>(Arrays.asList(3, 4))));
        adj.get(3).add(new ArrayList<>(new ArrayList<>(Arrays.asList(0, 5))));
        adj.get(3).add(new ArrayList<>(new ArrayList<>(Arrays.asList(1, 15))));
        adj.get(3).add(new ArrayList<>(new ArrayList<>(Arrays.asList(2, 4))));

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                edges.add(new Edge(adj.get(i).get(j).get(1), i, adj.get(i).get(j).get(0)));
            }
        }

        Collections.sort(edges);

        int mstWeight = getMstWeight(edges, V);

        System.out.println();
        System.out.println(mstWeight);
        System.out.println();
    }

    public static int getMstWeight(ArrayList<Edge> edges, int V) {
        int mstWeight = 0;
        DisjointUnionSet dus = new DisjointUnionSet(V);

        for (int i = 0; i < edges.size(); i++) {
            if(!dus.isConnected(edges.get(i).src, edges.get(i).dest)){
                dus.unionBySize(edges.get(i).src, edges.get(i).dest);
                mstWeight += edges.get(i).weight;
            }
        }
        return mstWeight;
    }
}
