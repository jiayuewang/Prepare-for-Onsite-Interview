package com.fishercoder.solutions;

import com.fishercoder.common.classes.ListNode;

/**Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

 Note: Do not modify the linked list.

 Follow up:
 Can you solve it without using extra space?*/
public class solution {

    public ListNode detectCycle(ListNode head) {
       ListNode slow = head;
       ListNode fast = head;
       while(fast! = null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) {//相遇了。从头开始走，slow与slow2不相等，他们都向下走
            ListNode slow2 = head;
            //On O1 // found the entry location
            while(slow2 != slow){
                slow = slow.next;
                slow2= slow2.next;
            }
                return slow;
            }
        }
        return null;// 如果不满足fast/ fast.next != null 就要返回null了
    }
}
// 即，如果此时有一个新的指针node，从head出发，当slow和head相遇时，相遇的点就是环的入口点

