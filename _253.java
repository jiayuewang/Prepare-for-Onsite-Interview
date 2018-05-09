package com.fishercoder.solutions;

import com.fishercoder.common.classes.Interval;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.

 For example,
 Given [[0, 30],[5, 10],[15, 20]],
 return 2.
 */
public class _253 {

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        // Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length, (a, b) -> a.end - b.end);

        // start with the first meeting, put it to a meeting room
        heap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // get the meeting room that finishes earliest
            Interval interval = heap.poll();

            if (intervals[i].start >= interval.end) {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                interval.end = intervals[i].end;
            } else {
                // otherwise, this meeting needs a new room
                heap.offer(intervals[i]);
            }

            // don't forget to put the meeting room back
            heap.offer(interval);
        }

        return heap.size();
    }
}
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
       // 按xstart 和 ystart 的递增顺序进行排序
       int[] starts = new int [intervals.length];
        int[] ends = new int[intervals.length];
        for(int i= 0; i<intevals.length; i++){
            starts[i] = intervals[i].start;
                ends[i] = intervals[i].end;
        }
        //
        Arrays.sort(starts);
        Arrays.sort(ends);
        int res = 0;
        int end = 0;
        for(int i = 0;i<intrtvals.length; i++){
           if( starts[i] < ends[end]) res++ ;
            else end++;
        }
    
    return res;
}
}
    // start> end  当前有一个会议已经结束，将end往前移
//有一个会议已经结束，房间可以用 新会议开始时间大于房子end时间所以room 不用增加了