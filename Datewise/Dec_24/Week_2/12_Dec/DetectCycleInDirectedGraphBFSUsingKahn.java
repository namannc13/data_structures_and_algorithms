import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Same as Kahns Algo / Using Kahns Algo
// TC: O( V + E ) ( directed graph bfs ) + O ( V + E ) ( filling the indegree array )
// SC: O(V) (indegree[] array) + O(V) (queue) + O(V) (list) => O(V)

public class DetectCycleInDirectedGraphBFSUsingKahn {
    public static void main(String[] args) {
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(2).add(3);
        adj.get(3).add(1);
        // adj.get(1).add(2);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        int[] indegree = new int[V];
        for(ArrayList<Integer> list: adj){
            for(Integer i: list){
                indegree[i] += 1;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0) q.add(i);
        }

        System.out.println(Arrays.toString(indegree));
        ArrayList<Integer> list = new ArrayList<>();
        bfs(adj, indegree, list, q);

        System.out.println();
        System.out.println(list);
        System.out.println();
        System.out.println(list.size() == V);
    }

    public static void bfs(ArrayList<ArrayList<Integer>> adj, int[] indegree, ArrayList<Integer> list, Queue<Integer> q){
        while(!q.isEmpty()){
            int node = q.poll();
            list.add(node);

            for(Integer it: adj.get(node)){
                indegree[it] -= 1;
                if(indegree[it] == 0) q.add(it);
            }
        }
    }
}
