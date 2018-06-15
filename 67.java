public class Solution {
    /**
     * @param num: a non-negative integer
     * @return: one digit
     */
    public int addDigits(int num) {
        // write your code here
        int count = 0;
        int i,j;
        while(num != 0){
            count = count + num%10;
            num = num/10;
        }
        while(count/10 != 0){
            return addDigits(count);
        }
        return count;
    }
}