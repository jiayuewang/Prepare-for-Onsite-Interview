使用 PriorityQueue 的版本
假设每个数组长度为 n，一共 k 个数组。
时间复杂度 O(nklogk)O(nklogk)
空间复杂度 O(k)O(k)

class Pair {
    int row, col;
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Solution {
    /**
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        int k = intervals.size();
        PriorityQueue<Pair> queue = new PriorityQueue<>(
            k,new Comparator<Pair>(){
                public int compare (Pair e1, Pair e2){
                    return intrtvals.get(e1.row)
                }
            })
        PriorityQueue<Pair> queue = new PriorityQueue<>(
          k,
          new Comparator<Pair>() {
            public int compare(Pair e1, Pair e2) {
                return intervals.get(e1.row).get(e1.col).start - 
                  intervals.get(e2.row).get(e2.col).start;
            }
          }
        );
        
        for (int i = 0; i < intervals.size(); i ++) {
            if (intervals.get(i).size() > 0) {
                queue.add(new Pair(i, 0));
            }
        }
        
        List<Interval> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            result.add(intervals.get(pair.row).get(pair.col));
            pair.col++;
            if (pair.col < intervals.get(pair.row).size()) {
                queue.add(pair);
            }
        }
        
        return merge(result);
    }
    
    private List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals;
        }
        
        List<Interval> result = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals) {
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        // kickoff the last interval
        result.add(new Interval(start, end));
        
        return result;
    }
}