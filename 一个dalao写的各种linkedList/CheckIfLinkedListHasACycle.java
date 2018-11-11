/**
 *
 * Check if a given linked list has a cycle. Return true if it does,
 * otherwise return false.
 *
 **/

public class CheckIfLinkedListHasACycle {

  static class ListNode {
    int value;
    ListNode next;
    ListNode(int value) {
      this.value = value;
    }
  }

  // slow and fast starts at head
  // slow walks one step at a time. fast walks two steps at a time
  // if slow and fast meets at some point, then there is a cycle

  // Time: O(n)
  // Space: O(1)
  public boolean hasCycle(ListNode head) {
    // Corner Cases
    if (head == null || head.next == null || head.next.next == null) {
      return false;
    }

    ListNode slow = head, fast = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }

    return false;
  }

}
