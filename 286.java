（1）等差数列先求得总和，然后减去给定的队列，剩下的值就是缺失值。

（2）先排个序，逐个比较，不等的直接return

public class Solution {
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int findMissing(int[] nums) {
        // write your code here
        int res = 0;
        for(int i = 0; i <= nums.length; i++){// 主要N要大于nums.length
            res ^= i;//此处是与i^
        }
        for(int i = 0; i < nums.length; i++){
            res ^= nums[i];
        }
        return res;
    }
}