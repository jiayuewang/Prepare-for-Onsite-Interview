256. Paint House

这道题说有n个房子，每个房子可以用红绿蓝三种颜色刷，每个房子的用每种颜色刷的花费都不同，
限制条件是相邻的房子不能用相同的颜色来刷，现在让我们求刷完所有的房子的最低花费是多少。
这题跟House Robber II和House Robber很类似，不过那题不是每个房子都抢，相邻的房子不抢，
而这道题是每个房子都刷，相邻的房子不能刷同一种颜色。而Paint Fence那道题主要考察我们有多少种刷法，
这几道题很类似，但都不一样，需要我们分别区分。但是它们的解题思想都一样，需要用动态规划Dynamic Programming来做，
这道题我们需要维护一个二维的动态数组dp，其中dp[i][j]表示刷到第i+1房子用颜色j的最小花费，递推式为:

dp[i][j] = dp[i][j] + min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3])；

这个也比较好理解，如果当前的房子要用红色刷，那么上一个房子只能用绿色或蓝色来刷，那么我们要求刷到当前房子，
且当前房子用红色刷的最小花费就等于当前房子用红色刷的钱加上刷到上一个房子用绿色和刷到上一个房子用蓝色的较小值，这样当我们算到最后一个房子时，
我们只要取出三个累计花费的最小值即可，

public class Solution {
public int minCost(int[][] costs) {
    if(costs==null||costs.length==0){
        return 0;
    }
    for(int i=1; i<costs.length; i++){
        costs[i][0] += Math.min(costs[i-1][1],costs[i-1][2]);
        costs[i][1] += Math.min(costs[i-1][0],costs[i-1][2]);
        costs[i][2] += Math.min(costs[i-1][1],costs[i-1][0]);
    }
    int n = costs.length-1;
    return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
}
}