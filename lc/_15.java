class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        if(nums == null || nums.length == 0) return res;
        
        for(int i =0; i < nums.length-2 ;i++){//至少保证后面有两个数的位置
            if(i > 0 && nums[i]== nums[i-1]) continue;
            int j = i+1;
            int k = nums.length -1;
            int cur = 0 - nums[i];//change positive to negative
            while(j < k){
                if(nums[j] + nums[k] == cur){
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));//同一个位置不能重复使用相同的数字
                    while(j < k && nums[j] == nums[j+1]) j++;
                    while(j < k && nums[k] == nums[k-1]) k--;
                    j++;k--;//排除重复之后
                }else if(nums[j] + nums[k] < cur){
                    j++;
                }else{
                    k--;
                }
            }
        }
        return res;
    }
}
          
            

/**
 * 15. 3Sum
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note: The solution set must not contain duplicate triplets.

 For example, given array S = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 */

class Solution {
public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    List<List<Integer>> res = new LinkedList<>(); 
    for (int i = 0; i < num.length-2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            int lo = i+1, hi = num.length-1, sum = 0 - num[i];
            while (lo < hi) {
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    while (lo < hi && num[lo] == num[lo+1]) lo++;
                    while (lo < hi && num[hi] == num[hi-1]) hi--;
                    lo++; hi--;
                } else if (num[lo] + num[hi] < sum) lo++;
                else hi--;
           }
        }
    }
    return res;
}
}//ON O1