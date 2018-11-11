On On
递推公式dp[i] = max(num[i] + dp[i - 2], dp[i - 1]),
 由此看出我们需要初始化dp[0]和dp[1]，
其中dp[0]即为num[0]，dp[1]此时应该为max(num[0], num[1])，

public class Solution {
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
//这个问题是 因为  最后的钱数 可能大于Integer.MAX
    public long houseRobber(int[] A) {
        // write your code here
        int n = A.length;
        if(n == 0)
            return 0;
        long []res = new long[n+1];
        
        
        res[0] = 0;
        res[1] = A[0];
        for(int i = 2; i <= n; i++) {
            res[i] = Math.max(res[i-1], res[i-2] + A[i-1]);
        }
        return res[n];
    }
    2222，
    滚动数组优化空间复杂度--> O1
    public long houseRobber(int[] A) {
        // write your code here
        int n = A.length;
        if(n == 0)
            return 0;
        long []res = new long[2];
        
        
        res[0] = 0;
        res[1] = A[0];
        for(int i = 2; i <= n; i++) {
            res[i%2] = Math.max(res[(i-1)%2], res[(i-2)%2] + A[i-1]);
        }
        return res[n%2];
    }
}

滚动数组】 可以想象成显示屏，
对于有很多的数字来说，每次只显示有限的数字，
用完（显示完）就向后移动一位，显示的数量不变，
但是在卡内存比较紧的题中，可以节省很多空间。

 

  最典型的就是斐波那契数列，

  、普通的求解方法不外乎就是用递推式f[i]=f[i-1]+f[i-2]，但是这个如果数据量大的话会爆内存，而用滚动数组的方法可以用3个单位大小的空间求得解，这样就节省了很多的空间。