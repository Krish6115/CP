Problem Statement (Paraphrased)

You are given an array of meeting time intervals where each interval is represented as [start, end].

Determine the minimum number of meeting rooms required so that all meetings can take place without any overlaps.

Example 1

Input

[[0,30],[5,10],[15,20]]

Output

2

Explanation

Meeting [0,30] occupies one room.
Meeting [5,10] overlaps with it, so another room is needed.
Meeting [15,20] can reuse the room freed by [5,10].
Example 2

Input

[[7,10],[2,4]]

Output

1
Java Solution (Two Pointers)
import java.util.*;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;

        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int rooms = 0;
        int maxRooms = 0;

        int i = 0;
        int j = 0;

        while (i < n) {
            if (start[i] < end[j]) {
                rooms++;
                maxRooms = Math.max(maxRooms, rooms);
                i++;
            } else {
                rooms--;
                j++;
            }
        }

        return maxRooms;
    }
}
Time Complexity
O(n log n) (sorting)
Space Complexity
O(n)
Another Common Solution (Min Heap)

This version is also frequently asked in interviews.

import java.util.*;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!pq.isEmpty() && pq.peek() <= interval[0]) {
                pq.poll();
            }

            pq.offer(interval[1]);
        }

        return pq.size();
    }
}
