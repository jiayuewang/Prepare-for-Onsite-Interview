

// 。题目所说的四分树，其实就是平面上的一种集合结构。把一个边长为2的幂的正方形均分成4块，然后再均分到不能均分为止即为叶子节点。类似于树结构，这是一个平面结构。

// 解题方法
// 首先，这种结构和树结构非常类似，肯定使用递归求解。重要的是如何判断此树结构如何判断叶子节点、val。
// 所以定义了一个新的函数，如果一个正方形中所有的数字都是0，则val是False，否则val是True。

// 判断leaf的方法是看看格子里的所有的值是不是相同的，如果全是0或者1那么就是leaf，否则就不是。

// 本来应该是两个函数，但是我用一个函数有3个返回值就实现了。

// 其他的难点就在把正方形进行切分成四块了，这个不是难点。

// class Solution {
    public Node construct(int[][] grid) {
           return helper(grid, 0, 0, grid.length);
    }
    
    Node helper(int[][] g, int i, int j, int len) {
        if(len == 1) return new Node(g[i][j] == 1, true, null, null, null, null);
      //  boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight
        
        int nxt_len = len >> 1;
        Node tl = helper(g, i, j, nxt_len);
        Node tr = helper(g, i, j + nxt_len, nxt_len);
        Node bl = helper(g, i + nxt_len, j, nxt_len);
        Node br = helper(g, i + nxt_len, j + nxt_len, nxt_len);

        if(tl.isLeaf && tr.isLeaf && bl.isLeaf && br.isLeaf && 
           (tl.val && tr.val && bl.val && br.val || !tl.val && !tr.val && !bl.val && !br.val)) return new Node(tl.val, true, null, null, null, null);
        else return new Node(false, false, tl, tr, bl, br);
    }
    }
}

          