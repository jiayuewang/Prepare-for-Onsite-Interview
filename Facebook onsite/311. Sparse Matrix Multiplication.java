
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.
// A[rowA][colA]*B[rowB][colB] = C[rowA][colB] (colA = rowB)

// C[i][j] = A[i][0]*B[0][j] + A[i][1]*B[1][j] +…+A[i][colA-1]*B[colA-1][j];
// 所以我们发现，这其实就是A[i][k]*B[k][j]的叠加。其中k = 0~colA-1

// 所以我们两层for循环先找到A[i][k],
// 这里需要注意如果A[i][k] == 0我们不用后面的for循环了，所以请判断不为0.否则会超时。同理B[k][j].
// 再来一层for循环就有B[k][j]，叠加即可。
     public int[][] multiply(int[][] A, int[][] B) {
        int rowA = A.length;
        int colA = A[0].length;
        int colB = B[0].length;
        int[][] result = new int[rowA][colB];
        if(A == null || B == null || A.length == 0 || B.length == 0 || (A[0].length != B.length)) {
            return result;
        }
        
         for(int i = 0; i < rowA; i++){
            for(int k = 0; k < colA; k++){
                if(A[i][k]!=0){ //row i is not all zero; 
                    for(int j = 0; j < colB; j++){
                        if(B[k][j]!=0){ //colum b is not all zero;
                            result[i][j] += A[i][k]*B[k][j];
                        }
                    }
                }
            }
        }
        return result;
    }      
}


//两个矩阵的乘法仅当第一个矩阵A的行数和另一个矩阵B的列数相等时才能定义。
   //如A是m×n矩阵，B是n×p矩阵，它们的乘积AB是一个m×p矩阵，它的一个元素其中 1 ≤ i ≤ m, 1 ≤ j ≤ p。
   //A[rowA][colA]*B[rowB][colB] = C[rowA][colB] (colA = rowB)


   
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        if(A == null || B == null || A.length == 0 || B.length == 0 || (A[0].length != B.length)) {
            return result;
        }
    //     // int aRows = A.length;
    //     // int aColumns = A[0].length;
    //     // int bRows = B.length;
    //     // int bColumns = B[0].length;
        
        HashMap<Integer, int[]> rowInA = new HashMap<>();     // store non-zero rows in A
        HashMap<Integer, int[]> colInB = new HashMap<>();     // store non-zero cols in B
        
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                if(A[i][j] != 0) {
                    rowInA.put(i, A[i]);
                    break;
                }
            }
        }
        
        for(int j = 0; j < B[0].length; j++) {
            for(int i = 0; i < B.length; i++) {
                if(B[i][j] != 0) {
                    int[] tmp = new int[B.length];
                    for(int k = 0; k <  B.length; k++) {
                        tmp[k] = B[k][j];
                    }
                    colInB.put(j, tmp);
                    break;
                }
            }
        }
        
        for(int i : rowInA.keySet()) {
            for(int j : colInB.keySet()) {
                for(int k = 0; k < A[0].length; k++) {
                    result[i][j] += rowInA.get(i)[k] * colInB.get(j)[k];
                }
            }
        }
        
        return result;
    
    }
    