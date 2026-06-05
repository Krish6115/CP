import java.util.*;

class Solution {

    /*
    Problem: Rotate Image (LeetCode 48)

    -----------------------------------------
    Understanding My Approach
    -----------------------------------------

    Idea:
    I noticed that after a 90-degree clockwise rotation:

    Last Row  -> First Column
    Second Last Row -> Second Column
    and so on.

    I tried changing:

        matrix[i][j]

    to

        matrix[j][i]

    which is actually the Transpose operation.

    Example:

    Original Matrix:

    1 2 3
    4 5 6
    7 8 9

    After Transpose:

    1 4 7
    2 5 8
    3 6 9

    But this is NOT the final rotated matrix.

    We still need to reverse every row.

    -----------------------------------------
    Brute Force Approach
    -----------------------------------------

    Approach Name:
    Extra Matrix Mapping

    Idea:
    Create a temporary matrix and place each element
    directly at its rotated position.

    Mapping:

    temp[j][n - 1 - i] = matrix[i][j]

    Time Complexity:
    O(n²)

    Space Complexity:
    O(n²)
    */

    public void rotateBruteForce(int[][] matrix) {

        int n = matrix.length;

        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n - 1 - i] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }

    /*
    -----------------------------------------
    Optimized Approach
    -----------------------------------------

    Approach Name:
    Transpose + Reverse Rows

    Idea:

    Step 1:
    Transpose the matrix

    matrix[i][j] <-> matrix[j][i]

    Example:

    1 2 3
    4 5 6
    7 8 9

    becomes

    1 4 7
    2 5 8
    3 6 9

    Step 2:
    Reverse every row

    1 4 7 -> 7 4 1
    2 5 8 -> 8 5 2
    3 6 9 -> 9 6 3

    Final Answer:

    7 4 1
    8 5 2
    9 6 3

    Time Complexity:
    O(n²)

    Space Complexity:
    O(1)

    Why Optimal?

    - No extra matrix required.
    - In-place rotation.
    - Standard interview solution.
    */

    public void rotate(int[][] matrix) {

        int n = matrix.length;

        // Step 1: Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse every row
        for (int i = 0; i < n; i++) {

            int left = 0;
            int right = n - 1;

            while (left < right) {

                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
        }
    }
}
