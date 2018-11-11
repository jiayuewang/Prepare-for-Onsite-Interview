package com.fishercoder.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Text Justification
 */

//  Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
//  You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
//  Pad extra spaces ' ' when necessary so that each line has exactly L characters.
//  Extra spaces between words should be distributed as evenly as possible.
//  If the number of spaces on a line do not divide evenly between words,
//  the empty slots on the left will be assigned more spaces than the slots on the right.
//  For the last line of text, it should be left justified and no extra space is inserted between words.

//  For example,
//  words: ["This", "is", "an", "example", "of", "text", "justification."]
//  L: 16.

//  Return the formatted lines as:
//  [
//  "This    is    an",
//  "example  of text",
//  "justification.  "
//  ]

//  Note: Each word is guaranteed not to exceed L in length.

//  Corner Cases:
//  A line other than the last line might contain only one word. What should you do in this case?
//  In this case, that line should be left-justified.
//  */
// 思路：该题为实现题，题目给我们若干个String类型的单词，L代表一行最多有多少个字母，我们需要一行一行的来处理，我们首先确定每行所能放下的单词，方法就是比较n个单词的长度和加上n - 1个空格的长度跟给定的长度L来比较即可，找到了一行能放下的单词个数，并且计算出这一行的空格的个数，即maxWidth减去这一行单词的总长度，之后我们需要在每个单词后面插入这些空格，1，如果不是最后一行我们需要将space填充在单词之间，所以这里需要分情况讨论，例如总空格数是奇数，空格位置是偶数，前面应该比后面多mod完的数量来填充当最后一行的处理方法。即int a=(maxWidth-numberOfword)%spaceBtword。也就是说，三个单词，中间有两个空位，如果空格的个数是3，那么左边的空间里要比右边的空间里多加入一个空格。2，最后一行需要将space填充在单词的末尾。

// 题解1:
// Time Complexity：O(n)
// Space Complexity：O(n)
// 代码如下：

    class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> result=new ArrayList<>();
            int index=0;
            while(index<words.length){
                int numberOfword=words[index].length();//存储每个单词的长度
                int last.index=index+1;
                //找到当前这行最多能存几个单词，并且记录边界index
                while(last.index<words.length){//没有遍历完继续遍历
                    if(words[last.index].length()+numberOfword+1>maxWidth) break;
                    numberOfword+=1+words[last.index].length();//1代表空格
                    last.index++;//last.index是遍历到的最大边界index
                }
                StringBuilder builder=new StringBuilder();
                builder.append(words[index]);
                int spaceBtword=last.index-index-1;//用spaceBtword 存放单词之间空格的个数，即三个单词间有两个空格
                if(last.index==words.length||spaceBtword==0){ //所有单词已经遍历完或者spaceBtword代表空格没有
                    for(int i=index+1;i<last.index;i++){//需要讲生于空格填充至单词末尾
                       // index＋1是遍历下一个单词，接下来需要加上空格并加上一个单词
                        builder.append(" ");
                        builder.append(words[i]);
                    }
                    for(int i=builder.length();i<maxWidth;i++){
                        builder.append(" ");
                    }
                }
                else {
                        int space=(maxWidth-numberOfword)/spaceBtword;
                        // space代表单词之间的空格个数
                        int a=(maxWidth-numberOfword)%spaceBtword;//总空格数是奇数，空格位置是偶数，前面应该比后面多mod完的数量来填充
                        for(int i=index+1;i<last.index;i++){
                            for(int j=space;j>0;j--){
                                builder.append(" ");//space 加入进去
                            }
                            if(a>0){//把a加入进去
                                builder.append(" ");
                                a--;
                            }
                            builder.append(" ");//上述numberOfword进行了加1的操作，所以此时需要append一个空格
                            builder.append(words[i]);
                        }
                    }
                    result.add(builder.toString());
                    index=last.index;
                }
                return result;
            }
        }
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_634E3559863E0F63344095DBBC953A84935D526392211084AFAB5FE2D2189328_1517189812992_+2018-01-28+7.33.25.png)


