public class ZigzagIterator {//在Linkeidlist中（）不用加index 直接remove了头部元素
   LinkedList<Iterator> list = new LinkedList<>();
  
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
       if(!v1.isEmpty()) list.add(v1.iterator());
        if(!v2.isEmpty()) list.add(v2.iterator());
        
    }

    public int next() {
        Iterator poll = list.remove();
        int res = (Integer)poll.next();// 强制类型转换
        if(poll.hasNext()){
            list.add(poll);
        }
        return res;
    }

    public boolean hasNext() {
        return !list.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// /**
//  * 281. Zigzag Iterator
//  *
//  * Given two 1d vectors, implement an iterator to return their elements alternately.

//  For example, given two 1d vectors:

//  v1 = [1, 2]
//  v2 = [3, 4, 5, 6]
//  By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

//  Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

//  Clarification for the follow up question - Update (2015-09-18):
//  The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases.
//  If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

//  [1,2,3]
//  [4,5,6,7]
//  [8,9]
//  It should return [1,4,8,2,5,9,3,6,7]. -->
//  */

这道题让我们写一个之字形迭代器，跟之前那道Flatten 2D Vector有些类似，那道题是横向打印，这道题是纵向打印，
虽然方向不同，但是实现思路都是大同小异。我最先想到的方法是用两个变量i和j分别记录两个向量的当前元素位置，初始化为0，
然后当i<=j时，则说明需要打印v1数组的元素，反之则打印v2数组中的元素。在hasNext函数中，当i或j打印等于对应数组的长度时，
我们将其赋为一个特大值，这样不影响我们打印另一个数组的值，只有当i和j都超过格子数组的长度时，返回false，

public class _281 {

    public static class Solution1 {
        public static class ZigzagIterator {
            private Iterator<Integer> i;
            private Iterator<Integer> j;
            private Iterator<Integer> tmp;

            public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
                i = v2.iterator();
                j = v1.iterator();
            }

            public int next() {
                if (j.hasNext()) {
                    tmp = j;
                    j = i;
                    i = tmp;
                }
                return i.next();
            }

            public boolean hasNext() {
                return i.hasNext() || j.hasNext();
            }
        }
    }


    public static class Solution2 {
        public static class ZigzagIterator {

            Queue<Iterator<Integer>> queue;

            public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
                queue = new LinkedList<>();
                if (v1 != null && !v1.isEmpty()) {
                    Iterator<Integer> iterator1 = v1.iterator();
                    queue.offer(iterator1);
                }
                if (v2 != null && !v2.isEmpty()) {
                    Iterator<Integer> iterator2 = v2.iterator();
                    queue.offer(iterator2);
                }
            }

            public boolean hasNext() {
                return !queue.isEmpty();
            }

            public int next() {
                Iterator<Integer> iterator = queue.poll();
                int next = iterator.next();
                if (iterator.hasNext()) {
                    queue.offer(iterator);
                }
                return next;
            }
        }
    }
}
