import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

  class Solution {
    public static int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int res = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int max = dfs(matrix, Integer.MIN_VALUE,i,j,m,n,cache);
                res = Math.max(res, max);
            }
        }
        return res;
    }
    private static int dfs(int[][] matrix, int min, int i, int j , int m, int n, int[][] cache){
        if(i < 0|| j < 0 || i >= m || j >= n || matrix[i][j] <= min){
            return 0;
        }
        if(cache[i][j] != 0){
            return cache[i][j];
        }
        min = matrix[i][j];
        int a = dfs(matrix, min, i-1, j,m, n, cache) +1;
        int b = dfs(matrix, min, i, j-1,m, n, cache) +1;
         int c = dfs(matrix, min, i+1, j,m, n, cache) +1;
         int d = dfs(matrix, min, i, j+1,m, n, cache) +1;
        int max = Math.max(a,Math.max(b, Math.max(c,d)));
        cache[i][j] = max;
        return max;
    }

    public static void main(String[] args) {
       int[][] matrix = {
         {3,4,5},
         {3,2,6},
         {2,2,1}
       }; 
     int res =  longestIncreasingPath(matrix);
    
       System.out.println(res);
    }
}
// + —
// 判断整数
// 判断小数


