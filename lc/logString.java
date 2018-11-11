public class Solution {
    public class Cell implements Comparable<Cell> {
        public String id;
        public String content;
        
        public Cell(String s1, String s2) {
            id = s1;
            content = s2;
        }
        
        public int compareTo(Cell a) {
            int r = content.compareTo(a.content);
            if (r == 0) {
                return id.compareTo(a.id);
            }
            else {
                return r;
            }
        }
    }
    /**
     * @param logs: the logs
     * @return: the log after sorting
     */
    public String[] logSort(String[] logs) {
        // Write your code here
        int n = logs.length;
        String[] res = new String[n];
        List<Cell> tmp = new ArrayList<>();
        int index = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            int space = logs[i].indexOf(' ');
            char ch = logs[i].charAt(space+1);
            if (ch >= '0' && ch <= '9') {
                res[index--] = logs[i];
            }
            else {
                tmp.add(new Cell(logs[i].substring(0, space), logs[i].substring(space+1)));
            }
        }
        Collections.sort(tmp);
        
        index = 0;
        for (Cell a: tmp) {
            res[index++] = a.id + " " + a.content;
        }
        
        return res;
    }
}