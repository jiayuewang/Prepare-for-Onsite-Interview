class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        int i = 0;
        int prev = 0;
        s = s.replaceAll(" ", "");
        char sign = '+';
        while (i < s.length()) {
            char c = s.charAt(i);
            int cur = 0;
            if (Character.isDigit(c)) {
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    cur = cur * 10 + (s.charAt(i) - '0');
                    i++;
                }
            } else if (c == '(') {
                int endIdx = findRight(s, i);
                cur = calculate(s.substring(i + 1, endIdx + 1));
                i = endIdx + 1;
            }
            if (sign == '+') {
                res += cur;
                prev = cur;
            } else if (sign == '-') {
                res -= cur;
                prev = -cur;
            } else if (sign == '*') {
                res = res - prev + prev * cur;
                prev = prev * cur;
            } else if (sign == '/') {
                res = res - prev + prev / cur;
                prev = prev / cur;
            }
            if (i < s.length()) {
                sign = s.charAt(i);
            }
            i++;
        }
        return res;
    }
    public int findRight(String s, int i) {
        int count = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count == 0) {
                return i;
            }
            i++;
        }
        return s.length() - 1;
    }
}