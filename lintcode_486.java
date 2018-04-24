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
        //last merge的 和现在将要merge的,last 是merge之后的
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
        //list1.get(i)就是current list
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
        // last end 还没有curt的start大，就curt 加入result
        
        if (curt.start > last.end) {
            results.add(last);
            return curt;
        }
        
        last.end = Math.max(last.end, curt.end);
        return last;
    }
}



法二:

 public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }

        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                heap.add(lists.get(i));
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if (head.next != null) {
                heap.add(head.next);
            }
        }
        return dummy.next;
    }
}
