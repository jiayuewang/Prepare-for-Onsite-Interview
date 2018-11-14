public class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        s.trim();
        String[] a = s.split("\\s+");
        for(int i = a.length -1; i>=0; i--){
            sb.append(a[i] +" ");
        }
        return sb.toString().trim();
    }
}