

class Solution {  
        public int maxProduct(int[] nums) {

    int firstNeg = 0;
        int p = nums[0];
        int maxP = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (p < 0 && firstNeg == 0) {
                firstNeg = p;
            }
            
            if (nums[i - 1] == 0 && nums[i] != 0) {
                p = nums[i];
                firstNeg = 0;
            } else {
                p = p*nums[i];
            } 
            
            if (p < 0 && firstNeg < 0) {
                maxP = Math.max(maxP, p/firstNeg);
            } else {
                maxP = Math.max(maxP, p);
            }
        }

        return maxP;
    }  // on; s1
