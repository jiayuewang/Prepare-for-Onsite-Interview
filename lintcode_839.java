public class Solution {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        List<Interval> results = new ArrayList<>();
        if (list1 == null || list2 == null) {
            return results;
        }
        
        Interval last = null, curt = null;
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).start < list2.get(j).start) {
                curt = list1.get(i);
                i++;
            } else {
                curt = list2.get(j);
                j++;
            }
            
            last = merge(results, last, curt);
        }
        
        while (i < list1.size()) {
            last = merge(results, last, list1.get(i));
            i++;
        }
        
        while (j < list2.size()) {
            last = merge(results, last, list2.get(j));
            j++;
        }
        
        if (last != null) {
            results.add(last);
        }
        return results;
    }
    
    private Interval merge(List<Interval> results, Interval last, Interval curt) {
        if (last == null) {
            return curt;
        }
        
        if (curt.start > last.end) {
            results.add(last);
            return curt;
        }
        
        last.end = Math.max(last.end, curt.end);
        return last;
    }
}