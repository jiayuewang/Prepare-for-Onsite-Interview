package com.fishercoder.solutions;

/**
 * 69. Sqrt(x)
 *
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */
验证是否非完全平方数，开方
二分法logn
牛顿法n

class Solution {
    //二分法
    public int mySqrt(int x) {
        if(x <= 0) return 0;
        int low =1; int high =x;
        while(low <= high) {
            long mid = (high - low)/2+low;//每次initial 新的， 不能拿出来
            if(mid*mid == x){
                return(int) mid;
            }else if (mid*mid < x) {
                low = (int) mid+1;
            }else{
                high = (int)mid -1;
            }
        }
        if(high * high < x) return (int)high;
        else return (int)low;

    }
}
Ologn o1
