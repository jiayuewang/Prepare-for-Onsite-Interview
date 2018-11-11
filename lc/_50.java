package com.fishercoder.solutions;

/**
 * 50. Pow(x, n)
 *
 * Implement pow(x, n).

 Example 1:

 Input: 2.00000, 10
 Output: 1024.00000

 Example 2:

 Input: 2.10000, 3
 Output: 9.26100
 */
//递归每次除以2进行分解,递归中只算一次n/2。需要注意的是当n为负数时相当于求(1/x)的n次方
public solution {

  public static class Solution1 {
    /**
     * Time: O(logn)
     * Space: O(logn)//递归所以调用空间？？求sugar同学解答
     */
    public double myPow(double x, int n) {
      long N = n;
      if (N < 0) {
        x = 1 / x;
        N = -N;
      }
      return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
      if (n == 0) {
        return 1.0;
      }
      double half = fastPow(x, n / 2);
      if (n % 2 == 0) {
        return half * half;
      } else {
        return half * half * x;
      }
    }
  }

  public static class Solution2 {
    /**
     * Time: O(logn)
     * Space: O(1)
     */
    public double myPow(double x, int n) {
      long N = n;
      if (N < 0) {
        x = 1 / x;
        N = -N;
      }
      double answer = 1;
      double currentProduct = x;
      for (long i = N; i > 0; i /= 2) {
        if (i % 2 == 1) {
          answer = answer * currentProduct;
        }
        currentProduct *= currentProduct;
      }
      return answer;
    }
  }
}
