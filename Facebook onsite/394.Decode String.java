
// Given an encoded string, return it's decoded string.

// The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

// You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

// Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].


// s = "3[a]2[bc]", return "aaabcbc".
// s = "3[a2[c]]", return "accaccacc".
// s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

public class Solution {
    public class StrItem {
        int count;
        StringBuilder sb;
        public StrItem(int n) {
            count = n;
            sb = new StringBuilder();
        }
    }
    
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Stack<StrItem> stack = new Stack<>();
        stack.push(new StrItem(1));//put an empty string into stack
        int num = 0;//keep tracking of curr strItem's repeating times
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';//if it's a digit, update the repeating times
            } else if (c == '[') {
                stack.push(new StrItem(num));//create a new StrItem with the string's repeating times
                num = 0;//update num back to 0
            } else if (c == ']') {
                StrItem item = stack.pop();
                int n = item.count;
                StringBuilder str = item.sb;
                
                for (int j = 0; j < n; j++) {
                    stack.peek().sb.append(str);//add curr str for n times to prev string
                }
            } else {
                stack.peek().sb.append(c);
            }
        }
        return stack.pop().sb.toString();
    }
}