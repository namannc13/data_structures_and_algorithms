import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

// TO find the number of strongly connected components
// TC: O(V + E) (dfs) + O(V + E) (reverse graph) + O(V + E) (dfs2)
// SC: O(V + E) (adj) + O(V) (visited array) + O(V) (stack) + O(V + E) (reversed graph)

public class KosarajuAlgorithm {
    public static void main(String[] args) {
        int V = 7;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(1);
        adj.get(1).add(3);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(0);
        adj.get(4).add(5);
        adj.get(4).add(0);
        adj.get(5).add(6);
        adj.get(6).add(4);
        adj.get(6).add(1);

        Stack<Integer> ending = new Stack<>();
        int[] visited = new int[V];
        Arrays.fill(visited, 0);

        for(int i = 0; i < adj.size(); i++){
            if(visited[i] == 0) dfs(adj, ending, i, visited);
        }

        ArrayList<ArrayList<Integer>> reversedList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            reversedList.add(new ArrayList<>());
        }

        for(int i = 0; i < adj.size(); i++){
            for(Integer it: adj.get(i)){
                reversedList.get(it).add(i);
            }
        }

        int ans = NoOfConnectedComponents(reversedList, ending);

        System.out.println();
        System.out.println(ans);
        System.out.println();
    }
    public static int NoOfConnectedComponents(ArrayList<ArrayList<Integer>> reversedList, Stack<Integer> ending){
        int[] visited = new int[reversedList.size()];
        Arrays.fill(visited, 0);

        int count = 0;

        while(!ending.isEmpty()){
            int num = ending.peek();
            if(visited[num] == 0){
                dfs2(reversedList, num, visited);
                count++;
            }
            ending.pop();
        }
        return count;
    }
    public static void dfs2(ArrayList<ArrayList<Integer>> adj, int start, int[] visited){
        visited[start] = 1;
        for(Integer it: adj.get(start)){
            if(visited[it] == 0){
                dfs2(adj, it, visited);
            }
        }
    }
    public static void dfs(ArrayList<ArrayList<Integer>> adj, Stack<Integer> ending, int start, int[] visited){
        visited[start] = 1;
        for(Integer it: adj.get(start)){
            if(visited[it] == 0){
                dfs(adj, ending, it, visited);
            }
        }
        ending.push(start);
    }
}
