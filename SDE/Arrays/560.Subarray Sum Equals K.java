import java.util.*;

class Solution {

    /*
    ==================================================
    Problem: Subarray Sum Equals K (LeetCode 560)
    ==================================================

    Given an integer array nums and an integer k.

    Return the total number of continuous subarrays
    whose sum equals k.

    Example:

    nums = [1,1,1]
    k = 2

    Subarrays:

    [1,1] -> Sum = 2
    [1,1] -> Sum = 2

    Answer:

    2

    ==================================================
    Approach 1: Brute Force
    ==================================================

    Idea:

    Generate every possible subarray.

    Calculate its sum.

    If sum == k:

    Increment count.

    --------------------------------------------------

    Example:

    nums = [1,1,1]

    Subarrays:

    [1]
    [1,1] -> Valid
    [1,1,1]

    [1]
    [1,1] -> Valid

    [1]

    Answer = 2

    --------------------------------------------------

    Time Complexity:
    O(n²)

    Space Complexity:
    O(1)
    */

    public int subarraySumBruteForce(int[] nums, int k) {

        int n = nums.length;
        int count = 0;

        for(int i = 0; i < n; i++) {

            int sum = 0;

            for(int j = i; j < n; j++) {

                sum += nums[j];

                if(sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    /*
    ==================================================
    Better Approach
    ==================================================

    Approach Name:
    Prefix Sum Array

    Idea:

    Build prefix sum array.

    prefix[i] =
    sum of elements from 0 to i.

    Then for every pair:

    prefix[j] - prefix[i-1]

    gives subarray sum.

    This reduces repeated summation.

    --------------------------------------------------

    Time Complexity:
    O(n²)

    Space Complexity:
    O(n)

    Still not enough for large inputs.
    */


    /*
    ==================================================
    Optimized Approach
    ==================================================

    Approach Name:
    Prefix Sum + HashMap

    Your Approach
    --------------------------------------------------

    Key Observation:

    Let:

    prefixSum = sum till current index

    Suppose:

    Current Prefix Sum = sum

    We need:

    Subarray Sum = k

    Therefore:

    sum - previousPrefix = k

    Rearranging:

    previousPrefix = sum - k

    So if:

    (sum - k)

    already appeared before,

    then a valid subarray exists.

    --------------------------------------------------

    Example:

    nums = [1,1,1]
    k = 2

    Prefix Sums:

    Index 0:

    sum = 1

    Need:

    sum-k = -1

    Not found

    Store:

    {0=1, 1=1}

    ----------------

    Index 1:

    sum = 2

    Need:

    sum-k = 0

    Found once

    count = 1

    Store:

    {0=1, 1=1, 2=1}

    ----------------

    Index 2:

    sum = 3

    Need:

    sum-k = 1

    Found once

    count = 2

    Answer = 2

    --------------------------------------------------

    Why freq.put(0,1)?

    Consider:

    nums = [2]
    k = 2

    Prefix Sum:

    sum = 2

    Need:

    sum-k = 0

    To count subarrays starting
    from index 0,

    we initialize:

    freq.put(0,1)

    --------------------------------------------------

    Why Store Frequencies?

    Multiple identical prefix sums
    can occur.

    Example:

    nums = [1,-1,1,-1]

    Prefix Sums:

    1,0,1,0

    When a prefix sum repeats,
    multiple valid subarrays can be formed.

    Therefore frequency must be stored.

    --------------------------------------------------

    Time Complexity:

    Single traversal

    O(n)

    --------------------------------------------------

    Space Complexity:

    HashMap stores prefix sums

    O(n)

    --------------------------------------------------

    Why Optimal?

    - Works with negative numbers.
    - Single traversal.
    - Uses prefix sum concept.
    - Interview expected solution.
    */

    public int subarraySum(int[] nums, int k) {

        int result = 0;

        HashMap<Integer, Integer> freq =
                new HashMap<>();

        freq.put(0, 1);

        int prefixSum = 0;

        for(int num : nums) {

            prefixSum += num;

            int required = prefixSum - k;

            if(freq.containsKey(required)) {

                result += freq.get(required);
            }

            freq.put(
                    prefixSum,
                    freq.getOrDefault(prefixSum, 0) + 1
            );
        }

        return result;
    }
}
