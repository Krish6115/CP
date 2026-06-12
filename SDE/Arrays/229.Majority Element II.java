import java.util.*;

class Solution {

    /*
    Problem: Majority Element II (LeetCode 229)

    Given an integer array of size n,
    return all elements that appear more than n/3 times.

    Observation:

    There can be at most 2 elements that appear
    more than n/3 times.

    Why?

    Assume 3 different elements each appear
    more than n/3 times.

    Total occurrences would exceed n,
    which is impossible.

    Therefore:

    Maximum possible majority elements = 2

    ==================================================
    Approach 1: Brute Force
    ==================================================

    Idea:

    For every element,
    count its frequency in the entire array.

    If frequency > n/3,
    add it to the answer.

    Avoid duplicates while adding.

    Time Complexity:
    O(n²)

    Space Complexity:
    O(1)
    */

    public List<Integer> majorityElementBruteForce(int[] nums) {

        List<Integer> ans = new ArrayList<>();

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            if (ans.contains(nums[i])) {
                continue;
            }

            int count = 0;

            for (int j = 0; j < n; j++) {

                if (nums[j] == nums[i]) {
                    count++;
                }
            }

            if (count > n / 3) {
                ans.add(nums[i]);
            }
        }

        return ans;
    }

    /*
    ==================================================
    Approach 2: HashMap
    ==================================================

    Idea:

    Store frequency of each element
    using a HashMap.

    Traverse the map and collect elements
    whose frequency is greater than n/3.

    Time Complexity:
    O(n)

    Space Complexity:
    O(n)
    */

    public List<Integer> majorityElementHashMap(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        List<Integer> ans = new ArrayList<>();

        int n = nums.length;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int key : map.keySet()) {

            if (map.get(key) > n / 3) {
                ans.add(key);
            }
        }

        return ans;
    }

    /*
    ==================================================
    Optimized Approach
    ==================================================

    Approach Name:
    Extended Boyer-Moore Voting Algorithm

    Idea:

    For Majority Element I (> n/2),

    there can only be one majority candidate.

    For Majority Element II (> n/3),

    there can be at most two majority candidates.

    Therefore maintain:

    el1, cnt1
    el2, cnt2

    Whenever we find a new number:

    Case 1:
    cnt1 == 0

    Make it candidate 1.

    Case 2:
    cnt2 == 0

    Make it candidate 2.

    Case 3:
    Number equals candidate 1

    cnt1++

    Case 4:
    Number equals candidate 2

    cnt2++

    Case 5:
    Different from both candidates

    cnt1--
    cnt2--

    After first traversal:

    We get potential candidates.

    Since Boyer-Moore only finds candidates,
    a second pass is required to verify them.

    Example:

    nums = [3,2,3]

    Candidate = 3

    Frequency = 2

    n/3 = 1

    Answer = [3]

    Example:

    nums = [1,1,1,3,3,2,2,2]

    Candidate1 = 1
    Candidate2 = 2

    Frequencies:

    1 -> 3
    2 -> 3

    n/3 = 2

    Answer = [1,2]

    Time Complexity:
    O(n)

    First pass  -> O(n)
    Second pass -> O(n)

    Overall:
    O(n)

    Space Complexity:
    O(1)

    Why Optimal?

    - Linear traversal
    - Constant extra space
    - No HashMap required
    - Interview expected solution
    */

    public List<Integer> majorityElement(int[] nums) {

        int n = nums.length;

        int cnt1 = 0;
        int cnt2 = 0;

        int el1 = Integer.MIN_VALUE;
        int el2 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {

            if (cnt1 == 0 && el2 != nums[i]) {

                cnt1 = 1;
                el1 = nums[i];
            }

            else if (cnt2 == 0 && el1 != nums[i]) {

                cnt2 = 1;
                el2 = nums[i];
            }

            else if (nums[i] == el1) {

                cnt1++;
            }

            else if (nums[i] == el2) {

                cnt2++;
            }

            else {

                cnt1--;
                cnt2--;
            }
        }

        cnt1 = 0;
        cnt2 = 0;

        for (int num : nums) {

            if (num == el1) {
                cnt1++;
            }

            if (num == el2) {
                cnt2++;
            }
        }

        List<Integer> ans = new ArrayList<>();

        int threshold = (n / 3) + 1;

        if (cnt1 >= threshold) {
            ans.add(el1);
        }

        if (cnt2 >= threshold) {
            ans.add(el2);
        }

        return ans;
    }
}
