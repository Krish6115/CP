class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n=nums.length;
        int ans[]=new int[2];
        Arrays.fill(ans,-1);
        int l=0,h=n-1;
        while(l<=h){
            int mid=(l+h)/2;
            if(nums[mid]==target){
                ans[0]=mid;
                h=mid-1;
            }
            else if(nums[mid]<target)
                l=mid+1;
            else
                h=mid-1;
        }
        l=0;h=n-1;
        while(l<=h){
            int mid=(l+h)/2;
            if(nums[mid]==target){
                ans[1]=mid;
                l=mid+1;
            }
            else if(nums[mid]<target)
                l=mid+1;
            else
                h=mid-1;
        }
        return ans;
    }
}
