/*BF*/
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int count = 0;

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }

            ans.add(count);
        }

        return ans;
    }
}
/* Better Approach*/
class Solution {

    int[] count;
    int[][] temp;

    public List<Integer> countSmaller(int[] nums) {

        int n = nums.length;

        count = new int[n];
        temp = new int[n][2];

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        mergeSort(arr, 0, n - 1);

        List<Integer> ans = new ArrayList<>();

        for (int x : count)
            ans.add(x);

        return ans;
    }

    private void mergeSort(int[][] arr, int left, int right) {

        if (left >= right)
            return;

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    private void merge(int[][] arr, int left, int mid, int right) {

        int i = left;
        int j = mid + 1;
        int k = left;

        int rightCounter = 0;

        while (i <= mid && j <= right) {

            if (arr[j][0] < arr[i][0]) {

                temp[k++] = arr[j++];
                rightCounter++;

            } else {

                count[arr[i][1]] += rightCounter;
                temp[k++] = arr[i++];
            }
        }

        while (i <= mid) {

            count[arr[i][1]] += rightCounter;
            temp[k++] = arr[i++];
        }

        while (j <= right) {

            temp[k++] = arr[j++];
        }

        for (int p = left; p <= right; p++) {

            arr[p] = temp[p];
        }
    }
}
