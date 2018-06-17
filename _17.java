public class Solution {
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public void dfs(int x, int l, String str, String digits, String phone[], List<String> ans ) {
        if(x == l){
            ans.add(str);
            return;
        }
        int d =  digits.charAt(x) - '0';
        for (char c : phone[d].toCharArray() ) {
            dfs(x+1, l, str+c,digits,phone,ans);
        }
    }
    
   public List<String> letterCombinations(String digits) {
        // write your code here
        String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }

        dfs(0, digits.length(), "", digits, phone, ans);
        return ans;
    }
}// 4^n 4^n +n

4是每个字母最多按出的情况