
/**Find the sum of all left leaves in a given binary tree.

 Example:

   3
  / \
 9  20
   /  \
  15   7

 There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.*/
 
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
     int res = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return res;
       helper(root);
        return res;
    }
  private boolean helper(TreeNode root){
    if(root.left == null && root.right == null){
      return true;
    }
    if(root.left != null && helper(root.left)){//已经找到了这条支路最左边的点。
      res += root.left.val;
    }
    if(root.right != null){
      helper(root.right);
    }
    return false;//并没有left leaf
  }
}



public class2 {
    public int sumOfLeftLeaves(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }
        return dfs(root, result, false);
    }

    private int dfs(TreeNode root, int result, boolean left) {
        if (root.left == null && root.right == null && left) {
            result += root.val;
            return result;
        }
        int leftResult = 0;
        if (root.left != null) {
            left = true;
            leftResult = dfs(root.left, result, left);
        }
        int rightResult = 0;
        if (root.right != null) {
            left = false;
            rightResult = dfs(root.right, result, left);
        }
        return leftResult + rightResult;
    }

    private class Solution3 {

        public int sumOfLeftLeaves(TreeNode root) {
            int sum = 0;
            if (root == null) {
                return sum;
            }
            if (root.left != null) {
                if (root.left.left == null && root.left.right == null) {
                    sum += root.left.val;
                } else {
                    sum += sumOfLeftLeaves(root.left);
                }
            }
            if (root.right != null) {
                sum += sumOfLeftLeaves(root.right);
            }
            return sum;
        }
    }
}
