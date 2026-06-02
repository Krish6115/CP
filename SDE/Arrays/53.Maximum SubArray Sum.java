/*# Problem: Maximum Subarray Sum

# 1. Brute Force / Basic Approach

## Approach Name

Brute Force using Nested Loops

## Explanation

The basic idea is:

1. Consider every possible subarray.
2. Calculate the sum of each subarray.
3. Track the maximum sum found.

Example:

Input:

[−2,1,−3,4,−1,2,1,−5,4]

Possible subarrays:

[-2]
[-2,1]
[-2,1,-3]
...
[4,-1,2,1]

Maximum sum:

[4,-1,2,1] = 6

This approach checks all possible subarrays.
## Java Code (Brute Force)

*/
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxi = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = i; j < n; j++) {
                sum += nums[j];
                maxi = Math.max(maxi, sum);
            }
        }

        return maxi;
    }
}
/*
## Time Complexity (TC)

Outer loop:

O(n)

Inner loop:

O(n)

Overall:

TC = O(n²)

## Space Complexity (SC)

No extra space used.

SC = O(1)


# 2. Your Optimized Approach

## Approach Name

Kadane’s Algorithm


## Understanding & Validation of Your Logic

✅ Your logic is fully correct.

Main idea:

1. Keep a running sum.
2. Add current element to the sum.
3. Update maximum sum if needed.
4. If sum becomes negative, reset it to 0.

Why reset?

Because a negative sum can only reduce future subarray sums.

So we discard it and start a new subarray.


## Step-by-Step Explanation

Example:

Input:

[-2,1,-3,4,-1,2,1,-5,4]

Initialize:

maxi = -∞
sum = 0

Iteration:

i = 0

sum = -2
maxi = -2

sum < 0

Reset:

sum = 0

i = 1

sum = 1
maxi = 1

i = 2

sum = -2

Reset:

sum = 0

i = 3

sum = 4
maxi = 4

i = 4

sum = 3
maxi = 4

i = 5

sum = 5
maxi = 5

i = 6

sum = 6
maxi = 6

Answer:

6

Subarray:

[4,-1,2,1]


## Java Code (Your Optimized Approach)
*/ 
import java.util.*;

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxi = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            if (sum > maxi) {
                maxi = sum;
            }

            if (sum < 0) {
                sum = 0;
            }
        }

        return maxi;
    }
}
/*
## Time Complexity (TC)

Single traversal:

TC = O(n)

---

## Space Complexity (SC)

Only variables used:

* sum
* maxi

SC = O(1)

---

# Summary

| Approach                           | Idea                             |    TC |   SC |
| ---------------------------------- | -------------------------------- | ----: | ---: |
| Brute Force                        | Check all subarrays              | O(n²) | O(1) |
| Kadane's Algorithm (Your Approach) | Running sum + reset negative sum |  O(n) | O(1) |
*/
