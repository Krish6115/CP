import java.util.*;

class Solution {

    /*
    Problem: Find Missing and Repeated Values (LeetCode 2965)

    -----------------------------------------
    Understanding My Approach
    -----------------------------------------

    My Idea:

    Step 1:
    Convert the n x n grid into a 1D array.

    Example:

    Grid:

    1 3
    2 2

    Converted Array:

    [1,3,2,2]

    Step 2:
    Sort the array.

    Sorted Array:

    [1,2,2,3]

    Step 3:
    Traverse the array.

    If:

    arr[i] == arr[i+1]

    then that number is the repeated number.

    Example:

    [1,2,2,3]

         ↑ ↑

    Duplicate = 2

    Step 4:
    Create another array containing numbers from 1 to n².

    Example:

    [1,2,3,4]

    Step 5:
    Compare both arrays and find which number
    from 1 to n² is missing.

    Missing = 4

    Return:

    [2,4]

    -----------------------------------------
    Validation
    -----------------------------------------

    Your idea is correct.

    It successfully finds:

    - Repeated Number
    - Missing Number

    However, creating an additional array and
    comparing using nested loops is not optimal.

    We can improve it after sorting itself.

    Time Complexity:
    O(n² log(n²))

    Space Complexity:
    O(n²)
    */

    public int[] findMissingAndRepeatedValuesSorting(int[][] grid) {

        int n = grid.length;
        int size = n * n;

        int[] arr = new int[size];

        int index = 0;

        for (int[] row : grid) {
            for (int num : row) {
                arr[index++] = num;
            }
        }

        Arrays.sort(arr);

        int repeated = -1;
        int missing = -1;

        for (int i = 0; i < size - 1; i++) {

            if (arr[i] == arr[i + 1]) {
                repeated = arr[i];
            }

            if (arr[i + 1] - arr[i] > 1) {
                missing = arr[i] + 1;
            }
        }

        if (arr[0] != 1) {
            missing = 1;
        }

        if (arr[size - 1] != size) {
            missing = size;
        }

        return new int[]{repeated, missing};
    }

    /*
    -----------------------------------------
    Optimized Approach
    -----------------------------------------

    Approach Name:
    Frequency Counting

    Idea:

    Numbers should be from:

    1 to n²

    Create a frequency array of size n² + 1.

    Traverse the grid:

    freq[number]++

    After that:

    freq[i] == 2
    -> Repeated Number

    freq[i] == 0
    -> Missing Number

    Example:

    Grid:

    1 3
    2 2

    Frequency:

    1 -> 1
    2 -> 2
    3 -> 1
    4 -> 0

    Repeated = 2
    Missing = 4

    Return:

    [2,4]

    Time Complexity:
    O(n²)

    Space Complexity:
    O(n²)

    Why Better?

    - No sorting required.
    - Single traversal for counting.
    - Simpler implementation.
    */

    public int[] findMissingAndRepeatedValues(int[][] grid) {

        int n = grid.length;
        int total = n * n;

        int[] freq = new int[total + 1];

        for (int[] row : grid) {
            for (int num : row) {
                freq[num]++;
            }
        }

        int repeated = -1;
        int missing = -1;

        for (int i = 1; i <= total; i++) {

            if (freq[i] == 2) {
                repeated = i;
            }

            if (freq[i] == 0) {
                missing = i;
            }
        }

        return new int[]{repeated, missing};
    }
}
