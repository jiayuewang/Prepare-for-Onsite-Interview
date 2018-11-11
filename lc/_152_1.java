

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



    2.

    int maxProduct(int A[], int n) {
    // store the result that is the max we have found so far
    int r = A[0];

    // imax/imin stores the max/min product of
    // subarray that ends with the current number A[i]
    for (int i = 1, imax = r, imin = r; i < n; i++) {
        // multiplied by a negative makes big number smaller, small number bigger
        // so we redefine the extremums by swapping them
        if (A[i] < 0)
            swap(imax, imin);

        // max/min product for the current number is either the current number itself
        // or the max/min by the previous number times the current one
        imax = max(A[i], imax * A[i]);
        imin = min(A[i], imin * A[i]);

        // the newly computed max value is a candidate for our global result
        r = max(r, imax);
    }
    return r;
}
