import java.util.*;

class Solution {

    /*
    ==================================================
    Problem: Two Sum (LeetCode 1)
    ==================================================

    Understanding My Approach
    ==================================================

    My Idea:

    Instead of checking:

    arr[i] + arr[j]

    directly,

    I used:

    arr[j-i] + arr[j]

    Example:

    nums = [2,7,11,15]
    target = 9

    i = 1
    j = 1

    arr[j-i] + arr[j]

    arr[0] + arr[1]

    2 + 7 = 9

    Answer:

    [0,1]

    --------------------------------------------------

    Validation
    --------------------------------------------------

    Your approach is CORRECT.

    It checks every possible pair exactly once.

    Reason:

    j-i generates all indices smaller than j.

    Example:

    j = 3

    i = 1 => compare index 2 with 3

    i = 2 => compare index 1 with 3

    i = 3 => compare index 0 with 3

    Thus all pairs are covered.

    However, this is still a Brute Force solution.

    Time Complexity:
    O(n²)

    Space Complexity:
    O(1)
    */

    public int[] twoSumBruteForce(int[] arr, int target) {

        for(int i = 1; i < arr.length; i++) {

            for(int j = i; j < arr.length; j++) {

                if(arr[j - i] + arr[j] == target) {

                    return new int[]{j - i, j};
                }
            }
        }

        return new int[]{};
    }

    /*
    ==================================================
    Better Approach
    ==================================================

    Approach Name:
    Sorting + Two Pointers

    Idea:

    Store:

    value + original index

    Sort by value.

    Use two pointers:

    left = 0
    right = n-1

    If sum < target:

    left++

    If sum > target:

    right--

    Else:

    Return original indices.

    Time Complexity:
    O(n log n)

    Space Complexity:
    O(n)
    */

    /*
    ==================================================
    Optimized Approach
    ==================================================

    Approach Name:
    HashMap

    Idea:

    For every number:

    complement = target - nums[i]

    Check whether complement
    already exists in HashMap.

    If yes:

    Return both indices.

    Otherwise:

    Store current number and index.

    Example:

    nums = [2,7,11,15]
    target = 9

    i = 0

    Store:

    {2 -> 0}

    ----------------

    i = 1

    complement = 9 - 7

    complement = 2

    Found in HashMap.

    Answer:

    [0,1]

    --------------------------------------------------

    Time Complexity:
    O(n)

    Space Complexity:
    O(n)

    Why Optimal?

    - Single traversal
    - Constant-time lookup
    - No sorting required
    - Interview expected solution
    */

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if(map.containsKey(complement)) {

                return new int[]{
                    map.get(complement),
                    i
                };
            }

            map.put(nums[i], i);
        }

        return new int[]{};
    }
}

/*
Your Approach Analysis

For:

for(int i=1 ; i<arr.length ; i++){
    for(int j=i ; j<arr.length ; j++){
        if(arr[j-i]+arr[j]==target){
            return new int[]{j-i,j};
        }
    }
}

You discovered a different way of generating all valid pairs:

j=1 → (0,1)

j=2 → (1,2), (0,2)

j=3 → (2,3), (1,3), (0,3)

So every pair is checked exactly once.

TC: O(n²)
SC: O(1)

It's a valid brute-force variation, though interviewers usually expect the more readable version:

for(int i=0;i<n;i++){
    for(int j=i+1;j<n;j++){
        ...
    }
}

because it's easier to understand immediately.
*/
