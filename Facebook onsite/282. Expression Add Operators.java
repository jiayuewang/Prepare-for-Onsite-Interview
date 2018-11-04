
// Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary)
//  +, -, or * between the digits so they evaluate to the target value.

// Examples: 
// "123", 6 -> ["1+2+3", "1*2*3"] 
// "232", 8 -> ["2*3+2", "2+3*2"]
// "105", 5 -> ["1*0+5","10-5"]
// "00", 0 -> ["0+0", "0-0", "0*0"]
// "3456237490", 9191 -> []


public class Solution {
// ## Idea
// * Use backing tracking (string path, postion, sum and other values into a helper function)
// * When the position is at the end  and target is met, add the path to res
// * Deal with 0, 0 can not be the start of a number 
// * Avoid overflow, use long
// ## Time 
// * Each digit have 3 situations(+,-,nothing)
// * O(3^n)

// ```
// T(n) = 2 * T(n-1) + 2 * T(n-2) + 2 * T(n-3) + ... + 2 *T(1);
// T(n-1) = 2 * T(n-2) + 2 * T(n-3) + ... 2 * T(1);
// Thus T(n) = 3T(n-1);

// ```
// ## Space 
// * For nums = 00000....0 target 0 
// * you get output of 3^(n-1) string
// * O(3^n)
// * O(n) for recursion stack

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }
        dfs(result, num, target, 0, 0, 0, "");
        return result;
    }
    
    public void dfs(List<String> result, String num, int target, int position, long currVal, long prevVal, String path) {
        if (position == num.length()) {
            if (currVal == target) {
                result.add(path);
            }
            return;
        }
        
        for (int i = position + 1; i <= num.length(); i++) {
            String currStr = num.substring(position, i);
            long curNum = Long.parseLong(currStr);
            
            //if i isn't on the first char(pos) and the first char(pos) is a '0'
            if (num.charAt(position) == '0' && currStr.length() > 1) {
                break;
            }
            
            //if it's the first num of path, just add it to path, no operations needed
            if (position == 0) {
                dfs(result, num, target, i, curNum, curNum, currStr);
            } else {//therefore, we need to change it back after passing it to the helper(kind of like backtracking)
                dfs(result, num, target, i, currVal + curNum, curNum, path + "+" + currStr);
                dfs(result, num, target, i, currVal - curNum, -curNum, path + "-" + currStr);
                dfs(result, num, target, i, currVal - prevVal + prevVal * curNum,
                prevVal * curNum, path + "*" + currStr);
            }
        }
        
        ////1234(val=0,mul=0) ->1-2(val=-1,mul=-2) ->1-2*3(val=-1+2+(-2*3)=-5,mul=-2*3=-6) ->1-2*3*4(val=-5+6+(-6*4)=-23,mul=-6*4=-24)
    }
}


//if input is a int[], output is the number of sums that equals to 0, and we only need to use "+", "-", or "connect"(12&2=122)
//also, we need to make sure that the total digits of input nums doesn't lead to long's overflow (19 digits)
public class Solution {
    public int addOperators(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return helper(nums, new StringBuilder(), 0, 0);
    }
    private int helper(int[] nums, StringBuilder path, int pos, long val) {
        if (pos == nums.length) {//val:total val we has added before curr recursion, mul:total consecutive product before
            if (val == 0) {
                return 1;
            }
            return 0;
        }
        StringBuilder num = new StringBuilder();//initialize it to ""
        int total = 0;
        for (int i = pos; i < nums.length; i++) {
            if (i != pos && nums[pos] == 0) {//if i isn't on the first char(pos) and the first char(pos) is a '0'
                break;
            }
            num.append(String.valueOf(nums[i]));//connect two nums
            long curr = Long.valueOf(num.toString());//remember to add toString() !!!
            int len = path.length();//save the length of needed part of stringbuilder
            if (pos == 0) {//if it's the first num of path, just add it to path, no operations needed
                total += helper(nums, path.append(curr), i + 1, val + curr);
                path.setLength(len);//stringbuilder is different from string,it changes itself before passed as a parameter
            } else {//therefore, we need to change it back after passing it to the helper (kind of like backtracking)
                total += helper(nums, path.append("+").append(curr), i + 1, val + curr);
                path.setLength(len);
                total += helper(nums, path.append("-").append(curr), i + 1, val - curr);
                path.setLength(len);
            }
        }
        return total;
    }
}