　题目给了我们一段paragraph，还有 banned words，让我们找出 在 paragraph 里出现最多的 non-banned word。

　　主要思想是，开始要把paragraph 都变成小写，然后利用 regex split 成 string array；

　　接着把banned words 放入 HashSet；

　　把 non-banned words 放入 HashMap，在这一过程中，可以保留 出现次数最多的 word，不需要在多一个loop 来找出 max。

　　!s.equals("") 的作用是因为 regex 会造成 “” 的情况，所以要跳过。

　　具体请看code。



import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
// hash 要装list类型
    public String mostCommonWord(String paragraph, String[] banned) {

        HashSet<String> set = new HashSet<>(Arrays.asList(banned));
        HashMap<String, Integer> map = new HashMap<>();
        String[] ps = paragraph.toLowerCase().split("[^a-z]");
        // 注意会有空的字符串出现
        // System.out.println(Arrays.toString(ps));
        for (String p : ps) {
            if ("".equals(p)) {
                continue;
            }
            if (!set.contains(p)) {
                if (map.containsKey(p)) {
                    map.put(p, map.get(p) + 1);
                } else {
                    map.put(p, 1);
                }
            }
        }
        int max = 0;
        String mcw = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mcw = entry.getKey();
            }
        }
        return mcw;
    }

    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        Solution solution = new Solution();
        String mostCommonWord = solution.mostCommonWord(paragraph, banned);
        System.out.println(mostCommonWord);
    }
}
