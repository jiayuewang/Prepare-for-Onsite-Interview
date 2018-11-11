public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        int min = nums[0];
        for(int i= 1; i<nums.length;i++){
            if(nums[i]<min){
                min = nums[i];
            }
        
        }
            return min;
    }
}//  worse case On