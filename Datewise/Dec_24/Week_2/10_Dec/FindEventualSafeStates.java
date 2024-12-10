import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TC: O(V+E) ( 'V' dfs calls and further child node calls in each of the dfs calls )
// SC: O(V) ( vis[] arr O(N) + recursion stack space O(N) + pathVis[] arr O(N) + ansList[] array)

public class FindEventualSafeStates {
    public static void main(String[] args) {
        int[][] graph = { { 0 }, { 2, 3, 4 }, { 3, 4 }, { 0, 4 }, {} };
        System.out.println(eventualSafeNodes3(graph));
    }

    // Best Approach
    // 1. Use the detect cycle in directed graph code and apply on this
    // 2. Everything same instead Whenever we find a cycle instantly return true and
    // do not change the pathVis[i] to false before going back ( will help )
    // 3. Keep track of all the nodes which is associated with cycle and mark them
    // in a boolean array when return back true when we found a cycle
    public static List<Integer> eventualSafeNodes3(int[][] graph) {
        boolean[] vis = new boolean[graph.length];
        boolean[] pathVis = new boolean[graph.length];
        boolean[] answer = new boolean[graph.length];
        Arrays.fill(answer, true);

        for (int i = 0; i < graph.length; i++) {
            if (!vis[i])
                detectCycleNodes3(vis, i, pathVis, graph, answer);
        }

        List<Integer> ansList = new ArrayList<>();
        for (int i = 0; i < answer.length; i++) {
            if (answer[i])
                ansList.add(i);
        }
        return ansList;
    }

    public static boolean detectCycleNodes3(boolean vis[], int node, boolean pathVis[], int[][] graph,
            boolean[] answer) {
        vis[node] = true;
        pathVis[node] = true;

        for (int it : graph[node]) {
            if (vis[it] == false) {
                if (detectCycleNodes3(vis, it, pathVis, graph, answer)) {
                    answer[node] = false;
                    return true;
                }
            } else {
                if (pathVis[it] == true) {
                    answer[node] = false;
                    return true;
                }
            }
        }

        pathVis[node] = false;
        return false;
    }

    // Better Approach - Too Slow
    // 1. All nodes involved in a cycle directly or indirectly cannot be a safe node
    // as the path of that node is never ending
    // 2. All nodes not a part of a cycle directly or indirectly are safe nodes as
    // they must end somewhere!! (have an ending path to terminal node)
    public static List<Integer> eventualSafeNodes2(int[][] graph) {
        boolean[] vis = new boolean[graph.length];
        boolean[] pathVis = new boolean[graph.length];
        boolean[] answer = new boolean[graph.length];
        Arrays.fill(answer, true);

        for (int i = 0; i < graph.length; i++) {
            if (!vis[i])
                detectCycleNodes2(vis, i, pathVis, graph, answer);
        }

        List<Integer> ansList = new ArrayList<>();
        for (int i = 0; i < answer.length; i++) {
            if (answer[i])
                ansList.add(i);
        }
        return ansList;
    }

    public static void detectCycleNodes2(boolean[] vis, int node, boolean pathVis[], int[][] graph, boolean[] answer) {
        System.out.println("node: " + node);
        vis[node] = true;
        pathVis[node] = true;

        for (int it : graph[node]) {
            System.out.println("it: " + it);
            if (vis[it] == false) {
                System.out.println("1");
                detectCycleNodes2(vis, it, pathVis, graph, answer);
            } else {
                System.out.println("2");
                if (pathVis[it] == true) {
                    System.out.println("21");
                    for (int i = 0; i < pathVis.length; i++) {
                        if (pathVis[i]) {
                            System.out.println("211 i: " + i);
                            answer[i] = false;
                            System.out.println(Arrays.toString(answer));
                        }
                    }
                } else {
                    System.out.println("22");
                    if (answer[it] == false) {
                        System.out.println("221 it: " + it);
                        for (int i = 0; i < pathVis.length; i++) {
                            if (pathVis[i]) {
                                answer[i] = false;
                            }
                        }
                        answer[node] = false;
                        break;
                    }
                }
            }
        }

        pathVis[node] = false;
    }

    // My Approach - TLE
    // Visit every node and find out whether they are a safe node or not
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        boolean[] pathVis = new boolean[graph.length];
        List<Integer> ansList = new ArrayList<>();
        boolean ans = true;

        for (int i = 0; i < graph.length; i++) {
            ans = detectCycleNodes(i, pathVis, graph);
            if (ans)
                ansList.add(i);
        }

        return ansList;
    }

    public static boolean detectCycleNodes(int node, boolean pathVis[], int[][] graph) {
        if (graph[node].length == 0)
            return true;
        pathVis[node] = true;
        boolean bool = true;

        for (int it : graph[node]) {
            if (pathVis[it] == true) {
                bool = false;
                break;
            } else {
                bool = bool && detectCycleNodes(it, pathVis, graph);
            }
        }

        pathVis[node] = false;
        return bool;
    }
}