/**
 * public class SVNRepo {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use SVNRepo.isBadVersion(k) to judge whether 
 * the kth code version is bad or not.
*/

public class Solution {
    /*
     * @param n: An integer
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
        int start = 1, end = n;
        while (start +1 < end) {//不重合时
            int mid = start + (end - start)/2;//避免溢出
            if(SVNRepo.isBadVersion(mid)) {
                end = mid; 
            }else {
                start =mid;
            }
        }
        if(SVNRepo.isBadVersion(start)){//lowercase
            return start;
        }
        return end;
        }
}
    
Ologn
S1