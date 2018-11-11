public class Solution {
    /**
     * @param digits: a number represented as an array of digits
     * @return: the result
     */
    public int[] plusOne(int[] digits) {
        // write your code here
        int carry = 1;//要加的这个1
       for(int i = digits.length-1;i >= 0 && carry > 0; i--){
           int sum = digits[i] + carry;
           digits[i] = sum%10;
          carry = sum/10;
       }
       if (carry == 0 ) return digits;
       int[] res = new int[digits.length+1]; 
       res[0] = 1;
       for(int i = 0; i< res.length;i++){
           res[i] = digits[i-1];
       }
       return res;
       
    }
}
//On O1


   



