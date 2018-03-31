package com.fishercoder.solutions;

 5. Longest Palindromic Substring
 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example:
 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.

 Example:
 Input: "cbbd"
 Output: "bb"
 */
class Solution {
    public String longestPalindrome(String s) {
       if(s == null || s.length() == 0){
           return s;
       }
        String res="";
        int max=0;
        boolean dp[][] = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++ ){
            for( int j = 0; j <= i; j++){//i and j can be the same!!!!XXXXXX
                
                dp[j][i]=s.charAt(i)==s.charAt(j) && (i - j <=2 || dp[j+1][i-1]);//the condition
                //befotre || solve initilization problrm 
                if (dp[j][i]){
                    if(i - j + 1 > max) {
                        max=i-j+1;
        res = s.substring(j, i + 1);
      }
            }
            
        }
        }
        return res;
    }
}
// O n^2  n^2