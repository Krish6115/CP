/* # Problem: Next Permutation

# 1. Brute Force / Basic Approach

## Approach Name

**Generate All Permutations + Sort**

## Explanation

The brute force idea is:

1. Generate **all possible permutations** of the array.
2. Sort them lexicographically.
3. Find the current permutation.
4. Return the next permutation.
5. If current permutation is the last one, return the first permutation.

### Example

Input:

```text id="1"
[1,2,3]
```

Permutations:

```text id="2"
[1,2,3]
[1,3,2]
[2,1,3]
[2,3,1]
[3,1,2]
[3,2,1]
```

Next of `[1,2,3]`:

```text id="3"
[1,3,2]

This works but is extremely inefficient.


## Java Code (Brute Force)
*/
import java.util.*;

class Solution {

    public void nextPermutation(int[] nums) {
        List<int[]> perms = new ArrayList<>();
        generate(nums, 0, perms);

        perms.sort((a, b) -> {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i])
                    return a[i] - b[i];
            }
            return 0;
        });

        int index = -1;

        for (int i = 0; i < perms.size(); i++) {
            if (Arrays.equals(perms.get(i), nums)) {
                index = i;
                break;
            }
        }

        int[] next = perms.get((index + 1) % perms.size());

        for (int i = 0; i < nums.length; i++) {
            nums[i] = next[i];
        }
    }

    private void generate(int[] nums, int idx, List<int[]> perms) {
        if (idx == nums.length) {
            perms.add(nums.clone());
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);
            generate(nums, idx + 1, perms);
            swap(nums, idx, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
/*

## Time Complexity (TC)

Generating permutations:

[
O(n!)
]

Sorting:

[
O(n! \log(n!))
]

Overall:

**TC = O(n! × n)**

(very expensive)

---

## Space Complexity (SC)

Stores all permutations:

**SC = O(n! × n)**

---

# 2. Your Optimized Approach

## Approach Name

**Lexicographical Next Permutation Algorithm**

---

## Understanding Your Logic

Your code follows **3 important steps**.

---

## Step 1: Find the Break Point

You traverse from right to left:
id="5"
nums[i] < nums[i+1]
```

This finds the first decreasing point.

Why?

Because:

* Right side is already in descending order.
* Descending order means **largest possible permutation** for that suffix.
* We must modify the first place where improvement is possible.

Example:

```text id="6"
1 2 7 4 3 1
```

From right:

```text id="7"
4 > 3 > 1
```

Breakpoint:

```text id="8"
2 < 7
```

Index:

```text id="9"
ind = 1
```

---

## Step 2: Find Next Greater Element

Now from the right side:

Find:

```java id="10"
nums[i] > nums[ind]
```

Why?

We want:

* Smallest number
* Greater than breakpoint value

This ensures the **next immediate permutation**.

Example:

Breakpoint:

```text id="11"
2
```

Right side:

```text id="12"
7 4 3 1
```

Next greater:
text id="13"
3

Swap:
text id="14"
1 3 7 4 2 1

## Step 3: Reverse the Suffix

After swap:

Right side remains descending.

To get the **smallest possible suffix**:

Reverse it.

Example:

Before reverse:
text id="15"
1 3 7 4 2 1

After reverse:

text id="16"
1 3 1 2 4 7


This becomes the **next lexicographical permutation**.
## Java Code (Optimized Approach)
*/
class Solution {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int ind = -1;

        // Step 1: Find breakpoint
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind = i;
                break;
            }
        }

        // If no breakpoint exists
        if (ind == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // Step 2: Find next greater element
        for (int i = n - 1; i > ind; i--) {
            if (nums[i] > nums[ind]) {
                swap(nums, i, ind);
                break;
            }
        }

        // Step 3: Reverse suffix
        reverse(nums, ind + 1, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
/*
## Time Complexity (TC)

Three traversals:

1. Find breakpoint → `O(n)`
2. Find greater element → `O(n)`
3. Reverse suffix → `O(n)`

Overall:

**TC = O(n)**

## Space Complexity (SC)

No extra array used.

Only:

* temp
* indices

**SC = O(1)**

# Summary

| Approach                        | Idea                               |        TC |        SC |
| ------------------------------- | ---------------------------------- | --------: | --------: |
| Brute Force                     | Generate and sort all permutations | O(n! × n) | O(n! × n) |
| Lexicographical (Your Approach) | Breakpoint + Swap + Reverse        |      O(n) |      O(1) |
*/
