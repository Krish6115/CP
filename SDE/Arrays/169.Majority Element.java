import java.util.*;

class Solution {

    /*
    Problem: Majority Element (LeetCode 169)

    Given an array of size n,
    return the element that appears
    more than n/2 times.

    It is guaranteed that the majority
    element always exists.

    ==================================================
    Approach 1: Brute Force
    ==================================================

    Idea:

    For every element,
    count its frequency by traversing
    the entire array again.

    If frequency > n/2,
    return that element.

    Example:

    nums = [2,2,1,1,1,2,2]

    Count frequency of:

    2 -> 4

    Since:

    4 > 7/2

    Answer = 2

    Time Complexity:
    O(n²)

    Space Complexity:
    O(1)
    */

    public int majorityElementBruteForce(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            int count = 0;

            for (int j = 0; j < n; j++) {

                if (nums[i] == nums[j]) {
                    count++;
                }
            }

            if (count > n / 2) {
                return nums[i];
            }
        }

        return -1;
    }

    /*
    ==================================================
    Approach 2: HashMap
    ==================================================

    Idea:

    Store frequency of every element.

    If frequency becomes greater than n/2,
    that element is the majority element.

    Example:

    nums = [2,2,1,1,1,2,2]

    Frequency Map:

    2 -> 4
    1 -> 3

    Answer = 2

    Time Complexity:
    O(n)

    Space Complexity:
    O(n)
    */

    public int majorityElementHashMap(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int answer = 0;
        int maxFreq = 0;

        for (int num : nums) {

            map.put(num, map.getOrDefault(num, 0) + 1);

            if (map.get(num) > maxFreq) {

                maxFreq = map.get(num);
                answer = num;
            }
        }

        return answer;
    }

    /*
    ==================================================
    Approach 3: Sorting
    ==================================================

    Idea:

    If an element appears more than n/2 times,
    then after sorting it must occupy the middle
    position of the array.

    Example:

    nums = [2,2,1,1,1,2,2]

    After Sorting:

    [1,1,1,2,2,2,2]

             ↑
           n/2

    Answer = nums[n/2]

    Time Complexity:
    O(n log n)

    Space Complexity:
    O(1)
    */

    public int majorityElementSorting(int[] nums) {

        Arrays.sort(nums);

        return nums[nums.length / 2];
    }

    /*
    ==================================================
    Optimized Approach
    ==================================================

    Approach Name:
    Moore's Voting Algorithm

    Idea:

    Since majority element appears more than n/2 times,

    It can never be completely cancelled out
    by all other elements.

    Maintain:

    candidate
    count

    Rules:

    If count becomes 0:
    Choose current element as candidate.

    If current element equals candidate:
    count++

    Else:
    count--

    At the end,
    candidate will be the majority element.

    Example:

    nums = [2,2,1,1,1,2,2]

    candidate = 2
    count = 1

    2 -> count = 2
    1 -> count = 1
    1 -> count = 0

    New candidate = 1

    1 -> count = 1
    2 -> count = 0

    New candidate = 2

    Final Answer = 2

    Why It Works?

    Majority element occurs more than n/2 times.

    Therefore it survives all cancellations.

    Time Complexity:
    O(n)

    Space Complexity:
    O(1)

    Why Optimal?

    - Single traversal
    - No extra space
    - Best possible solution
    */

    public int majorityElement(int[] nums) {

        Integer candidate = null;
        int count = 0;

        for (int num : nums) {

            if (count == 0) {
                candidate = num;
            }

            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
