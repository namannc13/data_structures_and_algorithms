package BFS_or_DFS.UsingTopoSortOrKahnAlgo;

import java.util.ArrayList;
import java.util.Stack;

// TC: O(V * L) (L is the avg string length, making the adj list) + O(V + E) (dfs) 
// SC: O(V) ( vis[] arr O(N) + recursion stack space O(N) + adj list O(N) + stack O(N) + ans list O(N)) => O(N)

public class AlienDictionary {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("baa");
        list.add("abcd");
        list.add("abca");
        list.add("cab");
        list.add("cad");
        int k = 4;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            adj.add(new ArrayList<>());
        }
        outer: for (int i = 0; i < list.size()-1; i++) {
            char[] arr1 = list.get(i).toCharArray();
            char[] arr2 = list.get(i + 1).toCharArray();

            int len = Math.min(arr1.length, arr2.length);
            for (int j = 0; j < len; j++) {
                if (arr1[j] != arr2[j]) {
                    int n1 = arr1[j] - 97;
                    int n2 = arr2[j] - 97;
                    adj.get(n1).add(n2);
                    continue outer;
                }
            }
        }

        boolean[] vis = new boolean[k];
        Stack<Integer> st = new Stack<>();
        ArrayList<Character> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (!vis[i])
                dfs(i, vis, adj, st);
        }

        for (int i = 0; i < k; i++) {
            int n = st.peek();
            ans.add((char)(n+97));
            st.pop();
        }
        System.out.println(ans);
    }

    public static void dfs(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = true;
        for (Integer it : adj.get(node)) {
            if (vis[it] == false) {
                dfs(it, vis, adj, st);
            }
        }
        st.push(node);
    }
}