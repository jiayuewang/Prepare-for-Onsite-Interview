class Solution {
    //大于中位数 从左向右 基数向插入   小于中位数 从右向左 偶数项插入
    // 0 1 2 3 4 5--index
    // 1 2 3 4 5 6 / median = 3
    // 3 6 2 5 1 4
    //中位数最后放
    public void wiggleSort(int[] nums) {
         Arrays.sort(nums);
        int n = nums.length;
        int mid = (n - 1) / 2;
        int index = 0;
        int[] temp = new int[n];
        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid - i];
            if (index + 1 < n) {
                temp[index + 1] = nums[n - 1 - i];
            }
            index += 2;//双数变化
        }
        System.arraycopy(temp, 0, nums, 0, n);
    }
// nlogn n
           
//odd - even

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 324. Wiggle Sort II
 *
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example:
 (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class _324 {

    /**Credit: https://discuss.leetcode.com/topic/41464/step-by-step-explanation-of-index-mapping-in-java
     * TODO: completely understand it.*/
    public void wiggleSort(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;

        int left = 0;
        int i = 0;
        int right = n - 1;

        while (i <= right) {

            if (nums[newIndex(i, n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(i++, n));
            } else if (nums[newIndex(i, n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(i, n));
            } else {
                i++;
            }
        }
    }

    private int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : nums) {
            maxHeap.offer(i);
        }
        while (k-- > 1) {
            maxHeap.poll();
        }
        return maxHeap.poll();
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

}
