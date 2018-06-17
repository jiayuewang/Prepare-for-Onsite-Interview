
import java.util.ArrayList;
import java.util.List;

// /**
//  * 22. Generate Parentheses
//  *
//  * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//  *
//  * For example, given n = 3, a solution set is:

//  [
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//  ]
// */
Recursion来解，由于字符串只有左括号和右括号两种字符，而且最终结果必定是左括号3个，
右括号3个，所以我们定义两个变量left和right分别表示剩余左右括号的个数，如果在某次递归时，
左括号的个数大于右括号的个数，说明此时生成的字符串中右括号的个数大于左括号的个数，即会出现')('这样的非法串，
所以这种情况直接返回，不继续处理。如果left和right都为0，则说明此时生成的字符串已有3个左括号和3个右括号，且字符串合法，
则存入结果中后返回。如果以上两种情况都不满足，若此时left大于0，则调用递归函数，注意参数的更新，若right大于0，则调用递归函数，同样要更新参数。

Recursion to solve, because the string has only two characters left brackets and right brackets, and the final result must be 3 left brackets.

The right bracket is 3, so we define two variables, left and right, respectively, 
to indicate the number of left and right parentheses.

The number of the left parentheses is greater than the number of the right parentheses, 
indicating that the number of the right brackets in the generated string is greater than the number of the left parentheses, 
that is, ') (' such an illegal string, 
So this situation is directly returned and not processed. 
If both left and right are 0, it means that the generated string now has 3 left brackets and 3 right brackets, and the string is legal.

Then return to the result. If the above two cases are not satisfied, 
if the left is more than 0, then the recursive function is called, and the parameter is updated. If right is greater than 0, the recursive function is called, and the parameters are updated as well


public class Solution {
    /**
     * @param n: n pairs
     * @return: All combinations of well-formed parentheses
     */
    public List<String> generateParenthesis(int n) {
        // write your code here
        List<String> result = new ArrayList();
        if(n == 0) {
            return result ;
        }
        helper(n,n,"",result);
        return result;
    }
    void helper(int left, int right, String par, List<String> result){
        if(left > 0) { 
            helper(left - 1, right,par+"(",result);
        }
        if(right > left){
            helper(left, right-1, par+')',result);
        }
        if(right == 0) { 
            result.add(par);
        }
    }
}





public class _22 {
    public static class Solution1 {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList();
            backtrack(result, "", 0, 0, n);
            return result;
        }

        void backtrack(List<String> result, String str, int left, int right, int max) {
            if (str.length() == max * 2) {
                result.add(str);
                return;
            }

            if (left < max) {
                backtrack(result, str + "(", left + 1, right, max);
            }

            if (right < left) {
                backtrack(result, str + ")", left, right + 1, max);
            }
        }
    }

    public static class Solution2 {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList();
            if (n == 0) {
                return result;
            }
            helper(result, "", n, n);
            return result;
        }

        void helper(List<String> result, String par, int left, int right) {
            if (left > 0) {
                helper(result, par + "(", left - 1, right);
            }
            if (right > left) {
                helper(result, par + ")", left, right - 1);
            }
            if (right == 0) {
                result.add(par);
            }
        }
    }
}
