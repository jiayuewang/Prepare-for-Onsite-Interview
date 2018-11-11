二分搜索的方法。我们要找的最优解是某一个时间的临界点，当时间小于这个值时，k个人一定不可能完成任务，当时间大于等于这个值时，则可以完成。
首先将时间的范围设为所有整数（0～99999999）。计算中点作为这次的假设时间临界点，尽量让每个人的工作时间都接近这个临界点。
假设当前某个人之前分配的书的页数加上当前书的页数小于当前临界点，则直接把这本书分配给这个人而不会影响最优解；
若大于，则看当前书的页数是否大于临界点： 1）若大于，则说明当前的临界值太小，连这本书都不能copy完全，所以最优解一定大于当前临界点，因此要增大临界点再重复2 2）若小于，则将书分配给下一个人copy
若所有书分配完时，所需要的人数比k小（即还剩下人没用），则说明每个人干活的时间太多，最优时间一定比当前值小，反之则说明每个人干活的时间太少，最优时间比当前值大
考虑3-4中的所有情况，若最优解比当前临界点小，则向当前临界点左半边搜索，否则向当前临界点右半边搜索，直到左右边界重合，此时的临界点（即左右边界）即为最优解。
代码如下：


public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here
        //O(n*logM)? O(n*k)
        int l = 0;
        int r = 9999999;
//首先将时间的范围设为所有整数（0～99999999）。计算中点作为这次的假设时间临界点，尽量让每个人的工作时间都接近这个临界点。
//假设当前某个人之前分配的书的页数加上当前书的页数小于当前临界点，则直接把这本书分配给这个人而不会影响最优解；
//若大于，则看当前书的页数是否大于临界点： 
//1）若大于，则说明当前的临界值太小，连这本书都不能copy完全，所以最优解一定大于当前临界点，因此要增大临界点再重复2
// 2）若小于，则将书分配给下一个人copy
//若所有书分配完时，所需要的人数比k小（即还剩下人没用），则说明每个人干活的时间太多，最优时间一定比当前值小，反之则说明每个人干活的时间太少，最优时间比当前值大
//考虑3-4中的所有情况，若最优解比当前临界点小，则向当前临界点左半边搜索，否则向当前临界点右半边搜索，直到左右边界重合，此时的临界点（即左右边界）即为最优解。


        while( l <= r){
            int mid = l + (r - l) / 2;
            if(search(mid,pages,k))
                r = mid-1;
            else
                l = mid+1;
        }
        return l;
    }

    private boolean search(int total, int[] page, int k){
    //至少有一个人copy，所以count从1开始
        int count = 1;
        int sum = 0;
        for(int i = 0; i < page.length;) {
            if(sum + page[i] <= total){
                sum += page[i++];
// Suppose that the number of pages of a book previously allocated by a certain person plus the number of pages in the current book is less than the current critical point, then the book is directly assigned to this person without affecting the optimal solution;
//If greater than, then look at the number of pages in the current book is greater than the critical point: 1) If greater than, then the current critical value is too small, even the book can not be copied completely, so the optimal solution must be greater than the current critical point, So increase the critical point and repeat 2 2) If less than, assign the book to the next person copy
//If all the books are allocated, the required number of people is smaller than k (ie, the remaining people are useless), it means that each person has too much time for work, the optimal time must be smaller than the current value, otherwise it means that each Personal working hours are too short, the optimal time is greater than the current value
//Consider all cases in 3-4. If the optimal solution is smaller than the current critical point, then search the left half of the current critical point, otherwise search the right half of the current critical point until the left and right boundaries coincide, the critical point at this time ( The left and right boundaries are the optimal solutions.
            }else if(page[i] <= total){
                sum = 0;
                count++;
            }else{
                return false;
            }
        }

        return count <= k;
    }
}