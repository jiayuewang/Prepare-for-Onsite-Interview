/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // write your code here
        if (head == null || head.next == null) return false;
        ListNode first = head;
        ListNode second = head.next;
        while (first != second) {
            if(second == null || second.next == null) return false;
            first = first.next;
            second = second.next.next;
        }
        return true;
    }
}


