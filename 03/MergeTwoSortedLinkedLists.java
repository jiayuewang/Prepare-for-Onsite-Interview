/**
 *
 * Merge two sorted lists into one large sorted list.
 *
 * Examples
 *    L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
 *    L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
 *    L1 = null, L2 = null, merge L1 and L2 to null
 *
 **/

public class MergeTwoSortedLinkedLists {

  static class ListNode {
    int value;
    ListNode next;
    ListNode(int value) {
      this.value = value;
    }
  }

  // Create a dummy head, and compare one and two, choose smaller one append to dummy
  // until all nodes are appended to new list

  // Time: O(m + n)
  // Space: O(1)
  public ListNode merge(ListNode one, ListNode two) {

    // Assumption: There are no cycles in two lists

    // Corner Cases
    if (one == null) {
      return two;
    } else if (two == null) {
      return one;
    }

    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;

    while (one != null && two != null) {
      if (one.value < two.value) {
        cur.next = one;
        one = one.next;
      } else {
        cur.next = two;
        two = two.next;
      }
      cur = cur.next;
    }

    if (one == null) {
      cur.next = two;
    } else if (two == null) {
      cur.next = one;
    }

    return dummy.next;
  }

}
