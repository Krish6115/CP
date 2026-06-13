import java.util.*;

class Solution {

    /*
    Problem: Unique Paths (LeetCode 62)

    Given an m x n grid.

    A robot starts at:

    Top Left Corner -> (0,0)

    The robot wants to reach:

    Bottom Right Corner -> (m-1,n-1)

    The robot can only move:

    1. Right
    2. Down

    Find the total number of unique paths.

    ==================================================
    Approach 1: Recursion (Brute Force)
    ==================================================

    Idea:

    From every cell:

    Move Right
    Move Down

    Total Paths:

    paths(i,j) =
    paths(i+1,j) + paths(i,j+1)

    Base Cases:

    Reached destination -> return 1

    Out of bounds -> return 0

    Time Complexity:
    O(2^(m+n))

    Space Complexity:
    O(m+n)

    Not practical due to repeated calculations.
    */


    /*
    ==================================================
    Approach 2: Dynamic Programming (2D DP)
    ==================================================

    Idea:

    dp[i][j] = Number of ways to reach cell (i,j)

    First Row:

    Only move Right

    dp[0][j] = 1

    First Column:

    Only move Down

    dp[i][0] = 1

    Transition:

    dp[i][j] =
    dp[i-1][j] + dp[i][j-1]

    Example:

    m = 3
    n = 7

    DP Table:

    1 1 1 1 1 1 1
    1 2 3 4 5 6 7
    1 3 6 10 15 21 28

    Answer:

    dp[2][6] = 28

    Time Complexity:
    O(m * n)

    Space Complexity:
    O(m * n)
    */

    public int uniquePaths2D(int m, int n) {

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {

                dp[i][j] =
                        dp[i - 1][j] +
                        dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }


    /*
    ==================================================
    Approach 3: Dynamic Programming (1D DP)
    ==================================================

    Idea:

    Observe:

    dp[i][j] depends only on:

    dp[i-1][j]
    dp[i][j-1]

    Therefore storing the entire matrix
    is unnecessary.

    Use a single array:

    dp[j] = Number of ways to reach current cell.

    Initially:

    [1,1,1,1,1...]

    Update:

    dp[j] = dp[j] + dp[j-1]

    Example:

    m = 3
    n = 7

    Initial:

    [1,1,1,1,1,1,1]

    After Row 2:

    [1,2,3,4,5,6,7]

    After Row 3:

    [1,3,6,10,15,21,28]

    Answer:

    28

    Time Complexity:
    O(m * n)

    Space Complexity:
    O(n)

    Why Better?

    - Same DP logic
    - Reduced space from O(m*n) to O(n)
    - Interview preferred DP solution
    */

    public int uniquePaths(int m, int n) {

        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {

                dp[j] = dp[j] + dp[j - 1];
            }
        }

        return dp[n - 1];
    }


    /*
    ==================================================
    Approach 4: Mathematical Combination
    ==================================================

    Idea:

    To reach destination:

    Total Moves Required:

    (m-1) Down
    (n-1) Right

    Total Moves:

    m + n - 2

    We need to choose:

    (m-1) positions for Down

    OR

    (n-1) positions for Right

    Formula:

    C(m+n-2, m-1)

    Example:

    m = 3
    n = 7

    Total Moves = 8

    Down Moves = 2

    Answer:

    C(8,2)

    = 28

    Time Complexity:
    O(min(m,n))

    Space Complexity:
    O(1)

    Why Optimal?

    - No DP table
    - No recursion
    - Constant space
    - Fastest solution
    */

    public int uniquePathsMath(int m, int n) {

        int N = m + n - 2;
        int R = Math.min(m - 1, n - 1);

        long answer = 1;

        for (int i = 1; i <= R; i++) {

            answer = answer * (N - R + i) / i;
        }

        return (int) answer;
    }
}
/* Another approach
Space Optimized Dynamic Programming (1D DP)

    Idea:

    In the 2D DP solution:

    dp[i][j] = dp[i-1][j] + dp[i][j-1]

    We observe that to compute the current cell,
    we only need:

    1. Value from the row above.
    2. Value from the left cell.

    Therefore storing the entire m x n matrix
    is unnecessary.

    We can use a single array.

    --------------------------------------------------
    Dry Run
    --------------------------------------------------

    m = 3
    n = 7

    Initial DP Array:

    [1,1,1,1,1,1,1]

    Row 2:

    j=1

    [1,2,1,1,1,1,1]

    j=2

    [1,2,3,1,1,1,1]

    j=3

    [1,2,3,4,1,1,1]

    j=4

    [1,2,3,4,5,1,1]

    j=5

    [1,2,3,4,5,6,1]

    j=6

    [1,2,3,4,5,6,7]

    Row 3:

    [1,3,6,10,15,21,28]

    Answer:

    28

    --------------------------------------------------
    Why Does This Work?
    --------------------------------------------------

    a[j]

    stores:

    Number of ways coming from TOP.

    a[j-1]

    stores:

    Number of ways coming from LEFT.

    Therefore:

    a[j] = a[j] + a[j-1]

    which is exactly:

    dp[i][j] =
    dp[i-1][j] + dp[i][j-1]

    --------------------------------------------------
    Time Complexity:
    O(m * n)

    We visit every cell once.

    --------------------------------------------------
    Space Complexity:
    O(n)

    Only one row is stored.

    --------------------------------------------------
    Is This Optimal?
    --------------------------------------------------

    For Dynamic Programming:

    Yes.

    Space optimized from:

    O(m*n)

    to

    O(n)
    */
import java.util.Arrays;

class Solution {
    public int uniquePaths(int m, int n) {
        // Guard clause for 0-sized grids
        if (m == 0 || n == 0) return 0;
        
        int[] a = new int[n];
        Arrays.fill(a, 1);
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // a[j] (top) + a[j-1] (left)
                a[j] += a[j - 1]; 
            }
        }
        
        return a[n - 1];
    }
}

this approach?
