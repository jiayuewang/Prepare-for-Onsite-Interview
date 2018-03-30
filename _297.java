package com.fishercoder.solutions;

import com.fishercoder.common.classes.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can
 * be deserialized to the original tree structure.
*/
//  For example, you may serialize the following tree

//      1
//     / \
//    2   3
//   / \
//  4   5

//  as "[1,2,3,null,null,4,5]",
//  just the same as how LeetCode OJ serializes a binary tree.
//  You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

//  Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
//  */

// 思路：这道题是让我们来设计序列化和反序列化的方法，并且方法应该是无状态的。比较常见的方法有前序遍历和层次遍历，其中采用层次遍历行需要利用队列。

// 题解1：Level Order Traversal
// 先检查当前node是否为空，为空的话就返回，如果不为空就递归的序列化自己的左子树和右子树，如果当前value是null则打印‘#’，否则打印当前递归节点的值。反序列化与之类似，如果读取当前节点值为‘#’，则返回null，否则递归反序列化该节点的左右子树。
// Time Complexity O(n)
// Space Complexity O(n)
// 代码如下：

    
    public class Codec {
        private static final String spl = ",";
        private static final String pre = "#";
    
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            helperToString(root, sb);
            return sb.toString();
        }
    
        private void helperToString(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(pre).append(spl);
            } else {
                sb.append(node.val).append(spl);
                helperToString(node.left, sb);
                helperToString(node.right,sb);
            }
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<String> new_node = new LinkedList<>();
            new_node.addAll(Arrays.asList(data.split(spl)));
            return helper(new_node);
        }
        private TreeNode (Deque<String> new_node) {
            String s = new_node.remove();
            if (s.equals(pre)) return null;
            else {
                TreeNode node = new TreeNode(Integer.valueOf(s));
                node.left = (new_node);
                node.right = (new_node);
                return node;
            }
        }
    }


// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_3BD5A4781D5096A38522F662B9C393882D050A7BB8E6072456B87D84FC08182D_1517699089597_+2018-02-03+4.57.16.png)


// 题解2：Pre-order Traversal

      public class Codec {
     // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null)
            return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            TreeNode h = stack.pop();   
            if(h!=null){
                sb.append(h.val+",");
                stack.push(h.right);
                stack.push(h.left);  
            }else{
                sb.append("#,");
            }
        }
        return sb.toString().substring(0, sb.length()-1);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null)
            return null; 
        int[] temp = {0};
        String[] s = data.split(",");
        return helper(s, temp);
    }
    public TreeNode helper(String[] s, int[] temp){
        if(s[temp[0]].equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s[temp[0]]));
        temp[0]=temp[0]+1;
        root.left = helper(s, temp);
        temp[0]=temp[0]+1;
        root.right = helper(s, temp);
        return root;
    }
    }