/**
 *
 * Insert a value in a sorted linked list.
 *
 * Examples
 *    L = null, insert 1, return 1 -> null
 *    L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
 *    L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
 *    L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null
 *
 **/

public class InsertInSortedList {

  static class ListNode {
    int value;
    ListNode next;
    ListNode(int value) {
      this.value = value;
    }
  }

  // Insert at head: when the new value is smaller than head.value
  // Insert between two nodes: when new value is smaller than a node and larger than a node
  // Insert at the end: when new value is larger than the tail node's value

  // Time: O(n)
  // Space: O(1)
  public ListNode insert(ListNode head, int value) {
    ListNode newNode = new ListNode(value);

    // Insert at head
    if (head == null || value <= head.value) {
      newNode.next = head;
      return newNode;
    }

    // Insert between two nodes
    ListNode cur = head;
    while (cur.next != null) {
      if (value > cur.value && value <= cur.next.value) {
        newNode.next = cur.next;
        cur.next = newNode;
        return head;
      }
      cur = cur.next;
    }

    // Insert at tail
    cur.next = newNode;
    return head;
  }

}
