二分搜索法的关键在于获得了中间数后，判断下面要搜索左半段还是右半段，
我们观察上面红色的数字都是升序的，由此我们可以观察出规律，如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，
则左半段是有序的，我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了，
public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        if(A == null||A.length == 0) return -1;
        int start = 0, end = A.length-1;

        while(start + 1 < end){//二者相邻时候退出
            int mid = (end - start)/2 + start;
            if(A[mid] == target) return mid;
            if(A[start] < A[mid]){
                if(A[start] <= target && A[mid] > target){
                    end = mid;
                }else{
                    start = mid;
                }
            }else{
                if(A[mid] < target && A[end] >= target){
                    start = mid;
                }else{
                    end = mid;
                }
            }
            
        }
        if(A[start] == target) return start;
        if(A[end] == target) return end;
        return -1;
    }
}

// 0　　1　　2　　 4　　5　　6　　7

// 7　　0　　1　　 2　　4　　5　　6

// 6　　7　　0　　 1　　2　　4　　5

// 5　　6　　7　　 0　　1　　2　　4

// 4　　5　　6　　7　　0　　1　　2

// 2　　4　　5　　6　　7　　0　　1

// 1　　2　　4　　5　　6　　7　　0

// Example
// For [4, 5, 1, 2, 3] and target=1, return 2.

// For [4, 5, 1, 2, 3] and target=0, return -1.

// Challenge
// O(logN) time