public class Solution {
    /*
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        int start = 1, end = A.length - 2; // 1.答案在之间，2.不会出界
        while(start < end - 1){// 注意此处还是-1
            int mid = start + (end - start)/2;
            if(A[mid] < A[mid - 1] ){
                end = mid;
            }else if(A[mid] < A[mid + 1]) {
                start = mid;
            }else {
                end = mid;
            }
        }
        if(A[start] < A[end]){
            return end;
        }else{
            return start;
        }
    }
} ologn s1