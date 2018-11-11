public class Solution {
    /**
     * @param n: an unsigned integer
     * @return: the number of â€™1' bits
     */
    public int hammingWeight(int n) {
        // write your code here
        if(n == 0) return 0;
        int count = 1;
        while((n&(n-1)) != 0){
            n = n&(n-1);
            count++;
        }
        return count;
    }
}//ons1