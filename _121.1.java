
/**
 * 121. Best Time to Buy and Sell Stock
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 *
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 *
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 *
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 *
 * In this case, no transaction is done, i.e. max profit = 0.
 */

class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }  
        int max = 0;
        int p = prices[0];
        
        for(int i = 1; i< prices.length; i++) {
            if(prices[i] > p){
                max = Math.max(max, prices[i] - p);
            }else{
                p = prices[i];
            }
        }
        return max;
       }
//         if(prices.length == 0) {
//             return 0;
//         }
//         int max = 0;
//         int p = prices[0];//基准值
        
//         for(int i=0;i<prices.length;++i){
//             if(prices[i] > p){//如果比基准值大，就相减，看是不是最大差值
    //If it is larger than the reference value, subtract it to see if it is the maximum difference
//                 max = Math.max(max, prices[i] - p);
//             }else{
//                 p = prices[i];//如果他比基准值小就赋值他为基准值再循环If he is less than the baseline value, it is assigned as the baseline value
//             }
//         }
//         return max;
    
}


