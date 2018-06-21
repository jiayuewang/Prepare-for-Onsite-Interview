/**
 *
 * Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null
 * to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null
 *
 * Examples
 *    L = null, is reordered to null
 *    L = 1 -> null, is reordered to 1 -> null
 *    L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
 *    L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null
 *
 **/

public class ReorderLinkedList {

  static class ListNode {
    int value;
    ListNode next;
    ListNode(int value) {
      this.value = value;
    }
  }

  // Separate the list to two list at the middle node
  // reverse the last half list
  // merge two lists

  // Time: O(n)
  // Space: O(1)
  public ListNode reorder(ListNode head) {
    // Corner Cases
    if (head == null || head.next == null || head.next.next == null) {
      return head;
    }

    ListNode middle = findMiddle(head);
    ListNode latterHalfListHead = reverse(middle.next);

    // Separate two lists
    middle.next = null;

    return merge(head, latterHalfListHead);
  }

  // Helper function: findMiddle
  private ListNode findMiddle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  // Helper function: reverse
  private ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    return prev;
  }

  // Helper function: merge
  private ListNode merge(ListNode one, ListNode two) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;

    while (one != null && two != null) {
      cur.next = one;
      one = one.next;
      cur = cur.next;
      cur.next = two;
      two = two.next;
      cur = cur.next;
    }

    cur.next = one;

    return dummy.next;
  }

}
