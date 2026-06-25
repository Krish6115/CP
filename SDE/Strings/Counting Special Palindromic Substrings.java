/**
 * Problem Name: Counting Special Palindromic Substrings
 * * Problem Description:
 * Given a string 's', determine the total number of palindromic substrings present in it. 
 * Every single character is by default a palindromic substring. Your task is to count 
 * all such substrings (single characters, repeated characters, and mirrored sequences).
 * * Input Format:
 * A single string 's' consisting of lowercase English letters.
 * * Output Format:
 * A single integer representing the count of palindromic substrings.
 * * Sample Input:
 * aaa
 * * Sample Output:
 * 6
 * * Explanation:
 * Palindromic substrings are: "a", "a", "a", "aa", "aa", "aaa".
 */

import java.util.*;

public class CountingSpecialPalindromicSubstrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;
        
        String s = sc.next();
        int totalPalindromes = 0;
        
        // Treat each index as the center of a potential palindrome
        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd length palindromes (e.g., "aba" centered at 'b')
            totalPalindromes += expandAroundCenter(s, i, i);
            
            // Case 2: Even length palindromes (e.g., "abba" centered between 'b' and 'b')
            totalPalindromes += expandAroundCenter(s, i, i + 1);
        }
        
        System.out.println(totalPalindromes);
        sc.close();
    }
    
    // Helper method to expand outward from a center point and count matches
    private static int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;  // Expand left pointer outward
            right++; // Expand right pointer outward
        }
        return count;
    }
}
