import java.util.*;

class Solution {

    /*
    ==================================================
    Problem: Longest Consecutive Sequence (LeetCode 128)
    ==================================================

    Given an unsorted array of integers nums.

    Return the length of the longest consecutive
    elements sequence.

    The algorithm must run in O(n) time.

    Example:

    nums = [100,4,200,1,3,2]

    Consecutive Sequence:

    [1,2,3,4]

    Answer:

    4
    */

    /*
    ==================================================
    Approach 1: Brute Force
    ==================================================

    Idea:

    For every element:

    Keep searching for:

    num + 1
    num + 2
    num + 3
    ...

    in the entire array.

    Example:

    nums = [100,4,200,1,3,2]

    Start from:

    1

    Find:

    2 -> Yes
    3 -> Yes
    4 -> Yes

    Length = 4

    --------------------------------------------------

    Time Complexity:

    For every element:

    Searching takes O(n)

    Therefore:

    O(n²)

    --------------------------------------------------

    Space Complexity:

    O(1)
    */

    public int longestConsecutiveBruteForce(int[] nums) {

        int longest = 0;

        for(int num : nums) {

            int current = num;
            int count = 1;

            while(linearSearch(nums, current + 1)) {

                current++;
                count++;
            }

            longest = Math.max(longest, count);
        }

        return longest;
    }

    private boolean linearSearch(int[] nums, int target) {

        for(int num : nums) {

            if(num == target) {
                return true;
            }
        }

        return false;
    }

    /*
    ==================================================
    Approach 2: Better Approach
    ==================================================

    Approach Name:
    Sorting

    Idea:

    Sort the array.

    Consecutive elements will become adjacent.

    Example:

    nums = [100,4,200,1,3,2]

    After Sorting:

    [1,2,3,4,100,200]

    Consecutive Sequence:

    1 -> 2 -> 3 -> 4

    Length = 4

    --------------------------------------------------

    Time Complexity:

    Sorting:

    O(n log n)

    Traversal:

    O(n)

    Total:

    O(n log n)

    --------------------------------------------------

    Space Complexity:

    O(1)

    Ignoring sorting space.
    */

    public int longestConsecutiveSorting(int[] nums) {

        if(nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longest = 1;
        int currentLength = 1;

        for(int i = 1; i < nums.length; i++) {

            if(nums[i] == nums[i - 1]) {
                continue;
            }

            if(nums[i] == nums[i - 1] + 1) {

                currentLength++;
            }
            else {

                longest =
                        Math.max(longest, currentLength);

                currentLength = 1;
            }
        }

        return Math.max(longest, currentLength);
    }

    /*
    ==================================================
    Optimized Approach
    ==================================================

    Approach Name:
    HashSet + Sequence Start Detection

    Your Approach
    --------------------------------------------------

    Step 1:

    Store all elements in a HashSet.

    This allows O(1) lookup.

    --------------------------------------------------

    Step 2:

    For every number:

    Check:

    num - 1

    If it exists,

    then current number is NOT the start
    of a sequence.

    Skip it.

    Example:

    Sequence:

    [1,2,3,4]

    For:

    2

    Since:

    1 exists

    We skip 2.

    Same for:

    3 and 4.

    --------------------------------------------------

    Step 3:

    Only start counting from sequence starts.

    Example:

    For:

    1

    Since:

    0 does not exist

    It is the sequence start.

    Now count:

    2 -> Yes
    3 -> Yes
    4 -> Yes

    Length = 4

    --------------------------------------------------

    Example Dry Run

    nums = [100,4,200,1,3,2]

    HashSet:

    {100,4,200,1,3,2}

    Start:

    100

    Length = 1

    Start:

    200

    Length = 1

    Start:

    1

    2 -> Yes
    3 -> Yes
    4 -> Yes

    Length = 4

    Answer:

    4

    --------------------------------------------------

    Why Is It O(n)?

    Every number is visited at most once
    while expanding sequences.

    No sequence is counted twice.

    --------------------------------------------------

    Time Complexity:

    O(n)

    --------------------------------------------------

    Space Complexity:

    O(n)

    For HashSet storage.

    --------------------------------------------------

    Why Optimal?

    - No sorting required.
    - Constant time lookup.
    - Meets problem requirement O(n).
    - Most expected interview solution.
    */

    public int longestConsecutive(int[] nums) {

        if(nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();

        for(int num : nums) {
            set.add(num);
        }

        int best = 0;

        for(int num : set) {

            if(!set.contains(num - 1)) {

                int currentLength = 1;
                int next = num + 1;

                while(set.contains(next)) {

                    currentLength++;
                    next++;
                }

                best =
                        Math.max(best, currentLength);
            }
        }

        return best;
    }
}
/*
Approach Summary
Approach	Idea	TC	SC
Brute Force	Linear search for every next element	O(n²)	O(1)
Sorting	Sort and count consecutive elements	O(n log n)	O(1)
HashSet (Your Approach)	Start only from sequence beginnings	O(n)	O(n)
  */
