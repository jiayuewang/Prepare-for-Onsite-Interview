class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for( int num : nums){
            heap.offer(num);
            if( heap.size() > k){
                heap.poll();
            }
        }
        return heap.poll();
        
    }
}
///  nlogk  n
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.
OnO1
 */ Solution
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length - 1, index = nums.length - k;
        while (start < end) {
            int pivot = partion(nums, start, end);
            if (pivot < index) start = pivot + 1; 
            else if (pivot > index) end = pivot - 1;
            else return nums[pivot];
        }
        return nums[start];
    }
    
    private int partion(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left+1;
        int r = right;
        while(l <= r){
            if(nums[l] < pivot && nums[r] > pivot){
                swap(nums, l++, r--);
            }
            if(nums[l] >= pivot) l++;
            if(nums[r] <= pivot) r--;
        }
        swap(nums, left, r);
        // 3 4 6 5 2 1 
        // 5 4 6 3 2 1 
        return r;
        
        }
        private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
//quick selected递归 or 非递归
//  PQ

//比如把3作为中心点，大于3的放在前面，小于的放在后面，遍历3以后的数字