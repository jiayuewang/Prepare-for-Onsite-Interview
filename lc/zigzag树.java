103. Binary Tree Zigzag Level Order Traversal
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
       List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean x = true;
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++){
            TreeNode cur = q.poll();
            if(x){
                list.add(cur.val);
            }else{
                list.add(0, cur.val);//add elements in each level to our list
            }
            if(cur.left != null){
                q.offer(cur.left);
            }
            if(cur.right != null){
                q.offer(cur.right);//q正常加 list 反着加
            }
        }
        res.add(list);
            x = x ? false: true;// 和初始化一样的话就变成反着的
            //odd --> 奇
        }
        return res;
    }
}
            