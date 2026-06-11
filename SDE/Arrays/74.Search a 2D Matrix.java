import java.util.*;

class Solution {

    /*
    Problem: Search a 2D Matrix (LeetCode 74)

    -----------------------------------------
    Understanding My Approach
    -----------------------------------------

    Approach Name:
    Binary Search on Flattened Matrix

    Idea:

    The matrix has two important properties:

    1. Each row is sorted in ascending order.

    2. First element of a row is greater than
       the last element of the previous row.

    Because of these properties,
    the entire matrix can be treated as a
    single sorted 1D array.

    Example:

    Matrix:

    1  3  5  7
    10 11 16 20
    23 30 34 60

    Flattened View:

    [1,3,5,7,10,11,16,20,23,30,34,60]

    Instead of actually creating this array,
    we use index mapping.

    Mapping:

    row = mid / cols
    col = mid % cols

    Example:

    cols = 4

    mid = 5

    row = 5 / 4 = 1
    col = 5 % 4 = 1

    matrix[1][1] = 11

    This allows us to perform Binary Search
    directly on the matrix.

    -----------------------------------------
    Is This Approach Optimal?
    -----------------------------------------

    Yes.

    Since the matrix behaves like a sorted array,
    Binary Search gives the best possible solution.

    -----------------------------------------
    Time Complexity:
    O(log(rows * cols))

    Space Complexity:
    O(1)
    */

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null ||
            matrix.length == 0 ||
            matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int low = 0;
        int high = rows * cols - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int midVal =
                    matrix[mid / cols][mid % cols];

            if (midVal == target) {
                return true;
            }

            else if (midVal < target) {
                low = mid + 1;
            }

            else {
                high = mid - 1;
            }
        }

        return false;
    }
}
