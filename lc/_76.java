package com.fishercoder.solutions;


 //  76. Minimum Window Substring
 
 //  Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 // For example,
 // S = "ADOBECODEBANC"
 // T = "ABC"

 // Minimum window is "BANC".

 // Note:

 // If there is no such window in S that covers all characters in T, return the empty string "".
 // If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 

// Solution 1: HashMap + Two Pointers O(n); O(n)
// 题目要求是在O(n)的时间度里找到最小窗口字串，那么暴力搜索肯定是不能用的，我们可以考虑哈希表，其中key是T中的字符，value是该字符出现的次数。 
// 首先遍历一遍T，把对应的字符及其出现的次数存到哈希表中。这里可以有个小优化：先扫描S，然后再扫描T，检查S是否包含T中所有字符，若不全部包含，这可以直接返回""，面试中可以不写，自己知道就行。 
// 然后遍历S，用左右两个指针维护一个窗口，首先左指针不动，移动右指针去找到一个有效窗口：遇到T中的字符，就把对应的哈希表中的value减一，直到包含了T中的所有的字符。 
// 找到有效窗口后，纪录子串的开始索引和长度，然后移动左指针去找最短有效窗口，如果遇到更短的子串，则更新开始索引和长度。

        public String minWindow(String s, String t) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()) { //toCharArray的空间复杂度是O(n)，若用charAt，可以降为O(1)，
                map.put(c, 0); //实际速度区别不大，运行的时候用charAt反而更慢。┓( ´∀` )┏
            }
            for (char c : t.toCharArray()) {
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    return "";
                }
            }
    
            int low = 0;
            int high = 0;
            int minStart = 0;
            int minLen = Integer.MAX_VALUE;
            int count = t.length();
            while (high < s.length()) {
                char c1 = s.charAt(high);
                if (map.get(c1) > 0) {
                    count--;
                }
                map.put(c1, map.get(c1) - 1);
                high++;
    
                while (count == 0) {
                    if (minLen > high - low) {
                        minLen = high - low;
                        minStart = low;
                    }
    
                    char c2 = s.charAt(low);
                    map.put(c2, map.get(c2) + 1);
                    if (map.get(c2) > 0) {
                        count++;
                    }
                    low++;
                }
            }
            return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
//         }
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_E7CC05C650C5D7CE15A13249EA844AC6A4780541CB01E578B6C571AD231BA411_1516383619889_image.png)


// Solution 2: map array + Two Pointers O(n); O(n)
// 因为key是字符，所以可以用int数组代替HashMap，推荐面试用这种写法，效率高且代码简洁。

        public String minWindow(String s, String t) {
            int[] map = new int[256];
            for (char c : t.toCharArray()) { 
                map[c]++;
            }
    
            int count = t.length();
            int left = 0, right = 0; 
            int minStart = 0, minLen = Integer.MAX_VALUE;
            while (right < s.length()) {
                if (map[s.charAt(right++)]-- > 0) { 
                    count--;
                }
                while (count == 0) { 
                    if (right - left < minLen) {
                        minStart = left;
                        minLen = right - left;
                    }
                    if (map[s.charAt(left++)]++ == 0) {
                        count++;
                    }
                }
            }
            return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
//         }
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_E7CC05C650C5D7CE15A13249EA844AC6A4780541CB01E578B6C571AD231BA411_1516383549294_image.png)


