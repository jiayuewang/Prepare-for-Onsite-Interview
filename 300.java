
import java.util.Arrays;

// /**
//  * 300. Longest Increasing Subsequence
//  *
//  * Given an unsorted array of integers, find the length of longest increasing subsequence.
//  For example,
//  Given [10, 9, 2, 5, 3, 7, 101, 18],
//  The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
//  Note that there may be more than one LIS combination, it is only necessary for you to return the length.

//  Your algorithm should run in O(n2) complexity.

//  Follow up: Could you improve it to O(nlogn) time complexity?
//  */

我们维护一个一维dp数组，其中dp[i]表示以nums[i]为结尾的最长递增子串的长度，对于每一个nums[i]，
我们从第一个数再搜索到i，如果发现某个数小于nums[i]，我们更新dp[i]，更新方法为dp[i] = max(dp[i], dp[j] + 1)，
即比较当前dp[i]的值和那个小于num[i]的数的dp值加1的大小，
我们就这样不断的更新dp数组，到最后dp数组中最大的值就是我们要返回的LIS的长度，

We maintain a one-dimensional dp array where!dp[i] represents the length of the longest incrementing substring !ending with 
nums[i]. For each nums[i],
We search for i from the first number. If we find that a number is less than nums[i],
we update dp[i] with dp[i] = max(dp[i], dp[j] + 1 ),
That is, compare the current dp[i] value with the dp value less than num[i] plus one.
We continue to update the dp array so that the maximum value in the last dp array is the length of the LIS we want to return.


public class _300 {

    public static class Solution1 {

        /**
         * credit: https://discuss.leetcode.com/topic/28719/short-java-solution-using-dp-o-n-log-n
         * The idea is that as you iterate the sequence,
         * you keep track of the minimum value a subsequence of given length might end with,
         * for all so far possible subsequence lengths.
         * So dp[i] is the minimum value a subsequence of length i+1 might end with.
         * Having this info, for each new number we iterate to,
         * we can determine the longest subsequence where it can be appended using binary search.
         * The final answer is the length of the longest subsequence we found so far.
         */
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;
            for (int x : nums) {
                /**Java Doc of this binarySearch API:
                 * @return index of the search key, if it is contained in the array
                 *         within the specified range;
                 *         otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
                 *         <i>insertion point</i> is defined as the point at which the
                 *         key would be inserted into the array: the index of the first
                 *         element in the range greater than the key,
                 *         or <tt>toIndex</tt> if all
                 *         elements in the range are less than the specified key.  Note
                 *         that this guarantees that the return value will be &gt;= 0 if
                 *         and only if the key is found.*/
                int index = Arrays.binarySearch(dp, 0, len, x);
                if (index < 0) {
                    index = -(index + 1);
                }
                dp[index] = x;
                if (index == len) {
                    len++;
                }
            }
            return len;
        }
    }
}


我们维护一个一维dp数组，其中dp[i]表示以nums[i]为结尾的最长递增子串的长度，对于每一个nums[i]，我们从第一个数再搜索到i，如果发现某个数小于nums[i]，我们更新dp[i]，更新方法为dp[i] = max(dp[i], dp[j] + 1)，即比较当前dp[i]的值和那个小于num[i]的数的dp值加1的大小，我们就这样不断的更新dp数组，到最后dp数组中最大的值就是我们要返回的LIS的长度，