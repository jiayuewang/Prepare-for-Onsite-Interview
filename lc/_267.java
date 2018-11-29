class Solution {
    public List<String> generatePalindromes(String s) {
      //两种回文形式  
        int add = 0;
        String mid = "";
        List<String> res = new ArrayList<>();
        List<Character> list =new ArrayList<>();//出现两次在list中只放入一半
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c , 0) + 1);
            add += map.get(c) % 2 != 0 ? 1:-1;
        }
        if(add > 1) return res;
        for(HashMap.Entry<Character, Integer> entry : map.entrySet()){
            char key =entry.getKey();
            int val =entry.getValue();
            if(val % 2 != 0) mid += key;//奇数 mid+这个key ==》 mid = 这个奇数
            for(int i = 0; i < val/2; i++){
                list.add(key);// 预处理
            }
        }
            helper(list, mid, new boolean[list.size()], new StringBuilder(), res);
        return res;
    }

    private void helper(List<Character> list, String mid, boolean[] used, StringBuilder sb, List<String> res) {
        if (sb.length() == list.size()) {//所有list中的元素都用了
            res.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) continue;
            //以前用过了并且相等 去重！
            if (!used[i]) {
                used[i] = true;//使用它 赋值为true
                sb.append(list.get(i));
                helper(list, mid, used, sb, res);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
  // 结束的条件是sb.length() == list.size()
        
        

//  abc  return[]
// list: a b
// mid："" 如果都是偶数 则为空、、mid代表奇数的中间
//回文，出现两次 只放入一次即可
//用Hashmap存，出现奇数次 > 1,就是回文

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

 For example:

 Given s = "aabb", return ["abba", "baab"].

 Given s = "abc", return [].

 Hint:

 If a palindromic permutation exists, we just need to generate the first half of the string.
 To generate all distinct permutations of a (half of) string, use a similar approach from: _46 II or Next Permutation.
 */
public class _267 {

    public List<String> generatePalindromes(String s) {
        int odd = 0;
        String mid = "";
        List<String> res = new ArrayList();
        List<Character> list = new ArrayList();
        Map<Character, Integer> map = new HashMap();

        // step 1. build character count map and count odds
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
            odd += map.get(c) % 2 != 0 ? 1 : -1;
        }

        // cannot form any palindromic string
        if (odd > 1) {
            return res;
        }

        // step 2. add half count of each character to list
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();

            if (val % 2 != 0) {
                mid += key;
            }

            for (int i = 0; i < val / 2; i++) {
                list.add(key);
            }
        }

        // step 3. generate all the permutations
        getPerm(list, mid, new boolean[list.size()], new StringBuilder(), res);

        return res;
    }

    // generate all unique permutation from list
    void getPerm(List<Character> list, String mid, boolean[] used, StringBuilder sb,
                 List<String> res) {
        if (sb.length() == list.size()) {
            // form the palindromic string
            res.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            // avoid duplication
            if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) {
                continue;
            }

            if (!used[i]) {
                used[i] = true;
                sb.append(list.get(i));
                // recursion
                getPerm(list, mid, used, sb, res);
                // backtracking
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

}
