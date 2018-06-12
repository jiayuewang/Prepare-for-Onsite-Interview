我们也可以使用位操作Bit Manipulation来做，利用异或的性质，相同位返回0，这样相同的字符都抵消了，剩下的就是后加的那个字符，

public class Solution {
    /**
     * @param s: a string
     * @param t: a string
     * @return: the letter that was added in t
     */
    public char findTheDifference(String s, String t) {
        // Write your code here
        int res = 0;
     for(int i = 0; i < s.length();i++){
         res ^= s.charAt(i);
     }
     for(int j = 0; j < t.length(); j++){
         res ^= t.charAt(j);
     }
     return (char)res;
    }
}

// public char findTheDifference(String s, String t) {  
//     int ret = 0;  
//     for (int i = 0; i < s.length(); i++) {  
//         ret -= (int)s.charAt(i);  
//     }  
//     for (int i = 0; i < t.length(); i++) {  
//         ret += (int)t.charAt(i);  
//     }  
//     return (char)ret;  
// }  