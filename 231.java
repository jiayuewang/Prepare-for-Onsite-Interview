public class Solution {
    /**
     * @param n: an integer
     * @return: if n is a power of two
     */
    public boolean isPowerOfTwo(int n) {
        // Write your code here
        if (n <= 0) {
            return false;
        }
        return (n & (n-1)) == 0;// n & (n - 1) == 0;这个是错误的判断==0之前要加括号因为&的优先级没有==大
    }
}
//O1