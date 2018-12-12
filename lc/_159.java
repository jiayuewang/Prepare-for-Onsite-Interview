class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0; int end = 0;
        int res = 0;
        while(end < s.length()){
            if(map.size() <= 2){
            map.put(s.charAt(end),end);
                end ++;
        }
            if(map.size() > 2){
                int left = s.length();
                for(int num : map.values()){
                    left = Math.min(left, num);
                }
                map.remove(s.charAt(left));
                start = left + 1;
            }
            res = Math.max(res, end- start);
        }
//         if (s == null || s.length == 0) return map;
//         for (int i = 0; i < s.length(); i++){
         
//             //map.gerOrDefault(s.charAt(i), 0) +1
        return res;
        }
    }
//eceba  < 2 ece 
//eceb > 2----> left = min(2, 1(c那个位置)) ，把c扔掉就剩下eb start = 2 
//然后size == 2 又加入一个 eba end++,超界，size = 2
//ecebad  or这个例子
// size==2 又加入eba end++, size > 2, delete e 最后长度为2 res = 3
//return res

import java.util.HashMap;

/**
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

 For example, Given s = “eceba”,

 T is "ece" which its length is 3.
 */
public class _159 {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() < 1) {
            return 0;
        }
        HashMap<Character, Integer> index = new HashMap<Character, Integer>();
        int lo = 0;
        int hi = 0;
        int maxLength = 0;
        while (hi < s.length()) {
            if (index.size() <= 2) {
                char c = s.charAt(hi);
                index.put(c, hi);
                hi++;
            }
            if (index.size() > 2) {
                int leftMost = s.length();
                for (int i : index.values()) {
                    leftMost = Math.min(leftMost, i);
                }
                char c = s.charAt(leftMost);
                index.remove(c);
                lo = leftMost + 1;
            }
            maxLength = Math.max(maxLength, hi - lo);
        }
        return maxLength;
    }

}
