在讨论区看到另一种判断思路，就是得到重复次数n/m，然后依次重复，再与原字符串比较。这种代码实现比较简单。 

具体代码如下：

public boolean repeatedSubstringPattern(String s) {
    int n = s.length();
    for (int i = n / 2; i >= 1; i--) {
        if (n % i == 0) {
            int k = n / i;
            String sub = s.substring(0, i);
            StringBuilder sb = new StringBuilder();
            int j;
            for (j = 1; j < k; j++) {
                if (!sub.equals(s.substring(j * i, i + j * i))) {
                    break;
                }
            }
            if (j == k) {
                return true;
            }
        }
    }
    return false;
}