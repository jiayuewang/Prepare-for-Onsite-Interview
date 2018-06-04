

class Solution {  
    public int countSubstrings(String s) {  
        if(s == null || s.length() == 0)  
            return 0;  
        int len = s.length();  
        int res = 0;  
        boolean[][] dp = new boolean[len][len];  
        for(int i = len - 1; i >= 0; i--){  
            for(int j = i; j < len; j++){  
                if(s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])){  
                    dp[i][j] = true;  
                    res++;  
                }  
            }  
        }  
          
        return res;  
    }  
}  // on*n; sn*n
//j - i <= 2,要写在前面，先判断极限条件，32这种情况不能出现