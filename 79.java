
// /**
//  * 79. Word Search
//  *
//  * Given a 2D board and a word, find if the word exists in the grid.
//  * The word can be constructed from letters of sequentially adjacent cell,
//  * where "adjacent" cells are those horizontally or vertically neighboring.
//  * The same letter cell may not be used more than once.

//  For example,
//  Given board =
//  [
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//  ]

//  word = "ABCCED", -> returns true,
//  word = "SEE", -> returns true,
//  word = "ABCB", -> returns false.
//  */

time 复杂度是m*n*4^(k-1). 也就是m*n*4^k.
m X n is board size, k is word size.

recuision最深是k层，recursive部分空间复杂度应该是O(k) + O(m*n)(visit array)


因为题目要求一个cell只能被访问一次。如果二维数组board的当前字符和目标字符串word对应的字符相等，
则对其上下左右四个邻字符分别调用DFS的递归函数，只要有一个返回true，那么就表示可以找到对应的字符串，否则就不能找到，
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        if (word.length() == 0) return false;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == word.charAt(0)) {
                    boolean res =helper(board,i,j,0,word);
                    if(res) return true;
                }
            }
        }
        return false;
    }
    
    boolean helper(char[][] board, int i, int j, int start, String word){
        if (start == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)) return false;
        board[i][j] = '#';//一个字符
            boolean res = helper(board, i-1, j, word, start+1) 
|| helper(board, i, j-1, word, start+1) 
|| helper(board, i+1, j, word, start+1) 
|| helper(board, i, j+1, word, start+1);
        board[i][j] = word.charAt(start);
        return res;
    }
}






public class Solution {
    // recursion
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        if(word.length() == 0)
            return true;
        
        for(int i = 0; i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    
                    boolean rst = find(board, i, j, word, 0);
                    if(rst)
                        return true;
                }
            }
        }
        return false;
    }
    
    private boolean find(char[][] board, int i, int j, String word, int start){
        if(start == word.length())
            return true;
        
        if (i < 0 || i>= board.length || 
     j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)){
            return false;
     }
        
        board[i][j] = '#'; // should remember to mark it
        boolean rst = find(board, i-1, j, word, start+1) 
|| find(board, i, j-1, word, start+1) 
|| find(board, i+1, j, word, start+1) 
|| find(board, i, j+1, word, start+1);
        board[i][j] = word.charAt(start);
        return rst;
    }
}