import java.util.*;

class Solution {

    /*
    ==================================================
    Problem: Longest Substring Without Repeating Characters
    (LeetCode 3)
    ==================================================

    Given a string s.

    Return the length of the longest substring
    without repeating characters.

    Example:

    s = "abcabcbb"

    Longest Substring:

    "abc"

    Length = 3

    ==================================================
    Approach 1: Brute Force
    ==================================================

    Idea:

    Generate every possible substring.

    For each substring:

    Check whether all characters
    are unique.

    If unique:

    Update answer.

    --------------------------------------------------

    Example:

    s = "abcabcbb"

    Substrings:

    "a"
    "ab"
    "abc" -> Valid

    "abca" -> Invalid

    Continue for all substrings.

    --------------------------------------------------

    Time Complexity:
    O(n³)

    Generating substrings -> O(n²)

    Checking uniqueness -> O(n)

    --------------------------------------------------

    Space Complexity:
    O(1)
    */

    public int lengthOfLongestSubstringBruteForce(String s) {

        int n = s.length();
        int maxLen = 0;

        for(int i = 0; i < n; i++) {

            for(int j = i; j < n; j++) {

                HashSet<Character> set =
                        new HashSet<>();

                boolean valid = true;

                for(int k = i; k <= j; k++) {

                    if(set.contains(s.charAt(k))) {

                        valid = false;
                        break;
                    }

                    set.add(s.charAt(k));
                }

                if(valid) {

                    maxLen =
                            Math.max(maxLen,
                                    j - i + 1);
                }
            }
        }

        return maxLen;
    }

    /*
    ==================================================
    Approach 2: Better Approach
    ==================================================

    Approach Name:
    Sliding Window + HashSet

    Idea:

    Maintain a window:

    [left .... right]

    If duplicate character appears:

    Remove characters from left
    until duplicate disappears.

    Keep updating maximum length.

    --------------------------------------------------

    Example:

    s = "abcabcbb"

    Window:

    "a"
    "ab"
    "abc"

    Next character:

    'a'

    Duplicate found.

    Remove old 'a'.

    Window becomes:

    "bca"

    Continue.

    --------------------------------------------------

    Time Complexity:
    O(2n)

    Space Complexity:
    O(256)
    */

    public int lengthOfLongestSubstringBetter(String s) {

        HashSet<Character> set =
                new HashSet<>();

        int left = 0;
        int maxLen = 0;

        for(int right = 0;
            right < s.length();
            right++) {

            while(set.contains(s.charAt(right))) {

                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));

            maxLen =
                    Math.max(maxLen,
                            right - left + 1);
        }

        return maxLen;
    }

    /*
    ==================================================
    Optimized Approach
    ==================================================

    Approach Name:
    Sliding Window + Last Seen Index

    Your Approach
    --------------------------------------------------

    Idea:

    Instead of removing characters
    one by one,

    directly jump the left pointer
    to the correct position.

    Store:

    hash[ch]

    = Last index where character appeared.

    --------------------------------------------------

    Example:

    s = "abcabcbb"

    Initially:

    hash[] = -1

    --------------------------------------------------

    r = 0

    'a'

    Not seen before.

    Window:

    "a"

    maxLen = 1

    Store:

    hash['a'] = 0

    --------------------------------------------------

    r = 1

    'b'

    Window:

    "ab"

    maxLen = 2

    hash['b'] = 1

    --------------------------------------------------

    r = 2

    'c'

    Window:

    "abc"

    maxLen = 3

    hash['c'] = 2

    --------------------------------------------------

    r = 3

    'a'

    Last seen:

    hash['a'] = 0

    Since:

    0 >= left

    Move:

    left = 0 + 1

    left = 1

    Window becomes:

    "bca"

    Length = 3

    --------------------------------------------------

    Why Check:

    hash[ch] >= left ?

    Example:

    s = "abba"

    After processing:

    "abb"

    left = 2

    Last 'a' index = 0

    Since:

    0 < left

    Old 'a' is outside current window.

    Therefore we should NOT move left.

    --------------------------------------------------

    Time Complexity:

    Every character processed once.

    O(n)

    --------------------------------------------------

    Space Complexity:

    Fixed array of size 256.

    O(1)

    --------------------------------------------------

    Why Optimal?

    - Single traversal.
    - No unnecessary removals.
    - Direct jump of left pointer.
    - Most efficient sliding window solution.
    */

    public int lengthOfLongestSubstring(String s) {

        int n = s.length();

        int[] hash = new int[256];

        Arrays.fill(hash, -1);

        int left = 0;
        int maxLen = 0;

        for(int right = 0;
            right < n;
            right++) {

            char current = s.charAt(right);

            if(hash[current] >= left) {

                left = hash[current] + 1;
            }

            maxLen =
                    Math.max(maxLen,
                            right - left + 1);

            hash[current] = right;
        }

        return maxLen;
    }
}
