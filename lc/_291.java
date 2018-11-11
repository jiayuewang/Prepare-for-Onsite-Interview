package com.fishercoder.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 291. Word Pattern II
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
*/

 // Examples:
 // pattern = "abab", str = "redblueredblue" should return true.
 // pattern = "aaaa", str = "asdasdasdasd" should return true.
 // pattern = "aabb", str = "xyzabcxzyabc" should return false.

 // Notes:
 // You may assume both pattern and str contains only lowercase letters.
 
// reference: https://leetcode.com/problems/word-pattern-ii/discuss/73664
// Keep 一个 char to string map,  key(char)代表单个字符的pattern，value(string)为单个字符对应的string。保证每个char对应一个string
// 再keep一个set记录已经使用过的char对应的string。保证每个string对应一个char。

// backtrack  match 过程举例：
// pattern = `"abab"`, str = `"redblueredblue"`
// 先let “a” match “r”, “b” match “e”，此时pattern中第二个“a” 不 match “d”, backtrack,
//                                  “b” match “ed”，此时pattern中第二个“a” 不 match “b”, backtrack,
//                                  …
//                                  “b” match “ed..e”, 此时string结束，backtrack.
//     let “a” match “re”, “b” match “d”, 此时pattern中第二个”a” 不match “b”, backtrack,
//                                  …
//     let “a” match “red”, “b” match “ blue”,  此时pattern中第二个”a” match “red”,   第二个b match “blue” pattern 和 string都到末尾。返回true。
//     由例子可得，我们只要写一个recursion返回是否能match , 从 i index字符开始的string 和 从j index字符pattern即可。并且使用 Map<Character, String> map 和 Set<String> set 来keep bijection mapping。
                                 

    public class Solution {
      
      public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        
        return isMatch(str, 0, pattern, 0, map, set);
      }
      
      boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        // base case
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;
        
        // get current pattern character
        char c = pat.charAt(j);
        
        // if the pattern character exists
        if (map.containsKey(c)) {
          String s = map.get(c);
          
          // then check if we can use it to match str[i...i+s.length()]
          if (!str.startsWith(s, i)) {
            return false;
          }
          
          // if it can match, great, continue to match the rest
          return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }
        
        // pattern character does not exist in the map
        for (int k = i; k < str.length(); k++) {
          String p = str.substring(i, k + 1);
    
          if (set.contains(p)) {
            continue;
          }
    
          // create or update it
          map.put(c, p);
          set.add(p);
          
          // continue to match the rest
          if (isMatch(str, k + 1, pat, j + 1, map, set)) {
            return true;
          }
    
          // backtracking
          map.remove(c);
          set.remove(p);
        }
        
        // we've tried our best but still no luck
        return false;
      }
      
    }

// 算法排名比较低，应该还有很大的空间可以improve。

// 使用map.values()代替多余的hashset记录bijection关系可以improve时间(节省了对set的操作时间）和空间（少了一个hashset).

// Running time: O (n^m)
// Space: O (n) 


    public class Solution {
      
      public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        
        return isMatch(str, 0, pattern, 0, map);
      }
      
      boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map) {
        // base case
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;
        
        // get current pattern character
        char c = pat.charAt(j);
        
        // if the pattern character exists
        if (map.containsKey(c)) {
          String s = map.get(c);
          
          // then check if we can use it to match str[i...i+s.length()]
          if (!str.startsWith(s, i)) {
            return false;
          }
          
          // if it can match, great, continue to match the rest
          return isMatch(str, i + s.length(), pat, j + 1, map);
        }
        
        // pattern character does not exist in the map
        for (int k = i; k < str.length(); k++) {
          String p = str.substring(i, k + 1);
    
          if (map.values().contains(p)) {
            continue;
          }
    
          // create or update it
          map.put(c, p);
          
          // continue to match the rest
          if (isMatch(str, k + 1, pat, j + 1, map)) {
            return true;
          }
    
          // backtracking
          map.remove(c);
        }
        
        // we've tried our best but still no luck
        return false;
      }
      
    }