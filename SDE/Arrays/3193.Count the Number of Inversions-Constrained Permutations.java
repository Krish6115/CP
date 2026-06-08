import java.util.*;

class Solution {

    /*
    Problem: Count the Number of Inversions-Constrained Permutations

    Approach Name:
    Dynamic Programming on Inversion Counts

    Idea:

    Let:

    dp[inv] = Number of permutations having exactly
              'inv' inversions.

    Build permutations incrementally.

    When adding the next largest element:

    It can be inserted at any position.

    If current length = len

    New inversions added can be:

    0, 1, 2, ... , len

    Therefore:

    newDp[inv + add] += dp[inv]

    Requirements:

    requirements[i] = [end, cnt]

    Meaning:

    Prefix ending at index 'end'
    must contain exactly 'cnt' inversions.

    Whenever we reach a required length,
    we keep only the DP state corresponding
    to the required inversion count.

    --------------------------------------------------
    Time Complexity:
    O(n * MAX_INV * n)

    MAX_INV <= 400

    --------------------------------------------------
    Space Complexity:
    O(MAX_INV)
    */

    private static final int MOD = 1_000_000_007;
    private static final int MAX_INV = 400;

    public int numberOfPermutations(int n, int[][] requirements) {

        Arrays.sort(requirements,
                (a, b) -> Integer.compare(a[0], b[0]));

        int reqIndex = 0;

        if (requirements[0][0] == 0) {

            if (requirements[0][1] != 0) {
                return 0;
            }

            reqIndex++;
        }

        long[] dp = new long[MAX_INV + 1];
        dp[0] = 1;

        int nextLimit;

        if (reqIndex < requirements.length) {
            nextLimit = requirements[reqIndex][1];
        } else {
            nextLimit = MAX_INV;
        }

        for (int len = 2; len <= n; len++) {

            long[] newDp = new long[MAX_INV + 1];

            for (int inv = 0; inv <= MAX_INV; inv++) {

                if (dp[inv] == 0) {
                    continue;
                }

                if (inv > nextLimit) {
                    continue;
                }

                for (int add = 0; add < len; add++) {

                    if (inv + add > MAX_INV) {
                        break;
                    }

                    newDp[inv + add] =
                            (newDp[inv + add] + dp[inv]) % MOD;
                }
            }

            if (reqIndex < requirements.length &&
                    len - 1 == requirements[reqIndex][0]) {

                int requiredInv =
                        requirements[reqIndex][1];

                if (newDp[requiredInv] == 0) {
                    return 0;
                }

                long ways = newDp[requiredInv];

                newDp = new long[MAX_INV + 1];
                newDp[requiredInv] = ways;

                reqIndex++;

                if (reqIndex < requirements.length) {
                    nextLimit =
                            requirements[reqIndex][1];
                } else {
                    nextLimit = MAX_INV;
                }
            }

            dp = newDp;
        }

        long answer = 0;

        for (long ways : dp) {
            answer = (answer + ways) % MOD;
        }

        return (int) answer;
    }
}
