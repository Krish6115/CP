class Solution {
    public int findMin(int[] nums) {
        int x=0;
        int low=0;
        int high=nums.length-1;
        
        while(low<=high){
            int mid=(high+low)/2;
            if(nums[0]>nums[mid]){
                high=mid-1;
                x=mid;
            }
            else{
                low=mid+1;
            }
            
    }
    return nums[x];
}}
