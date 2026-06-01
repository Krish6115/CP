/*Here are both versions with proper **approach names** so you can directly push them to your GitHub repo.

# 1. Brute Force / Extra Space Approach (Row & Column Marking)

**Approach Name:** *Row and Column Marking using Extra Arrays*

### Idea

* Traverse the matrix and mark rows and columns containing `0`.
* Traverse again and set elements to `0` if their row or column is marked.

### Code
// Approach: Row and Column Marking using Extra Arrays
// Time Complexity: O(m * n)
// Space Complexity: O(m + n) 
  */

class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        // Step 1: Mark rows and columns containing 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        // Step 2: Set matrix elements to 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

/*
# 2. Optimized Approach (Constant Space)

**Approach Name:** *In-Place Marking using First Row and First Column*

### Idea

* Use first row and first column as markers.
* `matrix[i][0]` → marks row `i`
* `matrix[0][j]` → marks column `j`
* Use one extra variable `col0` to handle the first column separately.

### Code
// Approach: In-Place Marking using First Row and First Column
// Time Complexity: O(m * n)
// Space Complexity: O(1)
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean col0 = false;

        // Step 1: Mark rows and columns
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
            }

            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // mark row
                    matrix[0][j] = 0; // mark column
                }
            }
        }

        // Step 2: Traverse from bottom-right and update matrix
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }

            // Handle first column
            if (col0) {
                matrix[i][0] = 0;
            }
        }
    }
}
/*
### Summary

| Approach                            |   Time |  Space |
| ----------------------------------- | -----: | -----: |
| Row & Column Marking (Extra Arrays) | O(m*n) | O(m+n) |
| In-Place Marking (First Row/Column) | O(m*n) | O(1)   |
*/
