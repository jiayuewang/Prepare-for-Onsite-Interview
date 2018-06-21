/**
 *
 * Find the middle node of a given linked list.
 *
 * Examples
 *    L = null, return null
 *    L = 1 -> null, return 1
 *    L = 1 -> 2 -> null, return 1
 *    L = 1 -> 2 -> 3 -> null, return 2
 *    L = 1 -> 2 -> 3 -> 4 -> null, return 2
 *
 **/

public class MiddleNodeOfLinkedList {

  static class ListNode {
    int value;
    ListNode next;
    ListNode(int value) {
      this.value = value;
    }
  }

  // Slow and fast both starts at head,
  // slow walks one step at a time, fast walks two steps at a time.
  // When fast reaches the end, slow is at the middle node of linkedlist

  // Time: O(n)
  // Space: O(1)
  public ListNode middleNode(ListNode head) {
    // Corner Cases
    if (head == null || head.next == null || head.next.next == null) {
      return head;
    }

    ListNode slow = head, fast = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

}
