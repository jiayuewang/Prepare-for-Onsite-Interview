class Solution {
    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> res = new ArrayList<>(); 
      if (root == null) return null;
      helper(root, res);
      TreeNode cur = new TreeNode(0);
      TreeNode n = cur;
      for(TreeNode node : res){
        n.right = new TreeNode(node.val);//每次都建一个新TreeNode的space
         n = n.right;
      }
      return cur.right;
    }
  private void helper(TreeNode root, List<TreeNode> res){
    if(root == null) return;
    helper(root.left, res);
    res.add(root);
    helper(root.right, res);
    
    
  }
}