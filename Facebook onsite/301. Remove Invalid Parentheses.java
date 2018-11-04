

// Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
// Note: The input string may contain letters other than the parentheses ( and ).
// Examples:
// "()())()" -> ["()()()", "(())()"]
// "(a)())()" -> ["(a)()()", "(a())()"]
// ")(" -> [""]





//below iss a dfs solution,which is faster than bfs cuz no dups are created, but stack overflow may occur if string too long.
//you can use bfs which needs a hashset, a queue and a boolean found. if curr string is valid, add it into res, found = true,
//so that only curr level's strings in the queue will be checked later(cuz we only need min ans); if curr string isn't valid,
//try to remove one char(par) i<s.length() from the string, then add the new string to the queue(if !containsKey)
这题就是DFS做最好，BFS的不要看    O(nk)
k is the total "number of recursive calls"


Key Points:

Generate unique answer once and only once, do not rely on Set.
Do not need preprocess.
Runtime 3 ms.
Explanation:
We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.

To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one,
 we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.

After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. 
However, we need to keep another information: the last removal position. 
If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
For this, we keep tracking the last removal position and only remove ‘)’ after that.

Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
The answer is: do the same from right to left.
However a cleverer idea is: reverse the string and reuse the code!
Here is the final implement in Java.

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(res, s, 0, 0, new char[]{'(', ')'});
        return res;
    }
    private void remove(List<String> res, String s, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;//if the prefix is in order legal(that is,within curr prefix,num of '(' is >= num of ')')
            //which means we can search j within previous legal prefix to make current prefix to i legal(so check last_j to i)
            for (int j = last_j; j <= i; j++) {//j <= i, not j < i !!!
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {//if it's first or not consecutive
                    remove(res, s.substring(0, j) + s.substring(j + 1), i, j, par);//don't forget to update last_i&last_j
                }
            }
            return;//important! this can avoid dups, too
        }
        String reversed = new StringBuilder(s).reverse().toString();//see how to get the reversed string
        if (par[0] == '(') {//if we haven't checked the reversed string
            remove(res, reversed, 0, 0, new char[]{')', '('});
        } else {//if the reversed string has been checked
            res.add(reversed);//we need to reverse the string twice in total
        }
    }
}
// bfs
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {//no s.length() == 0 !!! for "" we need to add it to res cuz it's a valid string
            return res;
        }
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                res.add(s);
                found = true;//then we will only check the rest of the strings in queue(same level), cuz we need min ans
            }
            if (found) {
                continue;
            }
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')') {//make sure it's a par, not a digit/letter/etc.
                    continue;
                }
                String next = s.substring(0, i) + s.substring(i + 1);//remove 1 par to get next possible valid string
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        return res;
    }
    
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            }
            if (s.charAt(i) == ')') {
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }
        return count == 0;
    }
}


给一个字符串，里面有小括号和各种字符，remove最少次数得到一个括号匹配的字符串。

two pass.
用两个变量， int left, int right.
从头扫第1遍的时候， 加进去所有left,如果right不大于left, 就加进去stringbuilder。这样保证“)”都有相对应的"("。
第2遍，从第1次的stringbuilder的末尾扫，加进去所有 )， 如果left 不大于 right, 加进去所有 (。 
第2遍pass的时候，解决了第1pass, “（”比 “）“多的情况。


  public String removeInvalidParantheses(String s) {
            
        String firstPass = "";
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
                firstPass +='(';
            } else if (c == ')' && right<left) {
                right++;
                firstPass+=')';
            } else if (c != ')' && c != '(') {
                firstPass+=c;
            }
        }

        left = 0;
        right = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = firstPass.length()-1;i>=0;i--)
        {
                char c = firstPass.charAt(i);
            if (c == ')') {
                right++;
                sb.append(')');
            } else if (c == '(' && left < right) {
                left++;
                sb.append('(');
            } else if (c != ')' && c != '(') {
                sb.append(c);
            }
        }

        return sb.reverse().toString();
    }


