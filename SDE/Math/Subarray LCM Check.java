/**
 * Problem Name: Powering the Machine: Subarray LCM Check
 * * Problem Description:
 * Given an array of integers and a target value 'k', find the total number of 
 * contiguous subarrays where the Least Common Multiple (LCM) of all elements 
 * in the subarray is exactly equal to 'k'.
 * * Input Format:
 * - The first line contains two integers: 'n' (size of the array) and 'k' (target LCM).
 * - The second line contains 'n' space-separated integers representing the array elements.
 * * Output Format:
 * - Print a single integer representing the count of valid subarrays.
 * * Sample Input:
 * 3 6
 * 2 3 6
 * * Sample Output:
 * 4
 * * Explanation:
 * The valid subarrays with LCM = 6 are:
 * 1. [2, 3]    -> LCM(2, 3) = 6
 * 2. [2, 3, 6] -> LCM(2, 3, 6) = 6
 * 3. [3, 6]    -> LCM(3, 6) = 6
 * 4. [6]       -> LCM(6) = 6
 */

import java.util.*;

public class SubarrayLCMCheck {
    // Helper method to find Greatest Common Divisor (GCD) using Euclidean Algorithm
    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper method to find Least Common Multiple (LCM)
    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b; // Rearranged to prevent premature integer overflow
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int n = sc.nextInt();
        long k = sc.nextLong();
        
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextLong();
        }
        
        int count = 0;
        
        // Traverse all possible starting points of subarrays
        for (int i = 0; i < n; i++) {
            long currentLcm = nums[i];
            
            // Optimization: If the starting element doesn't divide k, 
            // it can never form a valid subarray LCM matching k.
            if (k % currentLcm != 0) continue;
            
            // Check the single-element subarray [nums[i]]
            if (currentLcm == k) {
                count++;
            }
            
            // Expand the subarray to the right
            for (int j = i + 1; j < n; j++) {
                // Optimization: If any individual element doesn't divide k, 
                // the total LCM of this subarray will never divide k.
                if (k % nums[j] != 0) {
                    break; 
                }
                
                currentLcm = lcm(currentLcm, nums[j]);
                
                if (currentLcm == k) {
                    count++;
                } else if (currentLcm > k) {
                    // Since LCM can only grow or stay the same, break early if it exceeds k
                    break;
                }
            }
        }
        
        System.out.println(count);
        sc.close();
    }
}
