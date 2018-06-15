
import java.util.HashSet;
/**8. String to Integer (atoi)  QuestionEditorial Solution  My Submissions
Total Accepted: 115114
Total Submissions: 839893
Difficulty: Easy
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.*/
import java.util.Set;

/**
 * 8. String to Integer (atoi)
 *
 * Implement atoi to convert a string to an integer.
 *
 * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
 *
 * Requirements for atoi:
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.*/

}

// 1. 若字符串开头是空格，则跳过所有空格，到第一个非空格字符，如果没有，则返回0.

// 2. 若第一个非空格字符是符号+/-，则标记sign的真假，这道题还有个局限性，那就是在c++里面，+-1和-+1都是认可的，都是-1，而在此题里，则会返回0.

// 3. 若下一个字符不是数字，则返回0. 完全不考虑小数点和自然数的情况，不过这样也好，起码省事了不少。

// 4. 如果下一个字符是数字，则转为整形存下来，若接下来再有非数字出现，则返回目前的结果。

// 5. 还需要考虑边界问题，如果超过了整形数的范围，则用边界值替代当前值。



public class Solution {
    /**
     * @param str: A string
     * @return: An integer
     */
    public int atoi(String str) {
        // write your code here
        if(str.isEmpty()) return 0;
        int sign = 1,base = 0, i = 0, n = str.length();
        while(i < n && str.charAt(i)==' ') i++;
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            sign = (str.charAt(i++) == '+')? 1:-1;//向后移一位到符号位下一位
        }
        while (i < n && str.charAt(i) >='0' &&str.charAt(i) <='9') {
            if(base > Integer.MAX_VALUE/10||(base == Integer.MAX_VALUE/10 &&str.charAt(i)-'0' > 7)){
                return (sign == 1)? Integer.MAX_VALUE:Integer.MIN_VALUE; 
            }
            base = 10 * base +(str.charAt(i++)-'0');//循环至下一个i
        }
        return base*sign;//记得乘以符号位
    }
}

