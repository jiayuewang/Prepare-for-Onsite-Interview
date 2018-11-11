package com.fishercoder.solutions;

/**
 * 122. Best Time to Buy and Sell Stock II
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * */

public class _122 {
    public static class Solution1 {
        //peak and valley appraoch
        public int maxProfit(int[] prices) {
            int pro = 0;
            int i = 0;
            while (i < prices.length - 1) {
                while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                    i++;
                }
                int valley = prices[i];
                while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                    i++;
                }
                int peak = prices[i];
                pro += peak - valley;
            }
            return pro;
        }
    }

 class Solution {
    public int maxProfit(int[] prices) {//求差值
   if(prices == null || prices.length < 2) return 0;//不能进行交易
        int max=0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]){//
                max += prices[i]-prices[i-1];
            }
            
        }
        return max;
}
}

  
