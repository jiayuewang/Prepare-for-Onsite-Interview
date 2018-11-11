package com.fishercoder.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

//  Note: The input string may contain letters other than the parentheses ( and ).

//  Examples:
//  "()())()" -> ["()()()", "(())()"]
//  "(a)())()" -> ["(a)()()", "(a())()"]
//  ")(" -> [""]
//  */
// 题解：

// 1. BFS

// remove第一个左括号之后，下一步处理原来的原先字符串remove第二个左括号或者右括号之后的字符串。直到已经在 原先字符串上，将所有可能remove掉一个左括号或者右括号的情况，再继续对队列里的字符串继续remove。每一层对于一个字符串，子节点就是对每个位置移除左括号或者右括号剩下的字符串。当达到合法时，我们remove 掉最少的字符，在扫描每一层的时候，如果出现valid的括号字符串，那么我们应该停止下一层的搜索。使用BFS的好处在于，我们可以保证需要删除的括号的数量是最小的，在BFS中也不需要递归调用。在root层时s有长度n,则到第二层时，有c(n,n-1)个长度为n-1的子串，同时进行验证是时间为O(n-1)，因此第一层的时间复杂度为O(n-1)*c(n,n-1);同理第二层的时间复杂度为O(n-2)*c(n-1,n-2)  ..T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
// Running time: O(n x 2^(n-1))
// Space: O(n)
// 代码如下：

    public class Solution {  
        public List<String> removeInvalidParentheses(String s) {  
            Queue<String> q = new LinkedList<>(); 
            Set<String> set = new HashSet<>();  
            boolean res = false;  
            List<String> l = new ArrayList<>();  
            q.add(s);  
            while (!q.isEmpty()) {  
                String newString = q.poll(); 
                if (isValid(newString)) {  
                    l.add(newString);  
                    res = true;  
                    if (q.isEmpty()) {  
                        return l;  
                    }  
                }  
    // //通过输入字符串s，我们通过删除一个（或）来生成所有可能的状态，检查它们是否有效，如果在当前级别上找到有效的状态，将它们放到最终结果列表中，否则，将它们添加到队列中并继续进行到下一层。
                if (!res) {  
                    int len = newString.length();  
                    for (int i = 0; i < len; i ++) {  
                        if(newString.charAt(i) == '(' || newString.charAt(i) == ')') {  
                            String newString2 = newString.substring(0, i) + newString.substring(i + 1, len);  
                            if (set.add(newString2)) {  
                                q.add(newString2);  
                            }  
                        }  
                    }  
                }  
            }  
            return l;  
        }  
        boolean isValid(String s) {
          int count = 0;
          for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
          }
          return count == 0;
        }
    }
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_CF7BD3156E24739EAE6BE6FC05C5A895E67C5C603A9485E98CB813A899DA8546_1515341660471_image.png)


// 2.DFS
// 我们需要定义一个变量num计算需要删除的开合括号的差值，当'('出现，num将增加，当')'出现，num减少。每当num值是负数，我们在前缀中有比'('更多的')'。此时我们需要减少’(’的数量使其前缀有效，假设 给出字符串为“(((())”，那么我们需要减少两个左括号，即从四个左括号中任意删除两个左括号。
// 使用while循环来避免重复的DFS结果，而不是使用HashSet。i_0和j_0分别表示lasti和lastj
// 使用point变量来动态验证括号。如果s 是'())())',我们需要删除')'。将其翻转以后再检查是否含有非法的左括号。左右括号都检查完之后都合法就是我们要的。

// Running time: O(n*k),k:# of recursion calls
// Space: O(n)
// 代码如下：

    class Solution {
        public List<String> removeInvalidParentheses(String s) {
            List<String> res =new ArrayList<>();
            remove(res,s, 0, 0, new char[]{'(',')'}) ;
            return res;
        }
    public void remove(List<String>res, String s, int i_0, int j_0, char[] point) {
            for (int num=0,i = i_0; i < s.length(); i++) {//在删除括号的时候，为保证不会产生重复值，需要记录一个最后删除的位置i_0,j_0，这样可以使得在接下来删除的时候只删除这个位置之后的值
              if(s.charAt(i)==point[0]) num++;
                 if(s.charAt(i)==point[1]) num--;
                if(num>=0) continue;
                for(int j=j_0;j<=i;j++){// 当num小于0，即右括号多于左括号时开始执行for loop
                    if(s.charAt(j)==point[1]&&(j==j_0|| s.charAt(j-1)!=point[1])){
                        remove(res,s.substring(0,j)+s.substring(j+1),i,j,point);
                    }//s.substring(0,j)+s.substring(j+1)，这样就删去了s.charAt(j)
                }
                return;//如果c此处不return，会造成后面reverse后出现重复
            }
            String reverse=new StringBuilder(s).reverse().toString();
            if(point[0]=='('){
                i_0=0;j_0=0;
                remove(res,reverse,j_0,i_0,new char[]{')','('});
            }else{
            res.add(reverse);
        }
    }
    }
