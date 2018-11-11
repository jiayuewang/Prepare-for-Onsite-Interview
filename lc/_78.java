
// import java.util.ArrayList;
// import java.util.List;

// /**
//  * 78. Subsets
//  *
//  * Given a set of distinct integers, nums, return all possible subsets.
//  * Note: The solution set must not contain duplicate subsets.

//  For example,
//  If nums = [1,2,3], a solution is:
//  [
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//  ]
//  */

class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      if(nums == null || nums.length == 0) return res;
      helper(res, new ArrayList<>(), nums,0);// assign the value
      return res;
    }
    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int i){// initialize
        res.add(new ArrayList<>(list));//把current 放在res中做记录
        for(int j = i; j<nums.length;j++){
            list.add(nums[j]);// ItISj    将元素加入current 的 list中
            helper(res,list, nums,j+1);//assign the value but we already have list 
            list.remove(list.size()-1);
        }
    }
}
//2^n N

    
    每一个数字都是两种选择 选还是不选

    
public class _78 {

    public static class Solution1 {
        public static List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList();
            if (nums == null) {
                return result;
            }
            result.add(new ArrayList());
            for (int i = 0; i < nums.length; i++) {
                List<List<Integer>> temp = new ArrayList();
                //you'll have to create a new one here, otherwise, it'll throw ConcurrentModificationException.
                for (List<Integer> list : result) {
                    List<Integer> newList = new ArrayList(list);
                    newList.add(nums[i]);
                    temp.add(newList);
                }
                result.addAll(temp);
            }
            return result;
        }
    }

    public static class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList();
            backtracking(result, new ArrayList(), nums, 0);
            return result;
        }

        void backtracking(List<List<Integer>> result, List<Integer> list, int[] nums, int start) {
            result.add(new ArrayList(list));
            for (int i = start; i < nums.length; i++) {
                list.add(nums[i]);
                backtracking(result, list, nums, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    
    public static class Solution3 {
        /**
         * This is just a slight modification of Solution2, pay close to attention to notice the difference between them.
         */
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            result.add(list);
            backtracking(result, list, nums, 0);
            return result;
        }

        private void backtracking(List<List<Integer>> result, List<Integer> list, int[] nums, int start) {
            for (int i = start; i < nums.length; i++) {
                list.add(nums[i]);
                result.add(new ArrayList<>(list));
                backtracking(result, list, nums, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}
