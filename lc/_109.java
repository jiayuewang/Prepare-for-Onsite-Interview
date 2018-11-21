/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        return toBST(head, null);
    }
    public TreeNode toBST(ListNode head, ListNode tail){//头尾
        if(head == tail) return null;//head == tail
        ListNode slow = head;
        ListNode fast = head;
        //找root
        while(fast != tail && fast.next != tail ){
            fast = fast.next.next;
            slow= slow.next;// two steps each time and one step each time
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head, slow);
        root.right = toBST(slow.next, tail);
        return root;
    }
}
On
Oh
/**
 * 109. Convert Sorted List to Binary Search Tree
 *
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class _109 {

    public static class Solution1 {

        public TreeNode sortedListToBST(ListNode head) {
            return toBstRecursively(head, null);
        }

        public TreeNode toBstRecursively(ListNode start, ListNode end) {
            if (start == end) {
                return null;
            } else {
                ListNode mid = start;
                ListNode fast = start;
                while (fast != end && fast.next != end) {
                    mid = mid.next;
                    fast = fast.next.next;
                }

                TreeNode root = new TreeNode(mid.val);
                root.left = toBstRecursively(start, mid);
                root.right = toBstRecursively(mid.next, end);
                return root;
            }
        }
    }

}
