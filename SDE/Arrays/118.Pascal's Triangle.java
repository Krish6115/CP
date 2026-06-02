/* # Problem: Pascal's Triangle (Generate Entire Triangle)
# 1. Brute Force / Basic Approach

## Approach Name

**Simulation using Previous Row**

## Explanation

In Pascal’s Triangle:

* First and last elements of every row are always `1`
* Any middle element is:

[
arr[i][j] = arr[i-1][j-1] + arr[i-1][j]
]

### Steps

1. Start with an empty triangle.
2. For each row:

   * First and last elements = `1`
   * Middle elements are obtained from the previous row.
3. Store all rows.

This is the traditional simulation approach.

---

## Java Code (Brute Force)
*/
import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    int val = triangle.get(i - 1).get(j - 1)
                            + triangle.get(i - 1).get(j);
                    row.add(val);
                }
            }

            triangle.add(row);
        }

        return triangle;
    }
}
/*

## Time Complexity (TC)

Each row contains increasing elements:

[
1 + 2 + 3 + ... + n
]

So,

**TC = O(numRows²)**

---

## Space Complexity (SC)

Triangle itself stores:

[
1 + 2 + ... + n
]

elements.

**SC = O(numRows²)**

---

# 2. Your Optimized Approach

## Approach Name

**Combinatorial Approach (nCr Formula):

Instead of using previous rows, you generate each element directly using the mathematical relation:

For Pascal Triangle:

[
nCr = \frac{n!}{r!(n-r)!}
]

But factorial computation is expensive.

So you use the optimized recurrence:

[
nCr = \frac{nC(r-1)\times(n-r+1)}{r}
]

This allows generating the next element from the previous one in **O(1)** time.

Your code logic:

ans = ans * (row - col);
ans = ans / col;

correctly computes the next combination value.

Using `long` is also good to avoid intermediate overflow.


## Explanation

### Row Generation Logic

Every row starts with:


Then remaining values are generated using:

[
C(n,r)
]

from the previous value.

Example:

Row 5:

[
1\ 4\ 6\ 4\ 1
]

Generated as:

* 1
* 1×4/1 = 4
* 4×3/2 = 6
* 6×2/3 = 4
* 4×1/4 = 1

No previous row lookup is needed.

---

## Java Code (Optimized Approach)
*/
import java.util.*;

class Solution {

    public List<Integer> generateRow(int row) {
        long ans = 1;
        List<Integer> ansRow = new ArrayList<>();

        ansRow.add(1); // First element

        for (int col = 1; col < row; col++) {
            ans = ans * (row - col);
            ans = ans / col;
            ansRow.add((int) ans);
        }

        return ansRow;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        for (int row = 1; row <= numRows; row++) {
            ans.add(generateRow(row));
        }

        return ans;
    }
}
/*
## Time Complexity (TC)

For each row:

* Row 1 → 1 operation
* Row 2 → 2 operations
* …
* Row n → n operations

Total:

[
1+2+3+\dots+n
]

[
= O(numRows^2)
]

**TC = O(numRows²)**

---

## Space Complexity (SC)

Output triangle stores:

[
O(numRows^2)
]

Extra space apart from output:

* `ans`
* few variables

= **O(1)** auxiliary space.

Including output:

**SC = O(numRows²)**

---

# Summary

| Approach                      | Idea                        |    TC |    SC |
| ----------------------------- | --------------------------- | ----: | ----: |
| Brute Force                   | Use previous row values     | O(n²) | O(n²) |
| Combinatorial (Your Approach) | Directly compute nCr values | O(n²) | O(n²) |

*/
