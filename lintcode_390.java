
//和在数组中find peak element一样，对行和列分别进行二分查找。

//先对行进行二分搜索，对搜到的那一行元素再进行二分搜索寻找peak element
//对找到的element看上下行的同列元素，若相同则返回，若比上小则在上半部分//行继续进行搜索，若比下小则在下半部分的行继续进行搜索
class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        // write your code here
        List<Integer> res = new ArrayList<Integer>();
        if(A == null || A.length == 0 || A[0].length == 0){
            return res;
        }
        //根据题意，第1行和最后一行都不可能是peak，所以从第2行和倒数第2行开始
        int low = 1;
        int high = A.length - 2;

        while(low <= high){
            int mid = low + (high - low) / 2;
            int col = findPeak(mid, A);
            if(A[mid][col] < A[mid - 1][col]){
                high = mid - 1;//peek在low那一端，
            }else if(A[mid][col] < A[mid + 1][col]){
                low = mid + 1;//peek在high那一端
            
            }else{
                res.add(mid);
                res.add(col);//mid是peek了
                break;
            }
        }

        return res;

    }

    private int findPeak(int row, int[][] A){
        int start = 0;
        int end = A[row].length - 1;

        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(A[row][mid] > A[row][mid - 1] && A[row][mid] > A[row][mid + 1]){
                return mid;
            }else if(A[row][mid] < A[row][mid - 1]){
                end = mid;
            }else{
                start = mid;
            }
        }

        if(A[row][start] > A[row][end]){
            return start;
        }else{
            return end;
        }
    }
}