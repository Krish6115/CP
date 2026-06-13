import java.util.*;

class Solution {

    /*
    ==================================================
    Problem: Reverse Pairs (LeetCode 493)
    ==================================================

    Given an integer array nums.

    Return the number of reverse pairs.

    A reverse pair is:

    i < j

    and

    nums[i] > 2 * nums[j]

    Example:

    nums = [1,3,2,3,1]

    Reverse Pairs:

    (3,1)
    (3,1)

    Answer = 2
    */

    /*
    ==================================================
    Approach 1: Brute Force
    ==================================================

    Idea:

    Check every possible pair.

    For each pair:

    if(nums[i] > 2 * nums[j])

    increment answer.

    Example:

    nums = [1,3,2,3,1]

    Check:

    (1,3)
    (1,2)
    (1,3)
    (1,1)

    ...

    Reverse Pairs Found:

    (3,1)
    (3,1)

    Answer = 2

    Time Complexity:
    O(n²)

    Space Complexity:
    O(1)
    */

    public int reversePairsBruteForce(int[] nums) {

        int n = nums.length;
        int count = 0;

        for(int i = 0; i < n; i++) {

            for(int j = i + 1; j < n; j++) {

                if((long) nums[i] > 2L * nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    /*
    ==================================================
    Approach 2: Better Approach
    ==================================================

    Approach Name:
    Binary Indexed Tree (Fenwick Tree)

    Idea:

    As we traverse the array,
    we need to know how many previous
    numbers are greater than:

    2 * nums[i]

    We use:

    1. Coordinate Compression
    2. Fenwick Tree

    Operations:

    - Query count of numbers > 2*nums[i]
    - Insert nums[i]

    Both operations take:

    O(log n)

    Time Complexity:
    O(n log n)

    Space Complexity:
    O(n)

    Note:

    This approach is faster than brute force
    but implementation is complex.

    Therefore Merge Sort is generally preferred
    in interviews.
    */


    /*
    ==================================================
    Approach 3: Optimized Approach
    ==================================================

    Approach Name:
    Modified Merge Sort

    Idea:

    Similar to Count Inversions.

    During Merge Sort:

    Left Half -> Sorted
    Right Half -> Sorted

    Before merging:

    Count pairs satisfying:

    left[i] > 2 * right[j]

    Since both halves are sorted:

    We can use two pointers.

    --------------------------------------------------

    Example:

    Left:

    [1,3,5]

    Right:

    [1,2]

    For each element in Left:

    Find how many elements in Right satisfy:

    left[i] > 2 * right[j]

    Since arrays are sorted,
    pointer never moves backward.

    This makes counting O(n).

    --------------------------------------------------

    Your Approach

    You recursively divide the array.

    Every recursive call returns
    a sorted array.

    Then:

    Step 1:
    Count reverse pairs between
    left and right halves.

    Step 2:
    Merge both sorted halves.

    Step 3:
    Return merged sorted array.

    Global variable:

    ans

    stores total reverse pairs.

    --------------------------------------------------

    Dry Run

    nums = [1,3,2,3,1]

    Divide:

    [1,3,2]
    [3,1]

    Further Divide:

    [1]
    [3,2]

    ...

    While merging:

    Count:

    left[i] > 2 * right[j]

    Reverse Pairs Found:

    (3,1)
    (3,1)

    Answer = 2

    --------------------------------------------------

    Why Long?

    We use:

    2L * right[j]

    because:

    nums[i] can be close to Integer.MAX_VALUE

    Example:

    nums[i] = 2147483647

    Then:

    2 * nums[i]

    overflows int.

    Therefore:

    2L * nums[i]

    is required.

    --------------------------------------------------

    Time Complexity:

    Merge Sort Levels:

    O(log n)

    Work Per Level:

    O(n)

    Overall:

    O(n log n)

    --------------------------------------------------

    Space Complexity:

    Temporary arrays are created
    during merging.

    O(n)

    --------------------------------------------------

    Why Optimal?

    - Faster than O(n²)
    - Interview Standard Solution
    - Uses Divide and Conquer
    - Efficient Pair Counting
    */

    int ans = 0;

    public int reversePairs(int[] nums) {

        int n = nums.length;

        reversePairs(nums, 0, n);

        return ans;
    }

    public int[] reversePairs(int[] nums,
                              int start,
                              int end) {

        if(end - start == 1) {
            return new int[]{nums[start]};
        }

        int mid = (start + end) / 2;

        int[] left =
                reversePairs(nums, start, mid);

        int[] right =
                reversePairs(nums, mid, end);

        int m = left.length;
        int n = right.length;

        int p1 = 0;
        int p2 = 0;

        int count = 0;

        /*
        Count Reverse Pairs
        */

        while(p1 < m && p2 < n) {

            if(left[p1] > 2L * right[p2]) {

                count += (m - p1);

                p2++;
            }
            else {

                p1++;
            }
        }

        ans += count;

        /*
        Merge Step
        */

        int[] sorted =
                new int[end - start];

        p1 = 0;
        p2 = 0;

        int idx = 0;

        while(p1 < m && p2 < n) {

            if(left[p1] <= right[p2]) {

                sorted[idx++] = left[p1++];
            }
            else {

                sorted[idx++] = right[p2++];
            }
        }

        while(p1 < m) {

            sorted[idx++] = left[p1++];
        }

        while(p2 < n) {

            sorted[idx++] = right[p2++];
        }

        return sorted;
    }
}
/*
Approach Summary
Approach	Idea	TC	SC
Brute Force	Check every pair (i,j)	O(n²)	O(1)
BIT / Fenwick Tree	Query counts using compressed indices	O(n log n)	O(n)
Modified Merge Sort (Your Approach)	Count reverse pairs while merging sorted halves	O(n log n)	O(n) */
