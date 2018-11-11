package com.fishercoder.solutions;

import java.util.ArrayList;
import java.util.List;

// 212. Word Search II
//  Given a 2D board and a list of words from the dictionary, find all words in the board.

//  Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

//  For example,
//  Given words = ["oath","pea","eat","rain"] and board =

//  [
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//  ]
//  Return ["eat","oath"].
//  Note:
//  You may assume that all inputs are consist of lowercase letters a-z.

//  You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

//  If the current candidate does not exist in all words' prefix, you could stop backtracking immediately.
//  What kind of data structure could answer such query efficiently?
//  Does a hash table work? Why or why not? How about a Trie?
//  If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.*/
// 
//     思路：Word Search 中 我们用了DFS的方法。如果这道题我们也用DFS回溯的方法，逐个检查每个word是否在board里，显然效率是比较低的。我们可以利用Trie数据结构，也就是前缀树。然后dfs时，如果当前形成的单词不在Trie里，就没必要继续dfs下去了，也就是剪枝。如果当前字符串在trie里，就说明board可以形成这个word。

// 题解一：照Word Search，我先尝试使用类似的方法。由于查询单词需要从board的某个坐标点(i, j)出发，如果查询每个单词都得遍历一遍board显然效率太低。在查询过程中可以直接从哈希表中取起点，但是每次只查询一个单词，重复工作。所以我们选择做法是构造一个Trie，将字典树作为一个待查询的字符串集合。
// Time complexity:O(m*n) 
// Space complexity: O(mn nums of trieNode)
// 代码如下：

    public class Solution {
        public class TrieNode{
            public boolean isWord = false;
            public TrieNode[] child = new TrieNode[26];
            public TrieNode(){
                
            }
        }
        
        TrieNode root = new TrieNode();
        boolean[][] flag;
        public List<String> findWords(char[][] board, String[] words) {
            Set<String> result = new HashSet<>();
            flag = new boolean[board.length][board[0].length];
            
            addToTrie(words);
            
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[0].length; j++){
                    if(root.child[board[i][j] - 'a'] != null){
                        search(board, i, j, root, "", result);
                    }
                }
            }
            
            return new LinkedList<>(result);
        }
        
        private void addToTrie(String[] words){
            for(String word: words){
                TrieNode node = root;
                for(int i = 0; i < word.length(); i++){
                    char ch = word.charAt(i);
                    if(node.child[ch - 'a'] == null){
                        node.child[ch - 'a'] = new TrieNode();
                    }
                    node = node.child[ch - 'a'];
                }
                node.isWord = true;
            }
        }
        
        private void search(char[][] board, int i, int j, TrieNode node, String word, Set<String> result){
            if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || flag[i][j]){
                return;
            }
            
            if(node.child[board[i][j] - 'a'] == null){
                return;
            }
            
            flag[i][j] = true;
            node = node.child[board[i][j] - 'a'];
            if(node.isWord){
                result.add(word + board[i][j]);
            }
            
            search(board, i-1, j, node, word + board[i][j], result);
            search(board, i+1, j, node, word + board[i][j], result);
            search(board, i, j-1, node, word + board[i][j], result);
            search(board, i, j+1, node, word + board[i][j], result);
            
            flag[i][j] = false;
        }
    }
    