package com.fishercoder.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. Longest Substring Without Repeating Characters
 *
*/

Given a string, find the length of the longest substring without repeating characters.

 Examples:

 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 Given "pwwkew", the answer is "wke", with the length of 3.

 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

public int lengthOfLongestSubstring(String s) {
    int i = 0, j = 0, max = 0;
    Set<Character> set = new HashSet<>();
    
    while (j < s.length()) {
        if (!set.contains(s.charAt(j))) {
            set.add(s.charAt(j++));
            max = Math.max(max, set.size());
        } else {
            set.remove(s.charAt(i++));
        }
    }
    
    return max;
}

The idea is use a hash set to track the longest substring 
without repeating characters so far, use a fast pointer j to see if 
character j is in the hash set or not, if not, great, add it to the hash set, 
move j forward and update the max length, otherwise, delete from the head by using a slow pointer i until we can put character j to the hash set.



    public static class Solution2 {
        /**
         * Sliding Window
         * O(n) time
         * O(min(m,n)) or O(k) space
         */
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            Set<Character> set = new HashSet<>();
            int result = 0;
            int i = 0;
            int j = 0;
            while (i < n && j < n) {
                /**Try to extend the range i, j*/
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j++));
                    result = Math.max(result, j - i);
                } else {
                    set.remove(s.charAt(i++));
                }
            }
            return result;
        }
    }

    public static class Solution3 {
        /**
         * Sliding Window
         * O(n) time
         * O(n) space
         */
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) {
                return 0;
            }
            int max = 0;
            Map<Character, Integer> map = new HashMap<>();
            /**Try to extend the range (i, j)*/
            for (int i = 0, j = 0; i < s.length(); i++) {
                if (map.containsKey(s.charAt(i))) {
                    j = Math.max(j, map.get(s.charAt(i)) + 1);
                }
                map.put(s.charAt(i), i);
                max = Math.max(max, i + 1 - j);
            }
            return max;
        }
    }

    public static class Solution4 {
        /**
         * Sliding Window Optimized
         * O(n) time
         * O(n) space
         */
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) {
                return 0;
            }
            int max = 0;
            int[] index = new int[128];
            /**Try to extend the range (i, j)*/
            for (int i = 0, j = 0; j < s.length(); j++) {
                i = Math.max(index[s.charAt(j)], i);
                max = Math.max(max, j - i + 1);
                index[s.charAt(j)] = j + 1;
            }
            return max;
        }
    }
}
