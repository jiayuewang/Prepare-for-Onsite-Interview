

/**
 * 99. Recover Binary Search Tree
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.

 Note:
 A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */
   Recover the tree without changing its structure.
          6
         / \
        8  1
      /  \
     0   3
        / \
       2  5

     time : O(n)
     space : O(n)


     */

    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        if (root == null) return;
        helper(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    public void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        if (prev != null && prev.val >= root.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        helper(root.right);
    }

    public void recoverTree2(TreeNode root) {
        if (root == null) return;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (prev != null && cur.val <= prev.val) {
                    if (first == null) first = prev;
                    second = cur;
                }
                prev = cur;
                cur = cur.right;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}

public class _99 {
  public static class Solution1 {
    TreeNode firstElement = null;
    TreeNode secondElement = null;

    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
      traverseTree(root);

      //swap the two elements
      int temp = firstElement.val;
      firstElement.val = secondElement.val;
      secondElement.val = temp;
    }

    private void traverseTree(TreeNode root) {
      if (root == null) {
        return;
      }

      traverseTree(root.left);

      //prevElement means the one previous to the current root, refer to in-order traversal, previous element must be smaller than the current root
      //if it's bigger, then we find the first element, thus we store it in the variable called firstElement
      if (firstElement == null && prevElement.val >= root.val) {
        firstElement = prevElement;
      }

      if (firstElement != null && prevElement.val >= root.val) {
        secondElement = root;
      }

      //this is the last step in the "do some business logic", so we'll always to have update the previous node to be the current root before it traverses the right subtree
      //since the current root will be the new previous node for the right subtree.
      prevElement = root;

      traverseTree(root.right);
    }

  }
}
