3. Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.
Examples:
Given `"abcabcbb"`, the answer is `"abc"`, which the length is 3.
Given `"bbbbb"`, the answer is `"b"`, with the length of 1.
Given `"pwwkew"`, the answer is `"wke"`, with the length of 3. Note that the answer must be a substring, `"pwke"` is a subsequence and not a substring.

aasdfasdfg





        
    class Solution {
      public int lengthOfLongestSubstring(String s) {
          if(s==null||s.length()==0) return 0;
          HashMap map =new HashMap<>();
          int res = 0;
          for(int i=0,j=0;i<s.length();i++){
              if(map.containsKey(s.charAt(i))){
                j = Math.max(j, map.get(s.charAt(i))+1);
              }
              map.put(s.charAt(i),i);
              res=Math.max(res,i-j+1);
        }
        return res;
    }


    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int start = 0, end = 0, count = 0, max = 0;
            Map<Character, Integer> map = new HashMap<>();
            while(end < s.length()) {
                char c = s.charAt(end);
                map.put(c, map.getOrDefault(c, 0) + 1);
                if(map.get(c) > 1) count++;
                end++;
                while(count > 0) {
                    char tmpc = s.charAt(start);
                    map.put(tmpc, map.get(tmpc) - 1);
                    if(map.get(tmpc) == 1) count--;
                    start++;
                }
                max = Math.max(max, end - start);
            }
            return max;
        }
    }



