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
    public int maxDepth(TreeNode root) {
        int level = 0;
      if(root == null) return level;
      return Math.max(helper(root.left), helper(root.right)) + 1;
      
      }
  private int helper(TreeNode root) {
    if(root == null) return 0;
    int l = helper(root.left) +1;
    int r = helper(root.right) +1;
    return Math.max(l,r);
  }
}

OnOh
