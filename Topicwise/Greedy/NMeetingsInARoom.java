import java.util.ArrayList;
import java.util.Collections;

// TC: O(N) ( custom dsa ) + O(NlogN) ( sorting ) + O(N) ( for loop )
// SC: O(N) ( for storing the meetings ) + O(N) ( for storing the answer )

public class NMeetingsInARoom {
    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;
        int pos;
        Meeting(int start, int end, int pos) {
            this.start = start;
            this.end = end;
            this.pos = pos;
        }
        @Override
        public int compareTo(Meeting o) {
            return this.end - o.end;
        }
    }
    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        int n = start.length;

        System.out.println();
        System.out.println(maxMeetings(start, end, n));
        System.out.println();
    }
    public static int maxMeetings(int[] start, int[] end, int n) {
        ArrayList<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(start[i], end[i], i));
        }
        Collections.sort(meetings);

        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(meetings.get(0).pos);

        int lastEnd = meetings.get(0).end;
        for (int i = 1; i < n; i++) {
            if (meetings.get(i).start > lastEnd) {
                answer.add(meetings.get(i).pos);
                lastEnd = meetings.get(i).end;
            }
        }
        return answer.size();
    }
}