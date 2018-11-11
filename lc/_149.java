package com.fishercoder.solutions;

import com.fishercoder.common.classes.Point;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
// 题解1:Brute Force
// n个点总共构成n*(n-1)/2条直线，然后对每条直线看看是有多少点在直线上，记录最大值，最后返回结果。而判断一个点i在j,k构成的直线上的条件是points[k].y-points[i].y)*(points[j].x-points[i].x)==(points[j].y-points[i].y)*(points[k].x-points[i].x)。算法总共是三层循环.
// Time complexity: O(n^3)
// Space complexity: O(1)
// 代码如下：

      public class Solution {
        public int maxPoints(Point[] points) {
            int result = 0, n = points.length;
            for (int i = 0; i < n; ++i) {
                int inSame = 1;
                for (int j = i + 1; j < n; ++j) {
                    int newResult = 0;
                    long a.x = points[i].x, a.y = points[i].y;
                    long b.x = points[j].x, b.y = points[j].y;
                    if (a.x == b.x && a.y == b.y) {
                        ++inSame;
                        continue;
                    }for (int k = 0; k < n; ++k) {
                        int c.x = points[k].x, c.y = points[k].y;
                        if (a.x*b.y + b.x*c.y + c.x*a.y - c.x*b.y - b.x*a.y - a.x * c.y == 0) {
                            ++newresultult;
                        }
                    }
                    result = Math.max(result, newResult);
                }
                result = Math.max(result, inSame);
            }
            return result;
        }
    }
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_136CEE42F6735609A80BD7B50B22D2DC1484204B67F412B906BB4A6255C3B5ED_1517194302284_+2018-01-28+8.47.23.png)


// 题解2：HashMap空间换时间
// 哈希表来记录斜率和共线点个数之间的映射，对每个点建立map，斜率是key。   计算斜率分数时，用gcd法。我们还需要顶一个变量duplicate来记录重合点的个数，与哈希表中数值相加，共线点个数总和。
// Time complexity: O(n^2)
// Space complexity: O(n)
// 代码如下：

      public class Solution{
            public int maxPoints(Point[] points) {
                if (points==null) return 0;
                if (points.length<=2) return points.length;
                int res=0;
                for (int i=0;i<points.length;i++){ 
                    Map<String,Integer> map = new HashMap<>();
               //HashMap第一遍遍历所有点，第二遍还是遍历所有点，只要两个点不是同一个点，我们就求出这两两个点的斜率
                 //key用来存斜率，value用来存当前斜率下点的个数
                    int inSame=0;//定义两个变量用来表示两点重合的情况和在x轴上的情况
                    int xAxis =0;
                     int l = points.length;
                for (int j = i + 1; j < l; j++) {
                    int x = points[i].x - points[j].x;//分子
                    int y = points[i].y - points[j].y;//分母
                    if (x == 0 && y == 0) {
                        inSame++;
                        continue;
                    }
                    int gcd = helper(x, y);
                    x /= gcd;
                    y /= gcd;
                    // 用string来存储斜率
                    String slope = String.valueOf(x) + String.valueOf(y);
                    int count = map.getOrDefault(slope, 0);
                    count++;
                    map.put(slope, count);
                    xAxis = Math.max(xAxis, count);
                }
                res = Math.max(res, xAxis + inSame + 1);
            }
            return res;
        }
        public int helper(int x, int y) {//最大公约数函数，辗转相除法
            if (y == 0) return x;
            return helper(y, x % y);
        }
      }