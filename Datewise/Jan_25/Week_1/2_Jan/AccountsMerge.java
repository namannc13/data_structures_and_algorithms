import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TC: O(N^M * 4(alpha)) ( email to index + union by size ) + O(N) ( merged accounts initialization ) + O(Total emails * 4(alpha)) ( map traversal ) + O(merged accounts length * log (merged accounts length)) ( sorting ) + O(merged accounts length + total emails length) ( result formation )
// SC: O(N) ( parent + size ) + O(Total emails) ( map ) + O(merged accounts) ( merged accounts )

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

public class AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));

        System.out.println();
        for (List<String> result : accountsMerge(accounts)) {
            System.out.println(result);
        }
        System.out.println();
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<>();
        DisjointUnionSet dsu = new DisjointUnionSet(accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, i);
                } else {
                    int index = emailToIndex.get(email);
                    dsu.unionBySize(index, i);
                }
            }
        }
        @SuppressWarnings("unchecked")
        ArrayList<String>[] mergedAccounts = new ArrayList[accounts.size()];
        for(int i = 0; i < accounts.size(); i++){
            mergedAccounts[i] = new ArrayList<>();
        }

        for(Map.Entry<String, Integer> entry : emailToIndex.entrySet()){
            int parent = dsu.findParent(entry.getValue());
            mergedAccounts[parent].add(entry.getKey());
        }

        List<List<String>> result = new ArrayList<>();
        for(int i = 0; i < mergedAccounts.length; i++){
            if(mergedAccounts[i].size() > 0){
                Collections.sort(mergedAccounts[i]);
                List<String> temp = new ArrayList<>();
                temp.add(accounts.get(i).get(0));
                for(String email : mergedAccounts[i]){
                    temp.add(email);
                }
                result.add(temp);
            }
        }
        return result;
    }
}

