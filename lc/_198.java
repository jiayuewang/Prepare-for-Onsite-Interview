class Solution {
    public int rob(int[] nums) {
        int sto = 0;
        int nosto = 0;
        for (int num : nums){
            int temp = nosto;
            nosto = Math.max(nosto, sto);
            sto = num + temp;
        }
        return Math.max(nosto, sto);
    }
}

//  1，3，2，4，1
//  no  sto
//1: 0    1
//3: 1    3  temp = 3
//2：3     3
//

/**198. House Robber

You are a professional robber planning to rob houses along a street.
 Each house has a certain amount of money stashed,
 the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
 it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
 determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class _198 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

}
