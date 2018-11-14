class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];// pay attention to initialization
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        int cur = 0;
        for (int i = 1; i < nums.length; i++){
            dp[i] += nums[i] + (dp[i-1] < 0? 0:dp[i-1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

// 进行判断，小于0的时候不加进来
/**
 * 53. Maximum Subarray
 *
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */

class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for(int i = 1; i<nums.length; i++){
            dp[i] = nums[i] + (dp[i-1] < 0 ? 0: dp[i-1]);
                res = Math.max(res, dp[i]); 
        }
        return res;
    }
}// On O1