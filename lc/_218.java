package com.fishercoder.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
/**A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 *
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

 For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

 The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

 For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

 Notes:

 The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 The input list is already sorted in ascending order by the left x position Li.
 The output list must be sorted by the x position.
 There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]*/

/**This video is super clear and helpful: https://www.youtube.com/watch?v=GSBLe8cKu0s

 Algorithm:
First observation: all the points in the final result come from the four angles that each building has
Scan through the horizontal lines
Use a PriorityQueue to hold each building, and make the PriorityQueue to sort on the height of the buildings
whenever we encounter the start of a building, we push it into the PriorityQueue, whenever we finished scanning that building, we remove it from the PriorityQueue
Also, in the scan process, we’ll keep updating the maxHeight in the PriorityQueue if we find a new maxHeight which means the building will be overshadowed by the new higher one
 

Three edge cases (see the graph illustration in the above video at 12’18”):
when two buildings have the same start point, the one with higher height shows up in the final result
when two buildings have the same end point, the one with higher height shows up in the final result
when the start point of one building is is also the end point of another building, the one with higher height shows up in the final result
 

 We use TreeMap over a normal PriorityQueue:
For the sake of efficiency (better time complexity), we’ll use TreeMap which supports O(logn) for remove() operation, this is the reason we choose TreeMap over a normal PriorityQueue in Java (PriorityQueue supports add() and getMaxVal() in both O(logn) time, however, for remove(), it does NOT.)
But TreeMap in Java supports all the three operations in O(logn) time.*/

// 思路：拿到这道题目我们首先要想的是，需要一个data structure 可以的对我们的数据进行自动排序，按高度从大到小排序，按横坐标从小到大排序，并且在高度变化时会记录临界值以及高度差。因为我们需要记录高度最大值，在以后的所有点都要比最大高度小，所有点放在这个数据结构里无法改变这个最大值，我们需要一种数据结构不但可以insert还可以remove，已得到比它小的点，每一栋楼我们可以拆分成左边的横坐标和对应的高度，右边的横坐标和对应的高度，一个楼拆分成两个点。当遇到两个相同高度时，我们可以认为已经走完整个一栋楼，这是我们将这个楼的高度从我们的数据结构中remove掉，此时需要记录这个被remove掉的楼对应的右边横坐标的仅次于这个楼高度的对应楼的高度。我们还需考虑到两个特殊情况，一，一栋小楼被一栋大楼覆盖，左边轮廓重叠在一起，这时我们需要记录此时小楼的高度即被重叠的点，故我们此时将高度降序排序。二：当右边轮廓重叠时，这时我们可以考虑到使用，降序对这种情况不适用降序，为了把更高的点remove掉，这个case我们需要按照高度升序排序。综上所述，Maxheap是一个好的选择。

