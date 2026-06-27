//Better Approach : Counting Sort
class Solution {
    public void sortColors(int[] nums) {

        int zero = 0;
        int one = 0;
        int two = 0;

        for (int num : nums) {
            if (num == 0)
                zero++;
            else if (num == 1)
                one++;
            else
                two++;
        }

        int index = 0;

        while (zero-- > 0)
            nums[index++] = 0;

        while (one-- > 0)
            nums[index++] = 1;

        while (two-- > 0)
            nums[index++] = 2;
    }
}
//Optimal Approach : Dutch National Flag Algorithm
class Solution {
    public void sortColors(int[] nums) {

        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {

            if (nums[mid] == 0) {

                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;

                low++;
                mid++;

            } else if (nums[mid] == 1) {

                mid++;

            } else {

                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                high--;
            }
        }
    }
}
/* Complexity
Time: O(n)
Space: O(1) */
