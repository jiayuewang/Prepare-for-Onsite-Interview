package com.fishercoder.solutions;
/**434. Number of Segments in a String

Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5*/
class Solution {
    public int countSegments(String s) {
        s = s.trim();
        if(s.length() ==0 ) return 0;
           return s.split("\\s+").length;
//变成多个string 一个或多个空格的分开， + ------》正则表达的符号
    }
    // trim On  S1
}