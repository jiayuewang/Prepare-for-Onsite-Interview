class Solution {
    public int calculate(String s) {
        int res = 0;
        int sign = 1;
        int cur = 0;
        char[] sc = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < sc.length; i++) {
            if(sc[i] >= '0' && sc[i] <= '9') {
                cur = cur * 10 + (sc[i] - '0');
            } else if(sc[i] == '+' || sc[i] == '-') {
                res += cur * sign;
                sign = sc[i] == '+'? 1 : -1;
                cur = 0;
            } else if(sc[i] == '('){
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
                cur = 0;
            } else if(sc[i] == ')') {
                res += cur * sign;
                res *= stack.pop();
                res += stack.pop();
                System.out.println(res);
                sign = 1;
                cur = 0;
            }
        }
        res += cur * sign;
        return res;
    }
}


class Solution {
    public int calculate(String s) {
        int res = 0;
        char prevOp = '+';
        int cur = 0;
        char[] sc = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < sc.length; i++) {
            if(sc[i] >= '0' && sc[i] <= '9') {
                cur = 0;
                while(i < sc.length && sc[i] >= '0' && sc[i] <= '9') {
                    cur = cur * 10 + (sc[i] - '0');
                    i++;
                }
                if(prevOp == '+') {
                    stack.push(cur);
                } else if (prevOp == '-') {
                    stack.push(-cur);
                } else if(prevOp == '*') {
                    stack.push(stack.pop()*cur);
                } else {
                    stack.push(stack.pop()/cur);
                }
                i--;
            } else if(sc[i] == '+' || sc[i] == '-' || sc[i] == '*' || sc[i] == '/'){                
                prevOp = sc[i];
            }
        }
        while(!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}

class Solution {
    public int calculate(String s) {
        int res = 0;
        char prevOp = '+';
        int cur = 0;
        char[] sc = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < sc.length; i++) {
            if(sc[i] >= '0' && sc[i] <= '9') {
                cur = 0;
                while(i < sc.length && sc[i] >= '0' && sc[i] <= '9') {
                    cur = cur * 10 + (sc[i] - '0');
                    i++;
                }
                if(prevOp == '+') {
                    stack.push(cur);
                } else if (prevOp == '-') {
                    stack.push(-cur);
                } else if(prevOp == '*') {
                    stack.push(stack.pop()*cur);
                } else {
                    stack.push(stack.pop()/cur);
                }
                i--;
            } else if(sc[i] == '+' || sc[i] == '-' || sc[i] == '*' || sc[i] == '/'){                
                prevOp = sc[i];
            } else if(sc[i] == '(') {
                int count = 1, start = i++;
                while(i < sc.length && count != 0) {
                    if(sc[i] == '(') {
                        count++;
                    } else if (sc[i] == ')') {
                        count--;
                    }
                    i++;
                }
                int block = calculate(s.substring(start+1, i-1));
                if(prevOp == '+') {
                    stack.push(block);
                } else if (prevOp == '-') {
                    stack.push(-block);
                } else if(prevOp == '*') {
                    stack.push(stack.pop()*block);
                } else {
                    stack.push(stack.pop()/block);
                }
                i--;
            }
        }
        while(!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}