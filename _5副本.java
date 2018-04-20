package com.fishercoder.solutions;

/**
 * 5. Longest Palindromic Substring
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example:
 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.

 Example:
 Input: "cbbd"
 Output: "bb"
 */

 On……2 On……2
 用dp，判断i-j是否为回文串，若是，判断是否为最长的

 法二：中心扩散法1aba 2abba

class Solution {
    public String longestPalindrome(String s) {
       // aba，abba
        if(s==null || s.length()==0) return s;
        String res="";
        boolean[][]dp= new boolean[s.length()][s.length()];
        int max =0;
        for(int j=0; j< s.length();j++){
            for(int i=0;i<=j;i++){
                dp[i][j] = s.charAt(i) == s.charAt(j) &&((j-i<=2)|| dp[i+1][j-1]);
                if(dp[i][j]){
                    if(j-i+1>max){
                        max = j-i+1;
                        res=s.substring(i,j+1);//j+1右边边界
                    }
                }
            }
        }
        return res;
    }
}
// O n^2  n^2

类似题：409
class Solution {
    
    //最长回文串that could build
    public int longestPalindrome(String s) {
        if(s == null||s.length() == 0) return 0;
        HashSet<Character> set = new HashSet<>();
        int count =0;
        for(char c:s.toCharArray()){
            if(set.contains(c)){
                set.remove(c);
                count++;
            }else{
            set.add(c);
        }
        
    }
        if(set.size()!=0) return count*2+1;
        return count*2;
}
}
    //On
