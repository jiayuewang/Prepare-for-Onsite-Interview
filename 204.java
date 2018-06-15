
// /**
//  * 204. Count Primes
//  *
//  * Description:

//  Count the number of prime numbers less than a non-negative number, n.

//  Hint:

//  Let's start with a isPrime function. To determine if a number is prime, we need to check if it is not divisible by any number less than n.
//  The runtime complexity of isPrime function would be O(n) and hence counting the total prime numbers up to n would be O(n2). Could we do better?

//  As we know the number must not be divisible by any number > n / 2,
//  we can immediately cut the total iterations half by dividing only up to n / 2. Could we still do better?

//  Let's write down all of 12's factors:

//  2 × 6 = 12
//  3 × 4 = 12
//  4 × 3 = 12
//  6 × 2 = 12
//  As you can see, calculations of 4 × 3 and 6 × 2 are not necessary. Therefore, we only need to consider factors up 
//to √n because,
//  if n is divisible by some number p, then n = p × q and since p ≤ q, we could derive that p ≤ √n.

//  Our total runtime has now improved to O(n1.5), which is slightly better. Is there a faster approach?

//  public int countPrimes(int n) {
//      int count = 0;
//      for (int i = 1; i < n; i++) {
//         if (isPrime(i)) count++;
//      }
//  return count;
//  }

//  private boolean isPrime(int num) {
//      if (num <= 1) return false;
//      // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
//      // to avoid repeatedly calling an expensive function sqrt().
//      for (int i = 2; i * i <= num; i++) {
//         if (num % i == 0) return false;
//      }
//      return true;
//  }

//  The Sieve of Eratosthenes is one of the most efficient ways to find all prime numbers up to n.
//  But don't let that name scare you, I promise that the concept is surprisingly simple.

//  Sieve of Eratosthenes: algorithm steps for primes below 121. "Sieve of Eratosthenes Animation" by SKopp is licensed under CC BY 2.0.
//  We start off with a table of n numbers. Let's look at the first number, 2. We know all multiples of 2 must not be primes, so we mark them off as non-primes. Then we look at the next number, 3. Similarly, all multiples of 3 such as 3 × 2 = 6, 3 × 3 = 9, ... must not be primes, so we mark them off as well. Now we look at the next number, 4, which was already marked off.
//  What does this tell you? Should you mark off all multiples of 4 as well?
//  4 is not a prime because it is divisible by 2, which means all multiples of 4 must also be divisible by 2 and were already marked off.
//  So we can skip 4 immediately and go to the next number, 5.
//  Now, all multiples of 5 such as 5 × 2 = 10, 5 × 3 = 15, 5 × 4 = 20, 5 × 5 = 25, ... can be marked off.
//  There is a slight optimization here, we do not need to start from 5 × 2 = 10. Where should we start marking off?

//  In fact, we can mark off multiples of 5 starting at 5 × 5 = 25, because 5 × 2 = 10 was already marked off by multiple of 2,
//  similarly 5 × 3 = 15 was already marked off by multiple of 3.
//  Therefore, if the current number is p, we can always mark off multiples of p starting at p2, then in increments of p: p2 + p, p2 + 2p, ...
//  Now what should be the terminating loop condition?

//  It is easy to say that the terminating loop condition is p < n, which is certainly correct but not efficient. Do you still remember Hint #3?
//  Yes, the terminating loop condition can be p < √n, as all non-primes ≥ √n must have already been marked off. When the loop terminates,
//  all the numbers in the table that are non-marked are prime.

//  The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n).
//  For the more mathematically inclined readers, you can read more about its algorithm complexity on Wikipedia.

//  public int countPrimes(int n) {
//      boolean[] isPrime = new boolean[n];
//      for (int i = 2; i < n; i++) {
//         isPrime[i] = true;
//     }

//      // Loop's ending condition is i * i < n instead of i < sqrt(n)
//      // to avoid repeatedly calling an expensive function sqrt().

//      for (int i = 2; i * i < n; i++) {
//          if (!isPrime[i]) continue;
//          for (int j = i * i; j < n; j += i) {
//             isPrime[j] = false;
//          }
//     }
//      int count = 0;
//      for (int i = 2; i < n; i++) {
//         if (isPrime[i]) count++;
//      }

//     return count;
//  }
//  */
public class Solution {
    /**
     * @param n: a integer
     * @return: return a integer
     */
    public int countPrimes(int n) {
        // write your code here
        int count = 0;
        for(int i = 1; i < n; i++){
            if(isPrimes(i)) count++; 
        }
        return count;
    }
    public boolean isPrimes(int i){
        if (i <= 1) {
           return false; 
        }
        for (int j = 2; j*j <= i; j++) {// 注意这个取的到等号
            if (i % j == 0) {// without reminders
                return false;
            }
        }
        return true;
    }
}
  

// Specific operation: first put each number of 2~n into the table, then draw a circle above 2 and then draw down the other multiples of 2; the first number that is neither circled nor scratched is 3,
// Circle it and move the other multiples of 3; the first number that is neither circled nor scratched is 5, circle it, and draw 5 other multiples... and so on,
// Until all the numbers less than or equal to n are drawn or crossed. At this time, the numbers drawn in the table and undrawn are just prime numbers less than n.

// As can be seen from the above Eladoss sieve method, we only need to iterate through [2, root n) because if it exceeds the number if it is not a prime, then the number in front of the factor has been deleted.

// 西元前250年，希腊数学家厄拉多塞(Eeatosthese)想到了一个非常美妙的质数筛法，减少了逐一检查每个数的的步骤，
// 可以比较简单的从一大堆数字之中，筛选出质数来，这方法被称作厄拉多塞筛法(Sieve of Eeatosthese)。

// 具体操作：先将 2~n 的各个数放入表中，然后在2的上面画一个圆圈，然后划去2的其他倍数；第一个既未画圈又没有被划去的数是3，
// 将它画圈，再划去3的其他倍数；现在既未画圈又没有被划去的第一个数 是5，将它画圈，并划去5的其他倍数……依次类推，
// 一直到所有小于或等于 n 的各数都画了圈或划去为止。这时，表中画了圈的以及未划去的那些数正好就是小于 n 的素数。

//从上面的厄拉多塞筛法可以看出，我们只需遍历[2,根号n],因为超过这里写图片描述部分如果不是素数，则作为因子在前面的数已经被删除了。
