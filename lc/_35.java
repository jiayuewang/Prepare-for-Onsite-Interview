  // 变形为查找第一个大于等于target的元素的位置
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length-1;
        while(lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if(nums[mid] >= target) {
                if(mid == 0 || nums[mid - 1] < target) return mid;
                else hi = mid - 1;
            }
            else{
                lo = mid + 1;
            }
        }
        // 在nums中未找到大于等于target的位置，所以target应该放在最后
        return nums.length;
    }
    

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left <= right){//相等的时候也要比较
            int mid = (right - left)/2 + left;
            if(nums[mid] == target){
                 return mid;
            }else if(nums[mid] < target){
                left = mid +1;
            }else{
                 right = mid -1;
            }
        }
       return left;
       
    }
}
 //Arrays.binaryserach
/**
 * 35. Search Insert Position
 *
 * Given a sorted array and a target value,
 * return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Here are few examples.
 [1,3,5,6], 5 → 2
 [1,3,5,6], 2 → 1
 [1,3,5,6], 7 → 4
 [1,3,5,6], 0 → 0
 */

public class _35 {

    public int searchInsert(int[] A, int target) {
        int len = A.length;
        if (len == 0) {
            return 0;
        } else {
            for (int i = 0; i < len; i++) {
                if (A[0] > target) {
                    return 0;
                } else if (A[len - 1] < target) {
                    return len;
                } else if (A[i] == target) {
                    return i;
                } else if (A[i] < target && A[i + 1] > target) {
                    return i + 1;
                }
            }
            return len;
        }
    }

}
