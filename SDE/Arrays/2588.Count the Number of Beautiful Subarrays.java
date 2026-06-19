import java.util.*;

class Solution {

    /*
    ==================================================
    Problem: Count the Number of Beautiful Subarrays
    (LeetCode 2588)
    ==================================================

    A subarray is called beautiful if we can make
    all elements equal to 0 after performing any
    number of operations.

    Observation:

    The operation is based on XOR properties.

    A subarray is beautiful if:

    XOR of all elements in that subarray = 0

    Therefore the problem becomes:

    Count the number of subarrays having XOR = 0.

    ==================================================
    Approach 1: Brute Force
    ==================================================

    Idea:

    Generate every possible subarray.

    Compute XOR for each subarray.

    If XOR == 0:

    Increment answer.

    --------------------------------------------------

    Example:

    nums = [4,3,1,2,4]

    Check:

    [4]
    [4,3]
    [4,3,1]
    ...

    Count all subarrays whose XOR = 0.

    --------------------------------------------------

    Time Complexity:
    O(n²)

    Space Complexity:
    O(1)
    */

    public long beautifulSubarraysBruteForce(int[] nums) {

        long count = 0;

        for(int i = 0; i < nums.length; i++) {

            int xor = 0;

            for(int j = i; j < nums.length; j++) {

                xor ^= nums[j];

                if(xor == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    /*
    ==================================================
    Better Approach
    ==================================================

    Approach Name:
    Prefix XOR Array

    Idea:

    Build prefix XOR.

    XOR of subarray:

    prefixXor[r] ^ prefixXor[l-1]

    If result = 0

    Then:

    prefixXor[r] == prefixXor[l-1]

    We need to count equal prefix XOR values.

    This observation leads directly
    to the optimized solution.

    Time Complexity:
    O(n²)

    Space Complexity:
    O(n)
    */


    /*
    ==================================================
    Optimized Approach
    ==================================================

    Approach Name:
    Prefix XOR + HashMap

    Your Approach
    --------------------------------------------------

    Key Observation:

    Let:

    prefixXor[i]

    denote XOR from index 0 to i.

    XOR of subarray:

    l to r

    = prefixXor[r] ^ prefixXor[l-1]

    For XOR to become 0:

    prefixXor[r] = prefixXor[l-1]

    Therefore:

    We need to count pairs of equal
    prefix XOR values.

    --------------------------------------------------

    Example:

    nums = [1,2,3]

    Prefix XOR:

    1
    1^2 = 3
    3^3 = 0

    Prefix XOR Array:

    [1,3,0]

    Initial Map:

    {0 = 1}

    --------------------------------------------------

    Process:

    pre = 1

    Map:

    {0=1}

    Not found

    Add:

    {0=1,1=1}

    --------------------------------------------------

    pre = 3

    Not found

    Add:

    {0=1,1=1,3=1}

    --------------------------------------------------

    pre = 0

    Found:

    Map contains 0 once

    res += 1

    Beautiful Subarray Found

    --------------------------------------------------

    Why dp.put(0,1)?

    Consider:

    nums = [0]

    Prefix XOR:

    0

    We should count this subarray.

    Therefore:

    prefix XOR 0 must already exist once.

    --------------------------------------------------

    Why res += frequency?

    Suppose:

    Prefix XOR:

    [2,5,2,2]

    Current XOR = 2

    Frequency of 2 = 2

    Then current position forms:

    2 new beautiful subarrays.

    Hence:

    res += frequency

    --------------------------------------------------

    Time Complexity:

    Single traversal

    O(n)

    --------------------------------------------------

    Space Complexity:

    HashMap stores prefix XOR frequencies

    O(n)

    --------------------------------------------------

    Why Optimal?

    - Single pass solution.
    - Uses XOR property efficiently.
    - No nested loops.
    - Interview expected solution.
    */

    public long beautifulSubarrays(int[] nums) {

        HashMap<Integer, Integer> freq =
                new HashMap<>();

        freq.put(0, 1);

        int prefixXor = 0;

        long result = 0;

        for(int num : nums) {

            prefixXor ^= num;

            int count =
                    freq.getOrDefault(prefixXor, 0);

            result += count;

            freq.put(
                    prefixXor,
                    count + 1
            );
        }

        return result;
    }
}
