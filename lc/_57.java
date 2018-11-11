package com.fishercoder.solutions;

import com.fishercoder.common.classes.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
*/
 // You may assume that the intervals were initially sorted according to their start times.

 // Example 1:
 // Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 // Example 2:
 // Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 // This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 // */
// 题解1:  Comparator
// 新建一个链表res, 引入Comparator类,若newInterval与当前interval没有交集，则按照先后次序加入newInterval和当前interval，加入剩下的interval, 返回res。若newInterval与当前interval有交集，合并成为新的newInterval，处理interval。未返回res，此时newInterval为最后一个interval，装入res。return res。

// Time Complexity：O(nlogn)
// Space Complexity：O(n)
// 代码如下：

    class Solution {
        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
            List<Interval> res = new ArrayList<>();
            for(Interval i : intervals) {
                if( i.start <= newInterval.end && i.end >= newInterval.start) {
                    newInterval.start = Math.min(i.start, newInterval.start);
                    newInterval.end = Math.max(i.end, newInterval.end);
                } else {
                    res.add(i);//不存在gap，或者合并区间与后面区间没有gap，添加到结果集  
                }
            }
            res.add(newInterval);//将最后一个区间加入到结果集
            Collections.sort(res, new Comparator<Interval>() {//对原区间集合排序  
                public int compare(Interval a, Interval b) {
                    return a.start - b.start;//使用匿名类实现对区间的排序
                }
            });
            
            return res;
        }
    }
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_4D7F743A28D37E8336EDA851CBD6DA92B0B85A8BCB470F84E6A9961BF67FCE06_1517733465840_+2018-02-04+12.33.40.png)


// 题解2,Using index
// 遍历所有的区间，如果新区间的末尾小于当前区间的开头，循环结束。如果插入区间的开头大于当前区间的末尾，不产生重叠区域。如果新区间和当前区间有重叠，则更新新区间的开头为两者最小值，新区间的末尾为两者最大值，重叠数加1。指针移向下一个区间。如果重叠数大于0，则删除掉所有的重叠区间， return新生成的interval。
// Time Complexity：O(n)
// Space Complexity：O(n)
// 代码如下：

    public class Solution {
        public List<Interval> insert(List<Interval> intList, Interval newInterval) {
            List<Interval> res = new LinkedList<>();
            int i= 0;//从第一个点开始遍历
            int n=intList.size();
            int nStart=newInterval.start;//定义新的interval的起点和终点的初值并初始化
            int nEnd=newInterval.end;
           while(i<n&&intList.get(i).end<newInterval.start){//当前看到的区间的终点小于要插入interval的起点
               res.add(intList.get(i));//把当前不相关的interval加入至数组中
               i++;
           }if(i==n){
               res.add(newInterval);
               return res;//前面都是有效线段，在最后加入新的线段
           }
            // 如果找到合适的起点线段，则更新nStart的值
            nStart = Math.min(intList.get(i).start, newInterval.start);
            while(i<n&&intList.get(i).start<=newInterval.end){
                nEnd=Math.max(newInterval.end,intList.get(i).end);
                i++;
            }        
            res.add(new Interval(nStart,nEnd));
            while(i<n){
            // add newInterval at the right place
            res.add(intList. get(i++));
            }
            return res;
        }
    }