/*1.Brute Force Approach ($O(N)$ Time)This approach iterates through every number in the range and checks if it's odd. While it passes small inputs, it will result in a Time Limit Exceeded (TLE) error for large constraints like 10^9*/
class Solution {
    private boolean isOdd(int x) {
        return x % 2 != 0;
    }

    public int countOdds(int low, int high) {
        int cnt = 0;
        for (int i = low; i <= high; i++) {
            if (isOdd(i)) {
                cnt++;
            }
        }
        return cnt;
    }
}
/* 2. Optimized Approach ($O(1)$ Time) — RecommendedThis approach uses a mathematical formula to find the answer instantly without any loops, which satisfies the constant time*/
  class Solution {
    public int countOdds(int low, int high) {
        // Formula to get the base count of odd numbers in the interval
        int cnt = (high - low) / 2;
        
        // If either the low boundary or the high boundary (or both) is odd,
        // we need to include that boundary number in our total count.
        if (low % 2 != 0 || high % 2 != 0) {
            cnt++;
        }
        
        return cnt;
    }
}
