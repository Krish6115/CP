/*First Thought (Brute Force)

Generate all permutations and choose the largest concatenated number.

Example:

[3,30,34]

Permutations:

33034
33430
30334
30343
34330
34303

Pick the maximum.

Complexity
Time: O(n!)
Space: O(n)

Impossible for interview constraints.

Why Normal Sorting Doesn't Work

Suppose

[3,30]

Normal descending sort gives

30 3

Result

303

But

3 30

gives

330

which is larger.

So numeric comparison is incorrect.

Key Observation ⭐

Instead of comparing

A > B

compare

AB
vs
BA

Whichever is larger should come first.

Example 1
3
30

Compare

330
303

Since

330 > 303

Place

3 before 30
Example 2
9
34

Compare

934
349

934 is larger

So

9 comes first
Example 3
54
546

Compare

54546
54654

Since

54654 > 54546

546 comes first.

Algorithm
Step 1

Convert every integer into a string.

[3,30,34,5,9]

↓

["3","30","34","5","9"]
Step 2

Sort using custom comparator

Comparator rule

(a+b).compareTo(b+a)

For descending order

(b + a).compareTo(a + b)
Step 3

Join every string.

Step 4

Handle edge case

If the first character is

0

return

"0"

instead of

"00000"
Dry Run

Input

[3,30,34,5,9]

Convert

["3","30","34","5","9"]

Comparator comparisons

9 vs 5

95
59

9 first
5 vs 34

534
345

5 first
34 vs 3

343
334

34 first
3 vs 30

330
303

3 first

Final order

9
5
34
3
30

Join

9534330
Java Solution
*/
import java.util.*;

class Solution {
    public String largestNumber(int[] nums) {

        String[] arr = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        if (arr[0].equals("0"))
            return "0";

        StringBuilder sb = new StringBuilder();

        for (String s : arr) {
            sb.append(s);
        }

        return sb.toString();
    }
}
/*
Complexity Analysis
Operation	Complexity
Convert to Strings	O(n)
Sorting	O(n log n)
String Concatenation	O(n)

Overall:

Time: O(n log n)
Space: O(n)
*/
