
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.








/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
## Idea 
* First, the intervals has to be sorted by start time, if not, sort it yourselt 
* Compare every end[i] and start[i+1]

public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (x, y) -> x.start - y.start);
        for (int i = 1; i < intervals.length; i++)
            if (intervals[i-1].end > intervals[i].start)
                return false;
        return true;
    }
}

//arrys.sort -> O(nlogn), for loop -> O(n), so overall time complexity is O(nlogn+n) ~ O(nlogn)
 //space: O(1)
public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
       if(intervals == null || intervals.length == 0) {
           return true;
       }
       Arrays.sort(intervals, new Comparator<Interval>(){
           @Override
           public int compare(Interval i1, Interval i2) {
               return i1.start - i2.start;
           }
       });
       
       Interval front = intervals[0];
       for(int i = 1; i < intervals.length; i++) {
           if(intervals[i].start < front.end ) {
               return false;
           }
           else {
              front = intervals[i]; 
           }
       }
       return true;
    }
}