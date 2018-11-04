// Given an array of integers with possible duplicates, randomly output the index of a given target number. 
//You can assume that the given target number must exist in the array.

// Note:
// The array size can be very large. Solution that uses too much extra space will not pass the judge.



// int[] nums = new int[] {1,2,3,3,3};
// Solution solution = new Solution(nums);

// // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
// solution.pick(3);

// // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
// solution.pick(1);


// {1,2,3,3,3} with target 3, you want to select 2,3,4 with a probability of 1/3 each.

// 2 : It's probability of selection is 1 * (1/2) * (2/3) = 1/3
// 3 : It's probability of selection is (1/2) * (2/3) = 1/3
// 4 : It's probability of selection is just 1/3

// So they are each randomly selected.


public class Solution {
    int[] nums;
    Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        ArrayList<Integer> index = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            if(target==nums[i]){
                index.add(i);
            }
        }
        return index.get(rand.nextInt(index.size()));//nextInt random get 0<=num<n
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

2. find index of maximum number in array, if multiple maximum numbers occurs, return a random index
在HashMap中，它的put操作的时间复杂度是多少？最块肯定是O(1),最慢肯定是O(n)
// randomly return one of the maximal elements' indices
class myCode {
    public static void main (String[] args) throws java.lang.Exception {
        myCode sol = new myCode();
        int res = sol.findMax(new int[]{1,2,3,3,3});
        System.out.print(res);
    }
    public int findMax(int[] arr){
        int len = arr.length;
        int res = -1;
        int max = Integer.MIN_VALUE;
        int count = 0; // to record how many targets in the array
        for(int i=0; i<len; i++){
            if( arr[i]==max ){
                count++;
                Random rand = new Random();
                int judge = rand.nextInt(count);
                /*For the nth target, ++count is n. Then the probability that rnd.nextInt(++count)==0 is 1/n. 
                Thus, the probability that return nth target is 1/n.
                For (n-1)th target, the probability of returning it is (n-1)/n * 1/(n-1)= 1/n.*/
                if( judge == 0){  
                    res = i;
                }
            }else if( max == Integer.MIN_VALUE || arr[i] > max){
                max = arr[i];
                res = i;
                count = 1;
            }
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
public class Solution {
    
    int[] nums;
    Random rmd;
    public Solution(int[] nums) {
        this.nums=nums;
        this.rmd=new Random();
    }
    
    public int pick(int target) {
        int res=-1;
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=target){
                continue;
            }
            if(rmd.nextInt(++count)==0){//cuz possiblity of getting 0 equals to 1/count,so all indices have equal possibility
                res=i;
            }
        }
        return res;
    }
}
// {1,2,3,3,3} with target 3, you want to select the 3 on index 2,3,4 with a probability of 1/3 each.
// 2:probability of selection is 1* (1/2)(probability of 2nd 3 not getting 0) * (2/3)(probability of 3rd 3 not getting 0) =1/3
// 3:Its probability of selection is (1/2) * (2/3) = 1/3
// 4:Its probability of selection is just 1/3
// So they are each randomly selected.
random pick max number index with equal probability
public class Solution {
    int[] nums;
    Random rmd;
    public Solution(int[] nums) {
        this.nums=nums;
        rmd=new Random();
    }
    
    public int pick(int target) {
        int count=0;
        int res=-1;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(max==Integer.MIN_VALUE||nums[i]>max){
                res=i;
                count=1;
                max=nums[i];
            }
            if(nums[i]==max){
                if(rmd.nextInt(++count)==0) res=i;
            }
        }
        return res;
    }
}