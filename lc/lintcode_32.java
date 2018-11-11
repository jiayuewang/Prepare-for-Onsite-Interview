public class Solution {
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
       * @param source  : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
        // write your code here
        int[] cntT = new int[256];
        int numT = 0;
        for (char item : target.toCharArray()) {
            cntT[item]++;
            if (cntT[item] == 1) {
                numT++;
            }
        }

        int numS = 0;
        int ans = Integer.MAX_VALUE;
        String minStr = "";

        int[] cntS = new int[256];
        char[] sc = source.toCharArray();

        for (int l = 0, r = 0; r < source.length(); r++) {
            cntS[sc[r]]++;
            if (cntT[sc[r]] == cntS[sc[r]]) {
                numS++;
            }
            while (numS >= numT) {
                if (ans > r - l + 1) {
                    ans = Math.min(ans, r - l + 1);
                    minStr = source.substring(l, r + 1);
                }
                if (cntT[sc[l]] == cntS[sc[l]]) {
                    numS--;
                }
                cntS[sc[l]]--;
                l++;
            }
        }
        return minStr;
    }
}