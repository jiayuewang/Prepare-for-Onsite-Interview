// 求最大等差数列长度，不要求连续，要保持原来的顺序。 
// 例子，1、3、9、5、7。    输出四。因为1、3、5、7。 . 鍥


public class Solution {
   //given a sorted array with no dups
   //O(n^2) time, O(n^2) space
   private static int longestArithmeticProgression(int[] nums) {
       if (nums == null || nums.length < 2) {
           return 0;
       }
       HashMap<Integer, ArrayList<ArrayList<Integer>>> map = new HashMap<>();//key is diff, value is pair of indices
       for (int i = 0; i < nums.length - 1; i++) {
           for (int j = i + 1; j < nums.length; j++) {
               int diff = nums[j] - nums[i];
               if (!map.containsKey(diff)) {
                   map.put(diff, new ArrayList<>());
               }
               ArrayList<Integer> pair = new ArrayList<>(Arrays.asList(i, j));
               map.get(diff).add(pair);
           }
       }
       int max = 0;
       for (int key : map.keySet()) {
           int[] lengths = new int[nums.length];
           for (int i = 0; i < lengths.length; i++) {
               lengths[i] = 1;//initialize all nums to 1
           }
           for (ArrayList<Integer> pair : map.get(key)) {
               lengths[pair.get(1)] = lengths[pair.get(0)] + 1;//update length of this diff's Arithmetic Progression 等差
               max = Math.max(max, lengths[pair.get(1)]);
           }
       }
       return max;
   }
}