package com.fishercoder.solutions;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Note:
 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.

 */
class Solution {// O nlogn S1
    public int findDuplicate(int[] nums) {
        int min = 0;
        int max = nums.length -1;
        while(min <= max) {
            int mid=(max-min)/2 + min;
            int count =0;
        for(int i = 0; i < nums.length; i++ ){
            if(nums[i] <= mid){//利用下标，当有重复下标小于等于mid
                count ++;
            }
        }
            if(count > mid) {
                max = mid -1;
            }else{
                min = mid +1;
            }
    }
        return min;
}
}
    // 142,linked list cycle
