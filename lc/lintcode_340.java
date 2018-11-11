class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
    // write your code here    
        int[] cnt = new int[256];
          char[] sc = s.toCharArray();
    
        int ans = 0;
        int sum = 0;
    
    
        for (int l = 0, r = 0; r < s.length(); r++) {
            cnt[sc[r]]++;
            if (cnt[sc[r]] == 1) {
                sum++;
            }
            while (sum > k) {
                cnt[sc[l]]--;
                if (cnt[sc[l]] == 0) {
                    sum--;
                }
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

}