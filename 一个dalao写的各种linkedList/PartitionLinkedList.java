/**
 *
 * Given a linked list and a target value T, partition it such that all nodes
 * less than T are listed before the nodes larger than or equal to target value T.
 * The original relative order of the nodes in each of the two partitions
 * should be preserved.
 *
 * Examples
 *    L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
 *
 **/

public class PartitionLinkedList {

  static class ListNode {
    int value;
    ListNode next;
    ListNode(int value) {
      this.value = value;
    }
  }

  // Start with two dummy ListNode, small and large
  // Iterate through the original list,
  // append every nodes that less than target to small
  // append every nodes that greater than and equal to target to large
  // append large to small, and set large's tail's next to null to avoid cycle
  // return small.next

  // Time: O(n)
  // Space: O(1)
  public ListNode partition(ListNode head, int target) {
    // Corner Cases
    if (head == null || head.next == null) {
      return head;
    }

    ListNode small = new ListNode(0);
    ListNode large = new ListNode(0);

    ListNode smallCur = small;
    ListNode largeCur = large;

    while (head != null) {
      if (head.value < target) {
        smallCur.next = head;
        smallCur = smallCur.next;
      } else {
        largeCur.next = head;
        largeCur = largeCur.next;
      }
      head = head.next;
    }

    // Append large list to small list's tail
    smallCur.next = large.next;
    // Set large list's tail.next to null to avoid cycle
    largeCur.next = null;

    return small.next;
  }

}
