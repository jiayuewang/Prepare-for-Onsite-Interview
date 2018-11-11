
public class Solution {
public int trap(int[] height) {
   if(height==null||height.length==0) return 0;
    int left=0,leftmax=0,right=height.length-1,rightmax=0;
    int res=0;
    while(left<right){
        if(height[left]<height[right]){
            leftmax=Math.max(height[left],leftmax);
            res+=leftmax-height[left];
            left++;
        }else{
            rightmax=Math.max(height[right],rightmax);
            res+=rightmax-height[right];
            right--;
            
        }
    }
    return res;
}
}//On O1