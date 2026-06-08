import java.util.*;

class Solution {

    /*
    Problem: Merge Sorted Array (LeetCode 88)

    -----------------------------------------
    Understanding My Approach
    -----------------------------------------

    My Idea:

    nums1 has enough extra space at the end to hold all
    elements of nums2.

    Example:

    nums1 = [1,2,3,0,0,0]
    m = 3

    nums2 = [2,5,6]
    n = 3

    Step 1:
    Copy all elements of nums2 into the empty positions
    of nums1.

    nums1 becomes:

    [1,2,3,2,5,6]

    Step 2:
    Sort nums1.

    Result:

    [1,2,2,3,5,6]

    -----------------------------------------
    Validation
    -----------------------------------------

    Your idea is correct.But not optimal
    */

    /*
    -----------------------------------------
    Approach 1: Copy + Sort
    -----------------------------------------

    Approach Name:
    Append and Sort

    Steps:

    1. Copy nums2 into the empty positions of nums1.
    2. Sort nums1.

    Example:

    nums1 = [1,2,3,0,0,0]
    nums2 = [2,5,6]

    After Copy:

    [1,2,3,2,5,6]

    After Sort:

    [1,2,2,3,5,6]

    Time Complexity:
    O((m+n) log(m+n))

    Space Complexity:
    O(log(m+n))
    */

    public void mergeCopyAndSort(int[] nums1, int m, int[] nums2, int n) {
        int i = m;
        for (int j = 0; j < n; j++) {
            nums1[i] = nums2[j];
            i++;
        }
        Arrays.sort(nums1);
    }
    /*
    -----------------------------------------
    Optimized Approach
    -----------------------------------------

    Approach Name:
    Three Pointers from Back

    Key Observation:

    nums1 and nums2 are already sorted.

    Instead of sorting again,
    place the largest element at the end.

    Example:

    nums1 = [1,2,3,0,0,0]
    nums2 = [2,5,6]

    Compare from the back:

    3 and 6

    Place 6 at last position.

    Then compare:

    3 and 5

    Place 5.

    Then compare:

    3 and 2

    Place 3.

    Continue until all elements are placed.

    Final:

    [1,2,2,3,5,6]

    Time Complexity:
    O(m+n)

    Space Complexity:
    O(1)

    Why Optimal?

    - No sorting required.
    - Single traversal.
    - Uses existing extra space in nums1.
    */

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {

            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }

            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
