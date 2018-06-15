public class Solution {
    /*
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
   public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;//check whether the Number is null
        }
        
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid ;// mid or mie+1 are alright
            }
        }
        
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }
}
