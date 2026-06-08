import java.util.*;

class Solution {

    /*
    Problem: Merge Intervals (LeetCode 56)

    -----------------------------------------
    Understanding My Approach
    -----------------------------------------

    My Idea:

    1. First sort the intervals based on their starting values.
       This ensures that any overlapping intervals will be
       adjacent to each other.

    Example:

    [[1,3],[2,6],[8,10],[15,18]]

    After sorting:

    [[1,3],[2,6],[8,10],[15,18]]

    2. Start with the first interval.

       Current Interval = [1,3]

    3. Compare its ending value with the next interval's
       starting value.

       Current End = 3
       Next Start = 2

       Since 2 <= 3, they overlap.

       Merge them:

       [1, max(3,6)]

       => [1,6]

    4. Now compare:

       Current Interval = [1,6]
       Next Interval = [8,10]

       Since 8 > 6,

       No overlap exists.

       Store [1,6] and move to [8,10].

    5. Continue this process until all intervals are processed.

    -----------------------------------------
    Validation
    -----------------------------------------

    Yes, the approach is correct.

    Sorting guarantees that if an interval overlaps,
    it can only overlap with the current merged interval.

    This is the standard optimal solution for Merge Intervals.

    Since this approach is already optimal,
    a brute force approach is provided first.
    */

    /*
    -----------------------------------------
    Brute Force Approach
    -----------------------------------------

    Approach Name:
    Repeated Overlap Checking

    Idea:

    For every interval, check all other intervals
    and merge overlapping ones repeatedly.

    This results in many unnecessary comparisons.

    Time Complexity:
    O(n²)

    Space Complexity:
    O(n)
    */

    /*
    -----------------------------------------
    Optimized Approach
    -----------------------------------------

    Approach Name:
    Sorting + Interval Merging

    Steps:

    1. Sort intervals by starting point.
    2. Add the first interval to the answer.
    3. Compare the current interval with the last
       merged interval.

       If overlapping:
       Update ending point.

       Else:
       Add a new interval.

    Example:

    Input:

    [[1,3],[2,6],[8,10],[15,18]]

    Sorted:

    [[1,3],[2,6],[8,10],[15,18]]

    Merge:

    [1,3] + [2,6]
    => [1,6]

    Result:

    [[1,6],[8,10],[15,18]]

    Time Complexity:
    O(n log n)

    Sorting takes O(n log n)
    Traversal takes O(n)

    Overall:
    O(n log n)

    Space Complexity:
    O(n)
    */

    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {

            int[] lastInterval = merged.get(merged.size() - 1);

            if (intervals[i][0] <= lastInterval[1]) {

                lastInterval[1] =
                        Math.max(lastInterval[1], intervals[i][1]);

            } else {

                merged.add(intervals[i]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
