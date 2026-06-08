import java.util.*;

class Solution {

    /*
    Problem: Find the Duplicate Number (LeetCode 287)

    -----------------------------------------
    My Approach
    -----------------------------------------

    Approach Name:
    Nested Loop Comparison

    Idea:

    Compare every element with every other element.
    If two elements are equal, return that element.

    Example:

    [1,3,4,2,2]

    Compare:
    1 with all elements
    3 with all elements
    4 with all elements
    2 with 2 -> Duplicate Found

    Return 2

    Time Complexity:
    O(n²)

    Space Complexity:
    O(1)
    */

    public int findDuplicateBruteForce(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }

        return -1;
    }

    /*
    -----------------------------------------
    Optimized Approach
    -----------------------------------------

    Approach Name:
    Floyd's Cycle Detection Algorithm
    (Tortoise and Hare)

    Idea:

    Treat the array as a linked list:

    index -> nums[index]

    Since one number is duplicated,
    a cycle is formed.

    Phase 1:
    Find the meeting point of slow and fast pointers.

    Phase 2:
    Move one pointer to the start.
    Move both one step at a time.

    The point where they meet again
    is the duplicate number.

    Example:

    nums = [1,3,4,2,2]

    0 -> 1 -> 3 -> 2 -> 4
                     ↑   ↓
                     ←←←←

    Duplicate = 2

    Time Complexity:
    O(n)

    Space Complexity:
    O(1)

    Why Optimal?

    - No extra space
    - No modification of array
    - Linear traversal
    */

    public int findDuplicate(int[] nums) {

        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        while (slow != fast);

        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
