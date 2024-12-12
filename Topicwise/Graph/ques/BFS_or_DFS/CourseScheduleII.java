package BFS_or_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Same as Kahns Algo / Using Kahns Algo
// TC: O( V + E ) ( directed graph bfs ) + O ( V + E ) ( filling the indegree array )
// SC: O(V) (indegree[] array) + O(V) (queue) + O(V) (answer[] array) => O(V)

public class CourseScheduleII {
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println();
        System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
        System.out.println();
    }
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] arr : prerequisites) {
            adj.get(arr[0]).add(arr[1]);
        }
        int[] indegree = new int[numCourses];
        for(ArrayList<Integer> list: adj){
            for(Integer i: list){
                indegree[i] += 1;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0) q.add(i);
        }

        ArrayList<Integer> list = new ArrayList<>();
        bfs(adj, indegree, list, q);
        if(list.size() != numCourses) return new int[0];
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(list.size()-1-i);
        }
        return answer;
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
