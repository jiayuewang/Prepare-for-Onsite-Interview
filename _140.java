package com.fishercoder.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


 // 140. Word Break II
 
 //  Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 // Return all such possible sentences.

 // For example, given
 // s = "catsanddog",
 // dict = ["cat", "cats", "and", "sand", "dog"].

 // A solution is ["cats and dog", "cat sand dog"].

 

//  思路：这道题是Word Break I的follow up，那道题只让我们判断给定的字符串能否被拆分成字典中的词，而这道题加大了难度，让我们求出所有可以拆分成的情况，就像题目中给的例子所示。

// 题解1：DFS+部分剪枝,
// 搜索的时候记录后面的状态, 如果后面不能搜到, 那么就直接返回了, 否则就进行下去. 其实这样的效率并不高, 因为剪枝并不完全, 即后面可以找到的话, 还要再搜一遍。

// Time Complexity O(n^2)
// Space Complexity O(n)

    public List<String> wordBreak(String s, Set<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }       
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s)) 
            return map.get(s);
            
        LinkedList<String>res = new LinkedList<String>();     
        if (s.length() == 0) {
            res.add("");
            return res;
        }               
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) 
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
            }
        }       
        map.put(s, res);
        return res;
    }
    


// 题解2：Dynamic Programming
// 先采用动态规划算出句子所有子串是否是字典中的内容，保存在二维数组中。dp行代表子串起始的下标，列代表表示子串长度-1。我们需要在每个位置建立对应的List<String>，因此在实现的时候可以采用一个Map<Integer, List<String>> map。key表示s里面每个索引的值，value表示每个索引位置对应的list。剩下的就是我们采用类似的两层遍历，建立这个列表。
// Time Complexity O(n^2)
// Space Complexity O(n)
// 代码如下：

    public class Solution {
    
        private final Map<String, List<String>> cache = new HashMap<>();
    
        private boolean containsSuffix(Set<String> dict, String str) {
            for (int i = 0; i < str.length(); i++) {
                if (dict.contains(str.substring(i))) return true;
            }
            return false;
        }
    
        public List<String> wordBreak(String s, Set<String> dict) {
            if (cache.containsKey(s)) return cache.get(s);
            List<String> result = new LinkedList<>();
            if (dict.contains(s)) result.add(s);
            for (int i = 1; i < s.length(); i++) {
                String left = s.substring(0,i), right = s.substring(i);
                if (dict.contains(left) && containsSuffix(dict, right)) {
                    for (String ss : wordBreak(right, dict)) {
                        result.add(left + " " + ss);
                    }
                }
            }
            cache.put(s, result);
            return result;
        }
    }