package com.fishercoder.solutions;

/**
 * 14. Longest Common Prefix
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 */

public class solution{
   public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return null;
        }

        if (strs.length == 0) {
            return "";
        }

        int min = Integer.MAX_VALUE;  

        for (String str : strs) {

            if (str == null) {
                return null;
            }

            if (min > str.length()) {
                min = str.length();
            }
        }

        int i; 
        boolean flag;
        for (i = 0; i < min; i++) {
            flag = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[0].charAt(i) != strs[j].charAt(i)) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                break;
            }
        }

        return strs[0].substring(0, i);
    }
}
