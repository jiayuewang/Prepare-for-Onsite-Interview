/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode dummy = head;
        int sum = 0;
        if(l1 == null && l2 == null) return null;
        int remind = 0;
        while(l1 != null ||l2 != null){
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            dummy.next = new ListNode(sum%10);
            sum /= 10;
            dummy = dummy.next;
            }
        if(sum == 1){
            dummy.next = new ListNode(1);
        }
        
           return head.next;
    }
}


 2. Add Two Numbers

 You are given two linked lists representing two non-negative numbers.
 The digits are stored in reverse order and each of their nodes contain a single digit.
 Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
} Omn Omn


    public static class Solution2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode pre = new ListNode(-1);
            ListNode head = new ListNode(0);
            pre.next = head;
            int carry = 0;
            while (l1 != null || l2 != null) {
                int val = carry;
                if (l1 != null) {
                    val += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    val += l2.val;
                    l2 = l2.next;
                }
                if (val >= 10) {
                    val %= 10;
                    carry = 1;
                } else {
                    carry = 0;
                }
                head.next = new ListNode(val);
                head = head.next;
            }
            if (carry != 0) {
                head.next = new ListNode(carry);
            }
            return pre.next.next;
        }
    }
}
