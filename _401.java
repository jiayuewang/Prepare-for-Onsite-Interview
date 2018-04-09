package com.fishercoder.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
*/
 Each LED represents a zero or one, with the least significant bit on the right.


 For example, the above binary watch reads "3:25".

 Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

 Example:

 Input: n = 1
 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 Note:
 The order of output does not matter.
 The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 
 
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8,4,2,1};
        int[] nums2 = new int[]{32,16,8,4,2,1};
        for(int i=0;i<=num;i++){
            List<Integer> list1 = generateDigit(nums1,i);
            List<Integer> list2 = generateDigit(nums2,num-i);
            //题中是0-11小时，没有12
            for(int num1:list1){
                if(num1>=12) continue;
                for(int num2:list2){
                    if(num2>=60) continue;
                    res.add(num1+":"+(num2<10?"0"+num2:num2));
                }
            }
        }
        return res;
    }
    private List<Integer> generateDigit(int[] nums, int count){
        List<Integer> res= new ArrayList<>();
        helper(res,nums,count,0,0);
     return res;
}
 private void helper(List<Integer> res, int[] nums,int count,int start, int sum){
     //start 从8开始，start执行完，遍历4，sum值+numi
     if(count == 0){
         res.add(sum);
         return;
     }
     for(int i=start;i<nums.length;i++){
         helper(res,nums,count-1,i+1,sum+nums[i]);
     }
 }
}

//枚举，用backtracking
