import java.util.*;

class Solution {

    /*
    ==================================================
    Problem: 4Sum (LeetCode 18)
    ==================================================

    Given an array nums and an integer target.

    Return all unique quadruplets:

    [nums[a], nums[b], nums[c], nums[d]]

    such that:

    nums[a] + nums[b] + nums[c] + nums[d] = target

    and

    a != b != c != d

    Example:

    nums = [1,0,-1,0,-2,2]
    target = 0

    Output:

    [
      [-2,-1,1,2],
      [-2,0,0,2],
      [-1,0,0,1]
    ]
    */

    /*
    ==================================================
    Approach 1: Brute Force
    ==================================================

    Idea:

    Generate every possible quadruplet.

    Use 4 nested loops.

    Check:

    nums[i] + nums[j] + nums[k] + nums[l]

    If equal to target:

    Add to answer.

    Use Set to remove duplicates.

    --------------------------------------------------

    Time Complexity:
    O(n⁴)

    Space Complexity:
    O(number of quadruplets)

    --------------------------------------------------

    Not feasible for large inputs.
    */

    public List<List<Integer>> fourSumBruteForce(int[] nums, int target) {

        int n = nums.length;

        Set<List<Integer>> set = new HashSet<>();

        for(int i = 0; i < n; i++) {

            for(int j = i + 1; j < n; j++) {

                for(int k = j + 1; k < n; k++) {

                    for(int l = k + 1; l < n; l++) {

                        long sum =
                                (long) nums[i]
                              + nums[j]
                              + nums[k]
                              + nums[l];

                        if(sum == target) {

                            List<Integer> temp =
                                    Arrays.asList(
                                            nums[i],
                                            nums[j],
                                            nums[k],
                                            nums[l]
                                    );

                            Collections.sort(temp);

                            set.add(temp);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }

    /*
    ==================================================
    Approach 2: Better Approach
    ==================================================

    Approach Name:
    HashSet + Three Loops

    Idea:

    Fix:

    i
    j

    Then use HashSet to find
    the fourth element.

    Formula:

    fourth =
    target - (nums[i] + nums[j] + nums[k])

    If fourth already exists
    in HashSet:

    Quadruplet found.

    Use Set<List<Integer>>
    to avoid duplicates.

    --------------------------------------------------

    Time Complexity:
    O(n³)

    Space Complexity:
    O(n)

    --------------------------------------------------

    Better than brute force.
    */

    public List<List<Integer>> fourSumBetter(int[] nums, int target) {

        int n = nums.length;

        Set<List<Integer>> result = new HashSet<>();

        for(int i = 0; i < n; i++) {

            for(int j = i + 1; j < n; j++) {

                Set<Long> set = new HashSet<>();

                for(int k = j + 1; k < n; k++) {

                    long sum =
                            (long) nums[i]
                          + nums[j]
                          + nums[k];

                    long fourth = target - sum;

                    if(set.contains(fourth)) {

                        List<Integer> temp =
                                Arrays.asList(
                                        nums[i],
                                        nums[j],
                                        nums[k],
                                        (int) fourth
                                );

                        Collections.sort(temp);

                        result.add(temp);
                    }

                    set.add((long) nums[k]);
                }
            }
        }

        return new ArrayList<>(result);
    }

    /*
    ==================================================
    Optimized Approach
    ==================================================

    Approach Name:
    Sorting + Two Pointers

    --------------------------------------------------

    Idea:

    First sort the array.

    Fix first element:

    i

    Fix second element:

    j

    Now find remaining two numbers
    using Two Pointers.

    k = j + 1
    l = n - 1

    --------------------------------------------------

    Why Two Pointers?

    Since array is sorted:

    If sum < target

    Move k forward.

    If sum > target

    Move l backward.

    If sum == target

    Store answer.

    Skip duplicates.

    --------------------------------------------------

    Example:

    nums = [1,0,-1,0,-2,2]

    After Sorting:

    [-2,-1,0,0,1,2]

    Fix:

    i = -2

    j = -1

    Need:

    3 more

    Two pointers find:

    1 and 2

    Quadruplet:

    [-2,-1,1,2]

    --------------------------------------------------

    Why Skip Duplicates?

    Example:

    [-2,-2,-1,0,1,2]

    Without skipping duplicates:

    Same quadruplet appears multiple times.

    Therefore:

    if(nums[i] == nums[i-1])
        continue;

    similarly for j, k and l.

    --------------------------------------------------

    Why Use Long?

    Example:

    nums = [1000000000,
            1000000000,
            1000000000,
            1000000000]

    Sum exceeds Integer range.

    Therefore:

    long sum

    prevents overflow.

    --------------------------------------------------

    Time Complexity:

    Sorting:
    O(n log n)

    Outer Loop:
    O(n)

    Inner Loop:
    O(n)

    Two Pointer Scan:
    O(n)

    Total:

    O(n³)

    --------------------------------------------------

    Space Complexity:

    O(1)

    Ignoring answer storage.

    --------------------------------------------------

    Why Optimal?

    - Sorting helps remove duplicates.
    - Two pointers reduce one loop.
    - Standard interview solution.
    - Best accepted approach.
    */

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;

        Arrays.sort(nums);

        for(int i = 0; i < n; i++) {

            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for(int j = i + 1; j < n; j++) {

                if(j > i + 1 &&
                   nums[j] == nums[j - 1]) {
                    continue;
                }

                int k = j + 1;
                int l = n - 1;

                while(k < l) {

                    long sum =
                            (long) nums[i]
                          + nums[j]
                          + nums[k]
                          + nums[l];

                    if(sum == target) {

                        ans.add(Arrays.asList(
                                nums[i],
                                nums[j],
                                nums[k],
                                nums[l]
                        ));

                        k++;
                        l--;

                        while(k < l &&
                              nums[k] == nums[k - 1]) {
                            k++;
                        }

                        while(k < l &&
                              nums[l] == nums[l + 1]) {
                            l--;
                        }
                    }

                    else if(sum < target) {
                        k++;
                    }

                    else {
                        l--;
                    }
                }
            }
        }

        return ans;
    }
}
/*
Approach Summary
Approach	Idea	TC	SC
Brute Force	4 nested loops	O(n⁴)	O(1)
HashSet + 3 Loops	Find 4th element using HashSet	O(n³)	O(n)
Sorting + Two Pointers	Fix 2 elements, find 2 using pointers	O(n³)	O(1)
*/
