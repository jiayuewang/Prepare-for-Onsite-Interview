
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// /**
//  * 40. Combination Sum II
//  *
//  * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
//  * Each number in C may only be used once in the combination.

//  Note:
//  All numbers (including target) will be positive integers.
//  The solution set must not contain duplicate combinations.
//  For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
//  A solution set is:
//  [
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//  ]
//  */
之前那道题给定数组中的数字可以重复使用，而这道题不能重复使用，
只需要在之前的基础上修改两个地方即可，首先在递归的for循环里加上if (i > start && num[i] == num[i - 1]) continue; 
这样可以防止res中出现重复项，然后就在递归调用combinationSum2DFS里面的参数换成i+1，这样就不会重复使用数组中的数字了

pu
class Solution { 
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, 0, target,res,new ArrayList());
        return res;
    }
   void helper(int[] candidates, int start, int target, List<List<Integer>> res, List<Integer> list) {//记得写void: return type
         if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if(candidates[i] > target || (i > start && candidates[i-1] == candidates[i])) {// 大于start 避免I-1 越界
                    continue;
                }
                list.add(candidates[i]);
                helper(candidates, i+1, target - candidates[i],res,list);
                list.remove(list.size() - 1);
            }
        } else if (target == 0) {//一个list加完了
          res.add(new ArrayList(list));
        }
      }
    }




class Solution { 
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, 0, target,res,new ArrayList());
        return res;
    }
    helper(int[] candidates, int start, int target, List<List<Integer>> res, List<Integer> list) {
        if (target == 0) res.add(new ArrayList(list));//一个list加完了
        else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if(candidates[i] > target || (i > start || candidates[i-1] == candidates[i])) {
                    continue;
                }
                list.add(candidates[i]);
                helper(candidates, i+1, target - candidates[i],res,list);
                list.remove(list.size() - 1);
            }
        }
    }
}
