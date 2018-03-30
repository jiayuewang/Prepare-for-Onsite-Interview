package com.fishercoder.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

 // Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 // For example,
 // Given [100, 4, 200, 1, 3, 2],
 // The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 // Your algorithm should run in O(n) complexity.
//  题解：

// 1. Set 解法

// 使用 集合 set 存 所有的数字，然后遍历数组中的每个数字。找到连续序列的左端点 和 右端点 next。即 pre- 1 不在集合中，则令 next=pre+1。那么 next-pre 就是当前数字的最长连续序列，更新max res 即可。 需要 O(n)，然后我们可以在 O(1)中 查找是否有 我们需要的数字。

// Running time: O(n)
// Space: O(n)


    public class Solution {
        public int longestConsecutive(int[] nums) { 
            Set<Integer> set = new HashSet<>();
            //先将所有数字加 数组中 
            for(int pre : nums){
              set.add(pre);
            }
    
            int res = 0;
    
            for(int pre : nums) {
            //找到连续序列的左端点，即 pre-1 不在集合中 n 如果数字 n 是条纹的开始(即，n-1 不在该组 中)，
            //则测试 m = n + 1，n + 2，n + 3，...并停 在第 个数字 m 处 在集合中，res 就是 m-n
              if(!set.contains(pre - 1)) {
                int next = pre + 1;
                while(set.contains(next)) {
                //只要 next 存在在 set 中，向后继续寻找下 个连续的元素
                  next++;
                }
               //那么 next-pre 就是当前数字的最长连续序列，更新 res 即可 
                res = Math.max(res, next - pre);
              }
            } 
    
          return res; 
        }
//     }
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_F07F974E36D2852625C45C5E13E9D8B596F60CD6402899F04FCEA72B13227DB4_1514709192512_image.png)

// 2. Union Find 解法

// 使用两个array, 一个number[]存union-find的parent index，另一个number2[]存root对应的最大长度。

// Running time: O(n)
// Space: O(n)

// 这是因为路径压缩函数 exhibits growth which follows the Inverse Ackermann Function From Princeton Algorithm1.


    public class Solution { 
        
      int[] number;
      int[] number2; 
      Map<Integer, Integer> map;
        
      public int longestConsecutive(int[] nums) { 
        number = new int[nums.length];
        number2 = new int[nums.length];
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) { 
          insert(nums[i], i);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
          res = Math.max(res, number2[i]); 
        }
        return res; 
      }
        
      void insert(int num, int index) { 
        if (!map.containsKey(num)) {
          map.put(num, index); 
          number[index] = index; 
          number2[index] = 1;
          if (map.containsKey(num - 1)) {
            unite(num - 1, num); 
          }
          if (map.containsKey(num + 1)) { 
              unite(num + 1, num);
          } 
        }
      }
        
      void unite(int exp1, int exp2) {
        int pre = findSet(map.get(exp1)); 
        int curr = findSet(map.get(exp2)); 
        if (pre != curr) {
          if (number2[pre] > number2[curr]) { 
            number[curr] = pre; 
            number2[pre] += number2[curr];
          } else {
            number[pre] = curr;
            number2[curr] += number2[pre];
          } 
        }
      }
        
      private int findSet(int num) { 
        if (number[num] != num) {
          number[num] = findSet(number[num]); 
        }
        return number[num]; 
      }
        
    }

//  会比使用set快2ms.


// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_F07F974E36D2852625C45C5E13E9D8B596F60CD6402899F04FCEA72B13227DB4_1514708304334_image.png)



