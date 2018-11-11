package com.fishercoder.solutions;

/**
 * 37. Sudoku Solver
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution.
 *
 */

/**
 * 思路：这个题是36题的follow up，36题让我们验证给定的数组是否为数独数组，
 *这道让我们求解数独数组，对于每个需要填数字的格子带入1到9，
 *每代入一个数字都判定其是否合法，如果合法就继续下一次递归，
 *结束时把数字设回'.'，判断新加入的数字是否合法时，
 *只需要判定当前数字是否合法，不需要判定这个数组是否为数独数组，
 *因为之前加进的数字都是合法的，这样可以使程序更加高效一些，
*题解1: 我们需要以下几个变量:最大高度，当前区间，当前版面上最大值。
*枚举每一个掉落方块，将方块的起始值拿出来记录，那么end值就是起始值加上这个方块的
*size。用一个变量baseheight来存储从起始到结束的当前最大高度，
*遍历所有的intervals，如果我们的intervals不在当前的区间，就跳过。
*更新baseheight为当前intervals中baseheight的最大值。
*更新当前矩阵的高度为方块的size加上baseheight中的最大值。
*每一次循环增加一个intervals。
*Time complexity:O(n^9) 
*Space complexity: O(1)
*代码如下：
 *
 */



public class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }
    
    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell
                            
                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' && 
board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }
}




// 同样的思路如果用三个2Darray
// Time complexity:O(n^9) 
// Space complexity: O(n^2)
// 代码如下：

class Solution {
    private char[][] b;
    private boolean[][] row = new boolean[9][9];
    private boolean[][] col = new boolean[9][9];
    private boolean[][] block = new boolean[9][9];
    public void solveSudoku(char[][] board) {
        b = board;
        int num, k;
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if(board[i][j]!='.') {
                    num = board[i][j]-'1'; 
                    k = i/3*3 + j/3;
                    row[i][num] = col[j][num] = block[k][num] = true;
                }
            }
        }
        Helper(0);
    }
    public boolean Helper(int ind){
        if(ind==81) return true; 
        int i=ind/9, j=ind%9, num, k;
        if(b[i][j]!='.') return Helper(ind+1);
        else{
            for(char f='1'; f<='9'; f++){
                num = f-'1'; 
                k= i/3*3 + j/3;
                if(!row[i][num] && !col[j][num] && !block[k][num]){
                    b[i][j]= f;
                    row[i][num] = col[j][num] = block[k][num] = true;
                    if(Helper(ind+1)) return true;                
                    b[i][j]='.';
                    row[i][num] = col[j][num] = block[k][num] = false;
                }
            }
            return false;
        }
    }
}