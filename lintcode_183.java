源码分析
异常处理，若对 L 求和所得长度都小于 k，那么肯定无解。
初始化start和end, 使用二分搜索。
使用 list comprehension 求 ∑​i=1​n​​​l​​L[i]​​.
若求得的pieces_sum小于 k，则说明mid偏大，下一次循环应缩小mid，对应为将当前mid赋给end.
与一般的二分搜索不同，即使有pieces_sum == k也不应立即返回mid, 因为这里使用了取整运算，满足pieces_sum == k的值不止一个，应取其中最大的mid, 具体实现中可以将pieces_sum < k写在前面，大于等于的情况直接用start = end代替。
排除end == 2之后返回start即可。
简单对第6条做一些说明，首先需要进行二分搜索的前提是 sum(L) >= k且end不满足end == 1 || end == 2, end为2时单独考虑即可。

复杂度分析
遍历求和时间复杂度为 O(n), 二分搜索时间复杂度为 O(logmax(L)). 故总的时间复杂度为 O(nlogmax(L)). 空间复杂度 O(1).
public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        int l = 1;
        int r = 0;
        for(int item : L) {
           r = Math.max(r, item);
        }
        while( l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(count(L, mid) >= k) {
                l = mid;
            }else{
                r = mid;
            }
        }
       if (count(L, r) >= k) {
            return r;
        }
        
        if (count(L, l) >= k) {
            return l;
        }
        return 0;
    }
        
        private int count(int[] L, int len) {
            int sum = 0;
            for (int item : L) {
                sum += item/len;
            }
            return sum;
        }
    }