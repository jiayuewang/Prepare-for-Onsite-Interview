package com.fishercoder.solutions;

import java.util.Arrays;


 //  85. Maximal Rectangle
 
 //  Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 // For example, given the following matrix:

 // 1 0 1 0 0
 // 1 0 1 1 1
 // 1 1 1 1 1
 // 1 0 0 1 0
 // Return 6.

//  思路： 在0-1矩阵中找出面积最大的全1矩阵。这道题可以当作是84题的follow up ,如下图所示，自底面往上的矩阵看成一个直方图.其中每个column的高度就是从底面行开始往上1的数量。我们从左向右走，当遇到0就继续走，当遇到1就向上寻找在连续1的情况下，可以达到的最大高度，返回的面积是当前直方图中最大的矩形面积并且和之前已经计算的面积进行比较，然后求出最大值就是当前得到的面积。如下图所示，在图中只标记1的点，则row一行一行向下读，我们可以将数据转化成：
// 00000——>01100——>00211——>00322——>10400的形式，由此我们可以得出每一行读完的当前行最大矩形面积。我们可以想到两种方法，动态规划和stack。

// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_00979B0B5C14A4A6F6391759875704B4DED9E52B6F93CE72B7F5D481B0CB83E2_1516852687973_+2018-01-24+9.57.37.png)


// 题解1:Stack法
// column从上到下扫一遍，遇到0标记[i]＝0；遇到1标记[i]＝last_[i]+1。我们还两个额外的数组来维护一行中每个位置高度的左右边界。原因是当我们一行一行的遍历数组，维护每个位置到顶部的高度时，如果某位置高度大于０就往回扫描到列首，并且维护一个当前最低的高度，这样就可以求出从这个位置到第一列最大的面积。此时的m,n分别对应row和column长度，时间复杂是O(m*n*n)。这种方法每碰到一个高度不为０的高度就往左搜索找到当前最低的高度然后更新面积，高度将被重复计算，因此我们需要两个额外的数组来维护一行中每个位置高度的左右边界数值。如下图所示，右图为左图矩阵转化图。对一行为底边的矩阵求解复杂度是O(n+n)=O(n)。之后对每一行做同样的操作，算法总时间复杂度是O(m*n)。

// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_00979B0B5C14A4A6F6391759875704B4DED9E52B6F93CE72B7F5D481B0CB83E2_1516684451155_+2018-01-22+11.11.37.png)
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_00979B0B5C14A4A6F6391759875704B4DED9E52B6F93CE72B7F5D481B0CB83E2_1516684459294_+2018-01-22+11.12.13.png)


// Time: O(mn)
// Space: O(n)
// 代码如下：

     
    public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int[] height = new int[matrix[0].length];
        for(int i = 0; i < matrix[0].length; i ++){
            if(matrix[0][i] == '1') height[i] = 1;
        }
        int result = helper(height);//定义一个数组来标记扫过每一行的最大高度
        for(int i = 1; i < matrix.length; i ++){
            currentMaxh(matrix, height, i);
            result = Math.max(result, helper(height));
        }    
        return result;
    }
    
    public int helper(int[] height) {//helper函数用来计算每一行最大高速O(n)
        if(height == null || height.length == 0) return 0;
        int l = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int res = 0;
        for(int i = 0; i <= l; i++){
            int height_new ;//用来记录当前扫描行的高度
         if(i == l ) height_new= 0; 
          else height_new=height[i];
            if(s.isEmpty() || height_new >= height[s.peek()]){
                s.push(i);
            }else{// 小于栈顶元素时
                int a = s.pop();//栈顶元素出栈
                int temp;
                if(s.isEmpty()) temp=i;
                else temp=i - 1 - s.peek();//height[a] * temp为当前直方图中矩形最大面积是多少
                res = Math.max(res, height[a] * temp);//与之前直方图中所得最大面积比较，取最大值
                i--;
            }
        }
        return res;//在所有行遍历完之后，我们可以得出res
    }
        private void currentMaxh(char[][] matrix, int[] height, int j){//currentMaxh用来记录已经扫过连续为1的最大高度
        for(int i = 0; i < matrix[0].length; i ++){
            if(matrix[j][i] == '1') height[i] += 1;
            else height[i] = 0;
        }
    }    
    }


// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_00979B0B5C14A4A6F6391759875704B4DED9E52B6F93CE72B7F5D481B0CB83E2_1516685334337_+2018-01-22+11.28.28.png)


// 题解2:动态规划，我们维持三个数组，分别记录：1，从左到右，出现连续’1’的String的第一个坐标left[i]。2，从右到左，出现连续’1’的String的最后一个坐标right[i]。3， 定义一个height数组，大小是column。我们从第一行开始一行一行地处理，在 ［i，j］处我们获得的最大子矩阵面积是(right(i,j)-left(i,j))*height(i,j)。height统计当前位置向上 ‘1’ 的数量，最后我们想要返回的值，result＝(right[i]-left[i])*height[i]。
// 由此可以得到递推方程：left(i,j) = max(left(i-1,j), current_left)；
// right(i,j) = min(right(i-1,j), current_right);
// height(i,j)=height(i-1,j)+1,in this case, our 2D matrix [i][j]==’1’;
// height(i,j)=0, in this case our 2D matrix [i][j]=’0’.
// Time: O(mn)
// Space: O(n)
// 代码如下：

       public class Solution {
        public int maximalRectangle(char[][] matrix) {
            int result = 0, res, r, l;//定义左右边界分别为r和l
            if(matrix.length > 0){
                int[] height_new = new int[matrix[0].length];
                boolean[] temp = new boolean[matrix[0].length];
                for(int i = 0; i < matrix.length; i++){
                    for(int j = 0; j < matrix[i].length; j++){// 从任意一个方向开始，计算高度
                        if (matrix[i][j] == '1') {
                            height_new[j]++;// 遇到1，累计最大高度
                            temp[j] = false;
                        } else {
                            height_new[j] = 0;//遇到0，整个数组标记为0
                            temp[j] = true;
                        }
                    }
                    for(int j = 0; j < matrix[i].length; j++){
                        if(temp[j]) continue; //如果temp[j]==true，循环继续执行并将左右边界置1
                        r = l = 1;
                        while((j + r < height_new.length)&&(height_new[j + r] >= height_new[j])){
                            if(height_new[j + r] == height_new[j]) //更新最大值并向右继续遍历
                                    temp[j + r] = true;
                            r++;
                        }
                        while((j - l >= 0)&&(height_new[j - l] >= height_new[j])) l++;
                        res = (r + l - 1)*height_new[j]; //计算整个矩形的面积
                        if (res > result) result = res;
                    }
                }
            } return result;
        }
    }