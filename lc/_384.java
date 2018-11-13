/**384. Shuffle an Array
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class _384 {

    public static void main(String... strings) {
        int[] nums = new int[]{1, 2, 3};
        Solution test = new Solution(nums);
    }

    class Solution {
    private int[] nums;
    private Random rmd;
    public Solution(int[] nums) {
        this.nums = nums;
      rmd = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (nums == null) return null;
      int[] clo = nums.clone();//上面私有变量是不能变的
      for (int i = 1; i < clo.length; i++){//从一开始--> 与之前的比
        int random = rmd.nextInt(i+1);// 0-n 取出
        swap(clo, i, random);
      }
      return clo;
    }
  private void swap(int[] clone, int i, int j){
    int temp = clone[i];
    clone[i] = clone[j];
    clone[j] = temp;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

这是洗牌的一种高效的标准方法：遍历每一个元素，并且随机选择一个从它开始的位置，与这个位置交换。可以证明任意一个元素随机到任意一个位置的概率都是1/n。怎么证明呢？用数学归纳法：

1）当只有一个元素的时候，显然满足条件（为了保险，可以验证当有两个元素的时候也满足条件）；

2）假设当有n个元素的时候满足条件（n >= 1），那么当有n+1个元素的时候呢？第一个元素显然有1/(n+1)的概率被留在第一位，有n/(n+1)的概率和后面的元素发生交换。假设和第m个元素发生了交换， 而由于2 <= m <= n+1，所以第m个元素被调换到第一个位置的概率都是1/(n+1)。那么从第二个位置到最后一个位置上的情况呢？根据归纳假设，这n个元素留在每个位置上的概率都是1/n，又由于有n/(n+1)的概率发生这种情况，所以每个元素留在每个位置上的概率都是1/n * n/(n+1) = 1/(n+1)。

该算法的时间复杂度是O(n)，空间复杂度也是O(n)。