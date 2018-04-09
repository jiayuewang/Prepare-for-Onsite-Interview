package com.fishercoder.solutions;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
*/
 For example:

 Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

 Follow up:
 Could you do it without any loop/recursion in O(1) runtime?

 Hint:

 A naive implementation of the above process is trivial. Could you come up with other methods?
 What are all the possible results?
 How do they occur, periodically or randomly?
 You may find this Wikipedia article: https://en.wikipedia.org/wiki/Digital_root useful.


//不断相加知道剩下的是个位数
class Solution {
    public int addDigits(int num) {

        int sum = 0;
        while (num != 0 ){
         sum += sum % 10;
         num /= 10;
     }
        if (sum >= 10) {
            return addDigits(sum);
        }else {
            return sum;
        }
    }
    
}
// O1 O1

