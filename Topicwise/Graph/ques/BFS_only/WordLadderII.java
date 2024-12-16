import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// TC: Highly depends on the inputs so impossible to tell the TC

public class WordLadderII {
    public static class Pair {
        List<String> l;

        public Pair() {
            l = new ArrayList<>();
        }

        public Pair(List<String> list) {
            l = list;
        }

        public void add(String s) {
            l.add(s);
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        List<List<String>> list = ladderLength(beginWord, endWord, wordList);
        System.out.println(list);
    }

    public static List<List<String>> ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> list = new ArrayList<>();
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return list;
        }

        Queue<Pair> q = new LinkedList<>();
        Pair p = new Pair();
        p.add(beginWord);
        q.add(p);
        wordSet.remove(beginWord);

        bfs(q, endWord, wordSet, endWord.length(), list);
        return list;
    }

    public static void bfs(Queue<Pair> q, String endWord, HashSet<String> wordSet, int len, List<List<String>> list) {
        Set<String> remove = new HashSet<>();
        int maxSize = -1;
        while (!q.isEmpty()) {
            int size = q.size();
            remove.clear();
            for (int level = 0; level < size; level++) {
                Pair p = q.poll();

                if(p.l.size() > maxSize && maxSize != -1) return;
                String ans = p.l.get(p.l.size() - 1);
                if (ans.equals(endWord)) {
                    list.add(new ArrayList<>(p.l));
                    maxSize = p.l.size();
                }
                for (int i = 0; i < len; i++) {
                    char[] arr = p.l.get(p.l.size() - 1).toCharArray();
                    for (int j = 0; j < 26; j++) {
                        arr[i] = (char) (j + 97);
                        String s = new String(arr);

                        if (wordSet.contains(s)) {
                            Pair p2 = new Pair(new ArrayList<>(p.l));
                            p2.add(s);
                            q.add(p2);
                            remove.add(s);
                        }
                    }
                }
            }
            wordSet.removeAll(remove);
        }
    }
}
