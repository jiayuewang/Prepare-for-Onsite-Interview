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
      //  if(root == null) return 0;//return 0 not root, it is not treenode
       // return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    if(root == null ) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

OnO1