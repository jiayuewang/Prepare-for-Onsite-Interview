The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.


“汉明距离”，一个较为著名的问题，给定两个整数，求出这两个数字的二进制中位数不同的个数。比如上面的1和4，在第0位和第2位数字不同，因此这个汉明距离就是2。


解法一：分别列出两个数字的二进制形式，并放在两个数组中，数组的索引级二进制形式中的位数，元素值即位数上对应的值。逐个的比较元素值，从而得出结果。这种方法直白，容易想到，但总感觉效率低下，不那么“高级”，这里我就不列出具体代码了。
解法二：比较两个二进制数字中不一样的数据，其实可以直接将两个数字取异或，然后再求出异或结果的二进制形式中有多少个1即可。


When I start this problem, bit operation jumps out of my mind. And `XOR` is the one to use.
`XOR`: `Exclusive or` or `Exclusive disjunction` is a logical operation that outputs true only when inputs differ (one is true, the other is false).
Thus, comparing the inputs by XOR then counting the number of `1`s would be the solution for this one.


public class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int count = 0;
        while (xor != 0) {
            xor = (xor - 1) & xor;
             count++;
        }
        return count;
    }
}

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.


// The first solution came to my mind is brute forcely iterate through each pair, then sum all Integer.bitCount(x ^ y) like what I mentioned here https://discuss.leetcode.com/topic/72093/java-1-line-solution-d But as you can imagine, it TLE...

// Let's think about this problem another way. We can separate the calculation to do one bit at a time. For example, look at the rightmost bit of all the numbers in nums. Suppose that i numbers have a rightmost 0-bit, and j numbers have a 1-bit. Then out of the pairs, i * j of them will have 1 in the rightmost bit of the XOR. This is because there are i * j ways to choose one number that has a 0-bit and one that has a 1-bit. These bits will therefore contribute i * j towards the total of all the XORs.

// Apply above algorithm to each bit (31 bits in total as specified in the problem), sum them together then we get the answer.

// Reference: http://stackoverflow.com/questions/21388448/sum-of-xor-values-of-all-pairs

public class Solution {
    public int totalHammingDistance(int[] nums) {
        int n = 31;
        int len = nums.length;
        int[] countOfOnes = new int[n];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < n; j++) {
                countOfOnes[j] += (nums[i] >> j) & 1;
            }
        }
        int sum = 0;
        for (int count: countOfOnes) {
            sum += count * (len - count);
        }
        return sum;
    }
}