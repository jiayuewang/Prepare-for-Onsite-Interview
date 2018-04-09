package com.fishercoder.solutions;

/**
 * 123. Best Time to Buy and Sell Stock III
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
*/

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 

class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2=Integer.MIN_VALUE;
        int sell1 = 0,sell2 = 0;
        for(int price : prices){
              sell2= Math.max(sell2,buy2+price);
            buy2 = Math.max(buy2,sell1-price);//已经得到的钱减去利润
          
            
            sell1 = Math.max(sell1,buy1+price);//最大利润，buy1买的钱加上当前价值卖的钱
            buy1 = Math.max(buy1,-price); //买 所以收入是负数
            
        }
        return sell2;
    }
}
// On O1