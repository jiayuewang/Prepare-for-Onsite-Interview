package com.fishercoder.solutions;

/**Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 Note:
 You may assume that the array does not change.
 There are many calls to sumRange function.*/
LeetCode
Explore
Problems
Mock 
Contest
Articles
Discuss
 Store 
New Playground
woshichaojimeishaonv

303. Range Sum Query - Immutable
DescriptionHintsSubmissionsDiscussSolution
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
Seen this question in a real interview before?  
Difficulty:Easy
Total Accepted:93.8K
Total Submissions:290.5K
Contributor:LeetCode
Companies 

Related Topics 

Similar Questions 
Range Sum Query 2D - ImmutableRange Sum Query - MutableMaximum Size Subarray Sum Equals k
Java    

1
public class NumArray {
2
   int[] sums;
3
​
4
    public NumArray(int[] nums) {
5
        if(nums.length != 0){
6
            sums = new int[nums.length];
7
        
8
            sums[0] = nums[0];
9
            for(int i=1; i<nums.length; i++){
10
                sums[i] = nums[i] + sums[i-1];
11
            }
12
        }
13
    }
14
​
15
    public int sumRange(int i, int j) {
16
        return i==0 ? sums[j] : sums[j]-sums[i-1];
17
    }
18
}
  Custom Testcase( Contribute  )
Submission Result: Accepted 
Next challenges: Range Sum Query 2D - ImmutableRange Sum Query - MutableMaximum Size Subarray Sum Equals k
Share your acceptance!
Check out our solution!


Type here...(Markdown is enabled)
​
Copyright © 2018 LeetCode Contact Us  |  Jobs  |  Frequently Asked Questions  |  Terms of Service  |  Privacy Policy      

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);