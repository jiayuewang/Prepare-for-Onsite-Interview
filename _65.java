package com.fishercoder.solutions;

/**
 * 65. Valid Number
 *
 * Validate if a given string is numeric.
 *
 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" => false "2e10" => true
 *
 * Note: It is intended for the problem statement to be ambiguous. You should gather all
 * requirements up front before implementing one.
 */

// 思路:  判断一个字符串是否符合上述模式，从前往后扫描。在数值之前，可能有一个表示正负的‘-’或者‘+’。接下来是若干个0~9的数位表示数值的整数部分（某些小数可能没有数值的整数部分）。小数点之后可能会有若干个0~9的数位表示数值的小数部分。根据科学计数法，接下来是一个‘e’或者‘E’也是合法的，之后带有正负号的整数同样valid。

// 题解1：Iterated Function Systems 
// 合法情况包括合法的数字（int / double），或者再加上一个e加合法的int。我们可以列出几种合法情况，1，出现过0-9数字。2，在e出现前可以包含小数点‘.’。3，字符串里包含e但e之后必须有整数。4，字符串里包含sign ‘＋’或‘-’。为此我们需要定义几个boolean类型的变量：number， point，e，numberAfterE分别用来表示字符串中的数字，小数点，e，在e之后出现的数字。
// Time Complexity：O(n)
// Space Complexity：O(1)
// 代码如下：

    class Solution {
        public boolean isNumber(String s) {
            s = s.trim();
            boolean number = false;
            boolean point = false;
            boolean e = false;
            boolean numberAfterE = true;
            
            for(int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                if(cur >= '0' && cur <= '9') {
                    number = true;
                    numberAfterE = true;//保证e之后有整数出现是合法的
                } else if(cur == '.') {
                    //小数点之前不能有e或者.
                    if(e || point) {                    
                        return false;
                    }
                    point = true;
                } else if(cur == 'e') {
                    //e之前不能出现e; e之前必须有number出现
                    if(e || !number) {
                        return false;
                    }
                    e = true;
                    numberAfterE = false;
                } else if(cur == '+' || cur == '-') {
                    // sign can only appear at the the beginning / right after e
                    if(i != 0 && cur != 'e') {
                        return false;
                    }
                }else {
                    return false;
                }
            }
            return number&&numberAfterE;
        }
    }
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_7F949CF302E3CBCCE40B51DA2DF034381267326D30E7B9CF8D34E5DFF6FB3279_1517089564063_+2018-01-27+3.44.10.png)


// 题解2：Regular Expression / regex
// 中括号表示其内的内容都是符合要求的匹配，所以[+-]表示“+”或者"-"；
// 加一个?代表前面的character或者括号内的内容可以有，所以这里表示+-号可以有；
// 接下来需要匹配数字，有两种可能，要么8.8这种要么小数点开头像.8这种，即
// (\\d+\\.?\\d* | \\.\\d+)  其中\d，代表[0-9]中的任意一个，多一个\以便java可以compile，+表示有一个或多个，|表示或。注意这里”3.”是合法的情况；
// 接下来需要匹配e跟着整数，用e[-+]?\\d+表示；这个部分可以有，所以整体套在”()?”里。
// Time Complexity：O(n)
// Space Complexity：O(1)

    public class Solution {
         public static void main(String[] args) { 
        } 
        public boolean isNumber(String s) { 
            if(s.trim().isEmpty()){ 
                return false; 
            } 
            String regex = "[-+]?(\\d+\\.?\\d*|\\.\\d+)(e[-+]?\\d+)?"; 
            return (s.trim().matches(regex));
        } 
    }
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_7F949CF302E3CBCCE40B51DA2DF034381267326D30E7B9CF8D34E5DFF6FB3279_1517090832748_+2018-01-27+4.06.59.png)


// 这里可以看到submission的速度很慢。原因是regex需要额外的compile时间（来build DFA），这个时间为O(2^m)  (m为regex表示pattern的string本身的长度（具体解释参考https://swtch.com/~rsc/regexp/regexp1.html）。因此常见的优化方法是限制regex只compile一遍，代码如下。当然这里不考虑用static是否是个合适的design。

//     import java.util.regex.*;
    
    public class Solution {
        static String regex = "[-+]?(\\d+\\.?\\d*|\\.\\d+)(e[-+]?\\d+)?"; 
        static Pattern pattern = Pattern.compile(regex);
        
        public boolean isNumber(String s) { 
            if(s.trim().isEmpty()) { 
                return false; 
            } 
            Matcher matcher = pattern.matcher(s.trim());
            return matcher.matches();
        } 
    }

// 速度变快了。