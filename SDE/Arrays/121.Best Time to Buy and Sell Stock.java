/* # Problem: Best Time to Buy and Sell Stock (LeetCode 121)
1. Brute Force / Basic Approach

## Approach Name

Check All Buy-Sell Pairs

## Explanation

For every day:

1. Choose it as the buying day.
2. Check every future day as the selling day.
3. Compute profit.
4. Keep track of the maximum profit.

Example:

Input:

[7,1,5,3,6,4]

Possible profits:

Buy at 7:

* Sell at 1 → -6
* Sell at 5 → -2
* Sell at 3 → -4
* Sell at 6 → -1
* Sell at 4 → -3

Buy at 1:

* Sell at 5 → 4
* Sell at 3 → 2
* Sell at 6 → 5
* Sell at 4 → 3

Maximum profit = 5

---

## Java Code (Brute Force)
*/
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int n = prices.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }

        return maxProfit;
    }
}
/*
## Time Complexity (TC)

Two nested loops:

TC = O(n²)

## Space Complexity (SC)

No extra space used.

SC = O(1)


# 2. Your Optimized Approach

## Approach Name

Single Pass Minimum Price Tracking

## Understanding & Validation of Your Logic

### Core Idea

To maximize profit:

Profit = Selling Price − Buying Price

For every day:

* Find the minimum price seen before or on that day.
* Assume today's price is the selling price.
* Calculate profit.
* Update maximum profit.

This avoids checking all buy-sell combinations.

## Example Walkthrough

Input:

[7,1,5,3,6,4]

Initialize:
minPrice = ∞
maxPro = 0
### Day 1

Price = 7

minPrice = 7
profit = 0
maxPro = 0
### Day 2

Price = 1
minPrice = 1
profit = 0
maxPro = 0

### Day 3

Price = 5
profit = 5 - 1 = 4
maxPro = 4
### Day 4

Price = 3
profit = 3 - 1 = 2
maxPro = 4
### Day 5

Price = 6
profit = 6 - 1 = 5
maxPro = 5
### Day 6

Price = 4

profit = 4 - 1 = 3
maxPro = 5
Answer:
5
## Java Code (Your Optimized Approach)
*/
import java.util.*;

class Solution {
    public int maxProfit(int[] prices) {
        int maxPro = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxPro = Math.max(maxPro, prices[i] - minPrice);
        }

        return maxPro;
    }
}
/*
## Time Complexity (TC)

Single traversal of the array:

TC = O(n)

## Space Complexity (SC)

Only two variables are used:

* minPrice
* maxPro

SC = O(1)
# Summary

| Approach                                           | Idea                                   | TC    | SC   |
| -------------------------------------------------- | -------------------------------------- | ----- | ---- |
| Brute Force                                        | Check every buy-sell pair              | O(n²) | O(1) |
| Single Pass Minimum Price Tracking (Your Approach) | Track minimum price and maximum profit | O(n)  | O(1) |
*/
