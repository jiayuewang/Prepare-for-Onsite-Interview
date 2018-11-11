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
}

class Solution {
    public int maxSubArray(int[] nums) {
      int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int count = dp[0];
        for(int i = 1; i<nums.length; i++){//0初始化，从1开始加起
            dp[i] = nums[i]+dp[i-1]<0? 0:dp[i-1];
            count = Math.max(count,dp[i]);
                
        }
        return count;
    }
}// On On