package com.fishercoder.solutions;

import com.fishercoder.common.classes.TreeNode;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants
 * (where we allow a node to be a descendant of itself).”

 _______6______
 /              \
 ___2__          ___8__
 /      \        /      \
 0      _4       7       9
 /  \
 3   5

 For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6.
 Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

 */
public class _235 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        if ((root.val - p.val) * (root.val - q.val) > 0) {
            if (root.val - p.val > 0) {
                return lowestCommonAncestor(root.left, p, q);
            }
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}



 int m = matrix.length;
        int n = matrix[0].length;
        int dp[][] = new int[m+1][n+1];
        int max1 = 0, max 2=  0, val = 0, val2 = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1] != '0'){
                    max2 = Math.max(matrix[i-1][j-1], max2);
                    if(matrix[i][j-1]== matix[i-1][j-1] && matrix[i-1][j-1] == matrix[i-1][j] && matrix[i][j] == matrix[i-1][j-1])){
                     val = matrix[i][j];
                    dp[i][j] = Math.min(dp[i][j-1],Math.min(dp[i-1][j-1], dp[i-1][j])) + 1;
                }
                if(dp[i][j] > max){ 
                    val2 = val;
                }
                max1 = Math.max(dp[i][j], max1);//dp nm得到 n-1 m-1那一个点的信息，因为其他三个都为0
                
            }
        }
        return max1*max1*val2;

