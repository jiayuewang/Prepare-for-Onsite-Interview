package com.fishercoder.solutions;

import com.fishercoder.common.classes.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**226. Invert Binary Tree

Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9

to

     4
   /   \
  7     2
 / \   / \
9   6 3   1

Trivia:
This problem was inspired by this original tweet by Max Howell:
Google: 90% of our engineers use the software you wrote (Homebrew), but you can�t invert a binary tree on a whiteboard so fuck off.
 */
 
 class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right =left;
        return root;
    }
    public TreeNode invertTree2(TreeNode root){
        if(root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i =0; i < size; i++){
                TreeNode cur = queue.poll();
                TreeNode temp = cur.left;
                    cur.left = cur.right;
                cur.right = temp;
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
        }
        return root;
    }
}

S
public class _226 {

	public static class Solution1 {
		public TreeNode invertTree(TreeNode root) {
			if (root == null) {
				return root;
			}
			Queue<TreeNode> q = new LinkedList();
			q.offer(root);
			while (!q.isEmpty()) {
				TreeNode curr = q.poll();
				TreeNode temp = curr.left;
				curr.left = curr.right;
				curr.right = temp;
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
			return root;
		}
	}

	public static class Solution2 {
		public TreeNode invertTree(TreeNode root) {
			if (root == null) {
				return root;
			}
			TreeNode temp = root.left;
			root.left = root.right;
			root.right = temp;
			invertTree(root.left);
			invertTree(root.right);
			return root;
		}
	}

	public static class Solution3 {
		//more concise version
		public TreeNode invertTree(TreeNode root) {
			if (root == null) {
				return root;
			}
			TreeNode temp = root.left;
			root.left = invertTree(root.right);
			root.right = invertTree(temp);
			return root;
		}
	}
}
