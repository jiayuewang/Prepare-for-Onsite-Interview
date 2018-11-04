// You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

// Find out how many ways to assign symbols to make sum of integers equal to target S.

// Example 1:
// Input: nums is [1, 1, 1, 1, 1], S is 3. 
// Output: 5
// Explanation: 

// -1+1+1+1+1 = 3
// +1-1+1+1+1 = 3
// +1+1-1+1+1 = 3
// +1+1+1-1+1 = 3
// +1+1+1+1-1 = 3

// There are 5 ways to assign symbols to make the sum of nums be target 3.
// Note:
// The length of the given array is positive and will not exceed 20.
// The sum of elements in the given array will not exceed 1000.
// Your output answer is guaranteed to be fitted in a 32-bit integer.






public class Solution {
    //Backtracking time: O(2^n) space:
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return back(nums, S, 0, 0, 0);
    }
    
    private int back(int[] nums, int S, int sum, int count, int position) {
        if (position == nums.length) {
            if (sum == S) {
                count++;
            }
            return count;
        }
        else {
            int a = back(nums, S, sum + nums[position], count, position + 1);
            int b = back(nums, S, sum - nums[position], count, position + 1);
            return a + b;
        }
    }
}