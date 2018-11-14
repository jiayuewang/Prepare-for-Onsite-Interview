
 * 100. Same Tree
 *
 * Given two binary trees, write a function to check if they are equal or not. Two binary trees are
 * considered equal if they are structurally identical and the nodes have the same value.
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        else if(p == null||q == null|| p.val != q.val) return false;
        else{
         return helper(p.left, q.left) &&helper(p.right, q.right);
        }
    }
  private boolean helper(TreeNode a, TreeNode b) {
   if(a == null && b == null) return true;
    else if (a == null || b == null) return false;
    else{
      return a.val == b.val && helper(a.left, b.left) && helper(a.right, b.right);
    }
  }
}