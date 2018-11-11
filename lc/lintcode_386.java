public class Solution {
    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
        // write your code here
        // map from character's ASCII to its last occured index
   public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int[] cnt = new int[256];
        char[] sc = s.toCharArray();//方便一个一个比较

        int ans = 0;
        int sum = 0;
       for (int l = 0, r = 0; r < s.length(); r++) {
            cnt[sc[r]]++;//每读取一个计数字符，count都加1
            if (cnt[sc[r]] == 1) {//第一次出现时候
                sum++;//sum记录不同字符有多少个，出现第一个的时候 ++
            }
            while (sum > k) {//超过了之后
                cnt[sc[l]]--;//j要向前移动了，count减少。
                if (cnt[sc[l]] == 0) {//当这个字符出现次数为0了就sum减少
                    sum--;
                }
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
