import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// TC: O(N) (WordList to HashSet) + O(N*(beginWord.length())*26) (bfs + internal for loops)
// SC: O(N) (HashSet) + O(N) (queue) + O(N) bfs => O(N)

public class WordLadderI {
    public static class Pair {
        String a;
        int b;

        public Pair(String a, int b) {
            this.a = a;
            this.b = b;
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

        System.out.println();
        System.out.println(ladderLength(beginWord, endWord, wordList));
        System.out.println();
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));
        wordSet.remove(beginWord);

        return bfs(q, endWord, wordSet, endWord.length());
    }

    public static int bfs(Queue<Pair> q, String endWord, HashSet<String> wordSet, int len) {
        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (p.a.equals(endWord))
                return p.b;

            for (int i = 0; i < len; i++) {
                char[] arr = p.a.toCharArray();
                for (int j = 0; j < 26; j++) {
                    arr[i] = (char) (j + 97);
                    String s = new String(arr);

                    if (wordSet.contains(s)) {
                        q.add(new Pair(s, p.b + 1));
                        wordSet.remove(s);
                    }
                }
            }
        }
        return 0;
    }
}
