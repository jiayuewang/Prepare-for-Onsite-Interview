package com.fishercoder.solutions;

import com.fishercoder.common.classes.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141. Linked List Cycle
 *
 * Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
 *
 */
public class Solution {

public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
        return false;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (slow != fast) {
        if (fast == null || fast.next == null) {//只需要判断快的指针，不要太判断慢的那个
            return false;
        }
        slow = slow.next;
        fast = fast.next.next;
    }
    return true;
}
}
