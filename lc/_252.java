/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * } nlogn  S1 有交集就不能参加所有meeting room,不是em 所以需要重写方法
 */
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals,(x,y)->x.start- y.start);
        for(int i = 1; i< intervals.length; i++){
            if(intervals[i-1].end > intervals[i].start) {
                return false;//判断开会时间有无交集
            }
        }
        return true;
    }
}
