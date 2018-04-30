public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        // write your code here
        int sum = 0;
        int ans = Integer.MAX_VALUE;
       
        for ( int l = 0, r = 0; r < nums.length; r++ ) {
            sum += nums[r];
            
            while (sum >= s) {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l++];
            }
        }
        return ans == Integer.MAX_VALUE? -1 : ans;
    }
}// On O1