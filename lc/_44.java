package com.fishercoder.solutions;


//   44. Wildcard Matching
//   Implement wildcard pattern matching with support for '?' and '*'.

//  '?' Matches any single character.
//  '*' Matches any sequence of characters (including the empty sequence).

//  The matching should cover the entire input string (not partial).

//  The function prototype should be:
//  bool isMatch(const char *s, const char *p)

//  Some examples:
//  isMatch("aa","a") → false
//  isMatch("aa","aa") → true
//  isMatch("aaa","aa") → false
//  isMatch("aa", "*") → true
//  isMatch("aa", "a*") → true
//  isMatch("ab", "?*") → true
//  isMatch("aab", "c*a*b") → false



// 1. Greedy Algorithm 解法

// 每次碰到一个*号我们会将它记录下来，然后就去和target进行比较。如下图所示，第一个图中，我们假设＊匹配0个字符，于是我们进行接下来的操作。当尝试三个字符的匹配之后，我们发现匹配失败， 这个时候说明*号匹配０个字符不成立。我们将＊后面的字符与刚刚匹配失败的字符串的起始的下一个字符进行匹配，也就是说我们就尝试它后面的那个元素，当遇到匹配失败的情况时，我们逐渐向后面递推匹配字符直到达到下图中右面的情况。

 
// Time: O(mn), m=s.length, n=p.length
// Space: O(1)
// 代码如下：

    public class Solution {  
        public boolean isMatch(String str, String target) {  
            int s = 0, p = 0, visited = 0, pre = -1;              
            while (s < str.length()){  
                // both '?'and '*' condition
                if (p < target.length()  
                    && (target.charAt(p) == '?' || str.charAt(s) == target.charAt(p))){  
                    s++;  
                    p++;  
                }  
                // only * 
                else if (p < target.length() && target.charAt(p) == '*'){  
                    pre = p;  //遇到*号时，记录一下当前所在的位置和p＋1所在的位置。每次匹配比较不对了就回退。记录开始比较时候的位置为pre。如果匹配后不match就＋1，用来表示下一个开始比较的起点。
                    visited = s;  
                    p++;  
                }  
               // last match one is *
                else if (pre != -1){  
                    p = pre + 1;  
                    visited++;  
                    s = visited;  
                }  
               //last char is not * and not match 
                else return false;  
              //没有*号，如果字符串不匹配，我们返回false
            }  
            //检查字符串剩下元素
            while (p < target.length() && target.charAt(p) == '*')  
                p++;  
            return p == target.length();  
        }  
    }  
    
//     2.DP 解法
// 定义一个boolean dp[i][j]，用来表示s[0-i] 与p [0-j] 是否匹配。
// 初始化dp[i][0] ＝ false，即pattern为空字符串时无法匹配
// 初始化dp[0][j], j = [1 : p]. 只有pattern为*能匹配空字符串，一旦出现其他字符就不行。具体做法详见代码
// induction rule为：
// 如果p[j] != '*’，dp[i, j] = dp[i-1, j-1] &&(s[i] == p[j] || p[j] == '?')  
// 否则，dp[i][j] = dp[i-1][j] || dp[i][j-1]。从前面开始找到一个对应j-1时为true时，对应i-1的s串匹配的位置之后的位置都＝true。

// Time: O(mn),m=s.length,n=p.length
// Space: O(mn)
// 代码如下：

    class Solution {
        public boolean isMatch(String str, String target) {
          int s=str.length(), p=target.length();
          boolean[][] dp = new boolean[s+1][p+1];
          dp[0][0] = true;// initial
          for (int i=1; i<=s; i++) {
                  dp[i][0] = false;
          }
          for(int j=1; j<=p; j++) {
                  if(target.charAt(j-1)=='*'){
                          dp[0][j] = true;
                  } else {
                          break;
                  }
          }
          for(int i=1; i<=s; i++) {
              for(int j=1; j<=p; j++) {
                  if (target.charAt(j-1)!='*') {
                      dp[i][j] = dp[i-1][j-1] 
                                  && (str.charAt(i-1)==target.charAt(j-1) 
                                      || target.charAt(j-1)=='?');
                  } else {
                      dp[i][j] = dp[i-1][j] || dp[i][j-1];
                  }
              }
          }
          return dp[s][p];
        } 
    }