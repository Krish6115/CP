import java.util.*;

class Solution {

    /*
    ==================================================
    Problem: Length of Longest Subarray With
             At Most K Frequency
    (LeetCode 2958)
    ==================================================

    Given an integer array nums and an integer k.

    Return the length of the longest subarray such that
    the frequency of every element is at most k.

    Example:

    nums = [1,2,3,1,2,3,1,2]
    k = 2

    Valid Longest Subarray:

    [1,2,3,1,2,3]

    Length = 6

    ==================================================
    Approach 1: Brute Force
    ==================================================

    Idea:

    Generate every possible subarray.

    For each subarray:

    Calculate frequencies.

    Check if every frequency <= k.

    If valid:

    Update maximum length.

    --------------------------------------------------

    Example:

    nums = [1,2,1,2,1]
    k = 2

    Check:

    [1]
    [1,2]
    [1,2,1]
    [1,2,1,2]
    [1,2,1,2,1]

    Last subarray invalid because:

    Frequency of 1 = 3

    --------------------------------------------------

    Time Complexity:
    O(n³)

    Generating subarrays -> O(n²)
    Frequency checking -> O(n)

    --------------------------------------------------

    Space Complexity:
    O(n)
    */

    public int maxSubarrayLengthBruteForce(int[] nums, int k) {

        int n = nums.length;
        int answer = 0;

        for(int i = 0; i < n; i++) {

            HashMap<Integer, Integer> map =
                    new HashMap<>();

            for(int j = i; j < n; j++) {

                map.put(nums[j],
                        map.getOrDefault(nums[j], 0) + 1);

                boolean valid = true;

                for(int freq : map.values()) {

                    if(freq > k) {
                        valid = false;
                        break;
                    }
                }

                if(valid) {
                    answer = Math.max(answer,
                                      j - i + 1);
                }
            }
        }

        return answer;
    }

    /*
    ==================================================
    Better Approach
    ==================================================

    Approach Name:
    Sliding Window + Frequency Map

    Idea:

    Maintain a window:

    [left ...... right]

    Store frequencies using HashMap.

    Expand right pointer.

    If any element frequency becomes > k:

    Shrink window from left side.

    Continue until frequency becomes valid.

    At every step:

    Window contains only valid elements.

    Therefore:

    answer = max(answer, window length)

    --------------------------------------------------

    Example:

    nums = [1,2,1,2,1]
    k = 2

    Window:

    [1]
    Length = 1

    [1,2]
    Length = 2

    [1,2,1]
    Length = 3

    [1,2,1,2]
    Length = 4

    [1,2,1,2,1]

    Frequency of 1 becomes 3

    Invalid

    Move left pointer

    Window:

    [2,1,2,1]

    Frequency of 1 becomes 2

    Valid again

    Length = 4

    --------------------------------------------------

    Why Only Check nums[right]?

    When we add nums[right]:

    Only its frequency increases.

    All other frequencies remain unchanged.

    Therefore only:

    mp.get(nums[right])

    can violate the condition.

    --------------------------------------------------

    Time Complexity:
    O(n)

    Each element enters the window once.

    Each element leaves the window once.

    Total:

    O(2n)

    ≈ O(n)

    --------------------------------------------------

    Space Complexity:
    O(n)

    HashMap stores frequencies.

    --------------------------------------------------

    Why Optimal?

    - Single traversal.
    - Sliding Window.
    - No unnecessary recalculations.
    - Interview expected solution.
    */

    public int maxSubarrayLength(int[] nums, int k) {

        int answer = 0;

        HashMap<Integer, Integer> frequency =
                new HashMap<>();

        int left = 0;

        for(int right = 0;
            right < nums.length;
            right++) {

            frequency.put(
                    nums[right],
                    frequency.getOrDefault(
                            nums[right], 0
                    ) + 1
            );

            while(frequency.get(nums[right]) > k) {

                frequency.put(
                        nums[left],
                        frequency.get(nums[left]) - 1
                );

                left++;
            }

            answer = Math.max(
                    answer,
                    right - left + 1
            );
        }

        return answer;
    }
}
/*
Approach Summary
Approach	Idea	TC	SC
Brute Force	Generate every subarray and check frequencies	O(n³)	O(n)
Sliding Window + HashMap (Your Approach)
Maintain valid window with frequency map
O(n)
O(n)
  */
