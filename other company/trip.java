 判断是否string是数字
 public static boolean isNumericZidai(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
 public static void main(String[] args) {
        double aa = -19162431.1254;
        String a = "-19162431.1254";
        String b = "-19162431a1254";
        String c = "中文";
        System.out.println(isNumericzidai(Double.toString(aa)));
        System.out.println(isNumericzidai(a));
        System.out.println(isNumericzidai(b));
        System.out.println(isNumericzidai(c));
    }




每个coupon对应一个location。
*     1) A coupon for Griffith Park Observatory
*     2) A coupon for Los Angeles
*     3) A coupon for California


class Coupon {
    int id；
    int locationId; 
    int priority; [1,5]
}

class Location {
    int locationId;
    int parentLocationId; // -1 if no parent, location is hierarchy
    String name;

}




98. valid BST。
 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }，返回左子树中最大的值，和柚子树中最小的只
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
       if(root==null) return true;
        return helper(root,null,null);
    }
    boolean helper(TreeNode root ,Integer max, Integer min){
         if(root==null) return true;
        if((max!=null&&root.val>=max)||(min!=null&&root.val<=min)){
            return false;
        }
        boolean left= helper(root.left,root.val,min);
        boolean right=helper(root.right,max,root.val);
        return left&&right;
    }
}

public class _98Test {
    private static _98.Solution1 solution1;
    private static TreeNode root;

    @BeforeClass
    public static void setup() {
        solution1 = new _98.Solution1();
    }

    @Test
    public void test1() {
        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        assertEquals(true, solution1.isValidBST(root));
    }



    给一个数据结构，筛选出其中有5个以上特征的location，做hashmap存储就好了

早上面试脑子有点糊涂。。问hashmap的实现方式反应了会才答好


149. Max Points on a Line
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */


      
 public class Solution {
    public int maxPoints(Point[] points) {
        int res = 0, n = points.length;
        for (int i = 0; i < n; ++i) {
            int duplicate = 1;
            for (int j = i + 1; j < n; ++j) {
                int cnt = 0;
                long x1 = points[i].x, y1 = points[i].y;
                long x2 = points[j].x, y2 = points[j].y;
                if (x1 == x2 && y1 == y2) {++duplicate;continue;}
                for (int k = 0; k < n; ++k) {
                    int x3 = points[k].x, y3 = points[k].y;
                    if (x1*y2 + x2*y3 + x3*y1 - x3*y2 - x2*y1 - x1 * y3 == 0) {
                        ++cnt;
                    }
                }
                res = Math.max(res, cnt);
            }
            res = Math.max(res, duplicate);
        }
        return res;
    }
}
  
