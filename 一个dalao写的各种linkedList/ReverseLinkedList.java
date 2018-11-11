/**
 *
 * Reverse a singly-linked list.
 *
 * Examples
 *    L = null, return null
 *    L = 1 -> null, return 1 -> null
 *    L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
 *
 **/

public class ReverseLinkedList {

  static class ListNode {
    int value;
    ListNode next;
    ListNode(int value) {
      this.value = value;
    }
  }

  // Time: O(n)
  // Space: O(n)
  public ListNode reverseRecursively(ListNode head) {
    // Corner Case
    if (head == null || head.next == null) {
      return head;
    }
    ListNode newHead = reverseRecursively(head.next);
    head.next.next = head;
    head.next = null;

    return newHead;
  }

  // Time: O(n)
  // Space: O(1)
  public ListNode reverseIteratively(ListNode head) {
    // Corner Case
    if (head == null || head.next == null) {
      return head;
    }

    ListNode prev = null;

    while (head != null) {
      ListNode next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }

    return prev;
  }

}