// 题解一：使用ArrayList来装每个楼拆分的结点。我们需要一种方法区分重合状态，是左边横坐标还是右边横坐标。为了区分我们可以将坐标的点变成负数，然后对这些点进行排序。左边边界线重合，我们需要降序排列，所以我们可以把高度变成负数进行排序，刚开始放一个高度为0，接下来做一个for-loop，如果我们记录这个楼的边界点小于0，就证明是左边的边界点，所以我们要进行insert操作，如果是正数，我们证明是右边的点所以我们需要进行remove操作，我们还需要一个参照变量来记录它此时高度的最大值。如果高度发生变化，则说明我们找到了一个轮廓点，将当前点的横坐标和此时heap中存的最大高度加入在我们的result数组中。
// Time: O(n^2)
// Space: O(n)
// 代码如下：

    class Solution {
        public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> heightPoint = new ArrayList<>();
        for(int[] bd:buildings) {
            heightPoint.add(new int[]{bd[0], -bd[2]});
            heightPoint.add(new int[]{bd[1], bd[2]});
        }
        Collections.sort(heightPoint, (a, bd) -> {
                if(a[0] != bd[0]) 
                    return a[0] - bd[0];
                return a[1] - bd[1];
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0);//pq为我们的maxheap
        int prev = 0;
        for(int[] h:heightPoint) {
            if(h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int current = pq.peek();//用current来记录当前高度
            if(prev != current) {
                result.add(new int[]{h[0], current});
                prev = current;//更新之前的最大值为我们当前的高度
            }
        }
        return result;
    }
    }


// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_CCD34EEFA01B7D172B0B387B1F32AC42D07FB7B050B6425DA4FA73383C3F045A_1516857927202_+2018-01-24+11.25.07.png)


// 题解二：分治法
// 使用分治法，我们可以在O（nLogn）时间找到轮廓线。 这个想法与Merge Sort类似，将给定的building分成两个子集。 递归扫描，最后将两条轮廓线合并。假设两条线为Line_1和Line_2，比较二者x坐标。 选取带有较小x坐标的条带并将其添加到结果中。 添加的高度被视为来自Line1和Line_2的当前高度的最大值。举例如下：

    
//     height_1, height_2分别作为两条轮廓线，令他们初始值为0. 
//     Line_1 = {(1, 10),  (3, 12),  (9, 0),  (11, 7),  (16, 0)}
//     Line_2 = {(13, 3),  (21, 17), (24, 3), (25, 12),  (30, 0)}
//       Result = {},height_1 = 0, height_2 = 0
//       比较(1, 10)，(13, 3)两个坐标. 因为Line_1第一个点的横坐标超前于Line_2
     
//       height_1 = 10, height_2 = 0
//       Result =   {(1, 10)}
      
//       比较(3, 12) 和(13, 3)
//       Result =  {(1, 11), (3, 12)}   
      
//       比较(9, 0) 和(11, 7)
//       Result =   {(1, 10), (3, 12), (9, 0), (11, 7)}
    
//       比较(16, 0) 和(13, 3)
//       Result =   {(1, 10), (3, 12), (9, 0), (11, 7), (13, 7)}
    
//       比较(16, 0) 和(21, 8)
//       Result =   {(1, 10), (3, 12), (9, 0), (11, 7), (13, 3), (16, 3)}
    
//     Line_1.length<Line_2.length,故jiajiang将Line_2中剩余的点加进来
//     Result =   {(1, 10), (3, 12), (9, 0), (11, 7), (13, 3), (16, 3), 
//                   (21, 17), (24, 3), (25, 12), (30, 0)}
    

// Time: O(nlog(n)),与merge sort类似T(n) = T(n/2) + O(n)
// Space: O(n)
// 代码如下：

    public class Solution {
            public List<int[]> getSkyline(int[][] buildings) {
                    if (buildings.length == 0)
                            return new LinkedList<int[]>();
                    return helper(buildings, 0, buildings.length - 1);
            }
    
            private LinkedList<int[]> helper(int[][] buildings, int a, int b) {
                    if (a < b) {
                            int mid = a + (b - a) / 2;
                            return merge(helper(buildings, a, mid),
                                            helper(buildings, mid + 1, b));//merge 两条线
                    } else {
                        LinkedList<int[]> res = new LinkedList<int[]>();
                        rs.add(new int[] { buildings[a][0], buildings[a][2] });
                        rs.add(new int[] { buildings[a][1], 0 });
                        return rs;
                }
        }
    
            private LinkedList<int[]> merge(LinkedList<int[]> line_1, LinkedList<int[]> line_2) {
                    LinkedList<int[]> res = new LinkedList<int[]>();
                    int height_1 = 0, height_2 = 0;
                    while (line_1.size() > 0 && line_2.size() > 0) {
                    // 比较两个line左边轴坐标并且将较小值值放在result中
                            int x_line = 0, h = 0;
                            if (line_1.getFirst()[0] < line_2.getFirst()[0]) {
                                    x_line = line_1.getFirst()[0];
                                    height_1 = line_1.getFirst()[1];
                                    h = Math.max(height_1, height_2);
                                    line_1.removeFirst();
        // Check for redundant strip, a strip is redundant if it has same height or left as previous
                            } else if (line_1.getFirst()[0] > line_2.getFirst()[0]) {
                                    x_line = line_2.getFirst()[0];
                                    height_2 = line_2.getFirst()[1];
                                    h = Math.max(height_1, height_2);
                                    line_2.removeFirst();
                            } else {
                                    x_line = line_1.getFirst()[0];
                                    height_1 = line_1.getFirst()[1];
                                    height_2 = line_2.getFirst()[1];
                                    h = Math.max(height_1, height_2);
                                    //存储二者高度高度最大值
                                    line_1.removeFirst();
                                    line_2.removeFirst();
                            }
                            if (res.size() == 0 || h != rs.getLast()[1]) {
                                    res.add(new int[] { x_line, h });
                            }
                    }
                    res.addAll(line_1);
                    res.addAll(line_2);
                    return res;
            }
    }