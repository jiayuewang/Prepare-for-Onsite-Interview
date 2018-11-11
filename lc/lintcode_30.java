使用 PriorityQueue 的版本
假设每个数组长度为 n，一共 k 个数组。
时间复杂度 O(nklogk)O(nklogk)
空间复杂度 O(k)O(k)

Comparator
新建一个链表res, 引入Comparator类,若newInterval与当前interval没有交集，则按照先后次序加入newInterval和当前interval，加入剩下的interval, 返回res。若newInterval与当前interval有交集，合并成为新的newInterval，处理interval。未返回res，此时newInterval为最后一个interval，装入res。return res。

Time Complexity：O(nlogn)
Space Complexity：O(n)

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
