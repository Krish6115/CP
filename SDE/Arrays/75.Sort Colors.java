/* # Problem: Sort Colors (LeetCode 75)

1. Brute Force / Basic Approach

## Approach Name

Sorting the Array

## Explanation

The simplest approach is:

1. Sort the entire array using Java's built-in sorting method.
2. Since the array contains only 0s, 1s, and 2s, sorting automatically places them in the required order.

Example:

Input:

[2,0,2,1,1,0]

After sorting:

[0,0,1,1,2,2]

---
*/
## Java Code (Brute Force)

import java.util.*;

class Solution {
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
}
/*

## Time Complexity (TC)

Sorting takes:

TC = O(n log n)

---

## Space Complexity (SC)

Java's sorting implementation may use extra space.

SC = O(log n)

# 3. Your Optimized Approach

## Approach Name

Dutch National Flag Algorithm

## Understanding & Validation of Your Logic

The array contains only:

0 → should be on the left

1 → should be in the middle

2 → should be on the right

You maintain three pointers:

low → boundary for 0s

mid → current element being processed

high → boundary for 2s

Initially:
0 ........ mid ........ high

At any point:
0 to low-1      => all 0s
low to mid-1    => all 1s
mid to high     => unsorted region
high+1 to end   => all 2s

## Case 1: nums[mid] == 0

Swap nums[low] and nums[mid].

Reason:

Current 0 belongs to the left side.

After placing it:

* low++
* mid++

## Case 2: nums[mid] == 1

No action required.

1 already belongs in the middle.

Simply:
mid++;
## Case 3: nums[mid] == 2

Swap nums[mid] and nums[high].

Reason:

2 belongs on the right side.

After swapping:
high--;
Do NOT increment mid here.

Why?

Because the element that came from the high position is unprocessed and must be checked again.
## Example Walkthrough

Input:
[2,0,2,1,1,0]


Initial:
low = 0
mid = 0
high = 5

Step 1:
nums[mid] = 2

Swap with high:
[0,0,2,1,1,2]
high = 4

Step 2:
nums[mid] = 0

Swap with low:

[0,0,2,1,1,2]
low = 1

mid = 1

Continue until:

[0,0,1,1,2,2]


## Java Code (Your Optimized Approach)
*/
import java.util.*;

class Solution {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {

            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;

                low++;
                mid++;
            }

            else if (nums[mid] == 1) {
                mid++;
            }

            else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                high--;
            }
        }
    }
}
/*
## Time Complexity (TC)

Each element is processed at most once.

TC = O(n)
## Space Complexity (SC)

No extra array is used.

SC = O(1)

# Summary

| Approach                            | Idea                           | TC         | SC       |
| ----------------------------------- | ------------------------------ | ---------- | -------- |
| Sorting                             | Sort entire array              | O(n log n) | O(log n) |
| Dutch National Flag (Your Approach) | Three pointers: low, mid, high | O(n)       | O(1)     |
*/
