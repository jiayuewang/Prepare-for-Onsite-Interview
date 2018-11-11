package com.fishercoder.solutions;

/**
 * 10. Regular Expression Matching
 *
 * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */


// 思路：'.'匹配任意单字符，但不能匹配空字符；'*'可以匹配0或多个前面一个字符，匹配0个就是将前面的字符删掉，匹配多个代表对前面字符的重复。'*'也可以匹配前面的’.’ 即’.*'可以代表一个'.’ 两个'.’ 或者多个'.’ 每一步匹配都会出现多种条件判别情况 我们用递归和动态规划来分别实现。

// 题解1:Recursion, 定义一个isMatch递归函数进行匹配，，两个变量：s 和 p。先对 p 做条件判断，在此基础上，对 s 做条件判断。
// 条件1:p.length()==0, s为空返回true，s不空则返回false。
// 条件2:p.length()==1,
//           (2-1)s为空返回false；
//           (2-2)s不为空，p.charAt(0)＝= s.charAt(0)或者p.charAt(0) ＝= '.' 
//           若满足则返回isMatch(s.substring(1), p.substring(1))，否则为false。
// 条件3: P.length() >=2，
//             (3-1)s不空，p.charAt(1)!=’*’,且满足 s.charAt(0) == p.charAt(0) || p.charAt(0) ==                                   '.'时，若满足则返回isMatch(s.substring(1), p.substring(1))，否则为false。          

//     (3-2)s不空，P.length() >=2 p.charAt(1)＝=’*’，且满足 s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'时，如果该字符只出现一次 if (isMatch(s, p.substring(2))) 返回true，否则重复执行s = s.substring(1)
//     (3-3)p.charAt(1)＝=’*’，返回isMatch(s, p.substring(2))。
//   由上述，我们把条件2的最后一种情况和条件3的第一种情况合并。

// Time: O((m+n)x2^(m+n/2)), m=s.length,n=p.length,
// Space: O(m^2 + n^2)
public class _10 {


class Solution {
    public boolean isMatch(String s, String p) {
        if (p.length()==0) {
            return s.length()==0;
        }
        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))){
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')){  
            if (isMatch(s, p.substring(2))) { 
                return true;                     
            }                                    
            s = s.substring(1);
        }
        return isMatch(s, p.substring(2));
    }
}
