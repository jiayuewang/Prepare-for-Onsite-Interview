
public class Solution {
    /**
     * @param dividend the dividend
     * @param divisor the divisor
     * @return the result
     */
     
    //  这道题让我们求两数相除，而且规定我们不能用乘法，除法和取余操作，那么我们还可以用另一神器位操作Bit Operation，思路是，如果被除数大于或等于除数，则进行如下循环，定义变量t等于除数，定义计数p，当t的两倍小于等于被除数时，进行如下循环，t扩大一倍，p扩大一倍，然后更新res和m。
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE; //等于0也是返回最大整数
        }
        if (dividend == 0) {
            return  0;//上边没有return说明除数也不是0了，就不用考虑了
            
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == Integer.MAX_VALUE && divisor == -1){
            return Integer.MIN_VALUE;
        }
        boolean isNegative = (dividend < 0 && divisor > 0) || (divisor < 0 && dividend > 0 );
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int res = 0;
        while( a >= b){
            int shift = 0;
        }


                             
//         long a = Math.abs((long)dividend);
//         long b = Math.abs((long)divisor);
//         int result = 0;
//         while(a >= b){
//             int shift = 0;
//             while(a >= (b << shift)){
//                 shift++;
//             }
//             a -= b << (shift - 1);  //减去减法求商的次数
//             result += 1 << (shift - 1);
//         }
//         return isNegative? -result: result;
//     }
// }



题目的意思简单明了，就是要求不使用乘法、除法和取余mod，输入两个整数，输出除法操作的结果。

出去乘除法，剩下的只有加减法和位运算，这是不难想到的，而直接使用减法，对被除数逐次减去除数大小的值，记录被减次数，肯定是可以得出和除法操作一样的结果，但该方法比较傻瓜，且会超时，时间复杂度为O(n)。

使用位运算可以做到O(logn)的复杂度，但要一步想到具体操作却也不那么简单，首先，我们知道任何一个整数都可以表示成以2的幂为底的一组基的线性组合，即num = flag0 * 2^0 + flag1 * 2^1 + flag2 * 2^2 + ... + flagn * 2^n 其中，flag0, flag1, flag2, ..., flagn 取值为0 & 1。

基于以上事实，如果令：dividend / divisor = num，则有：

dividend = divisor * num = divisor * (flag0 * 2^0 + flag1 * 2^1 + flag2 * 2^2 + ... + flagn * 2^n)

对于除数，使用移位操作<<使其每次翻倍，从而减少减法求商的次数。以下是步骤：

当被除数大于除数时，对除数乘2（代码中使用变量step用于记录每次除数乘2），直到step大于被除数为止。记录移位操作的次数i。
如果被除数大于除数，那么被除数减去step。直到被除数小于除数。保存结果。
输出结果result。