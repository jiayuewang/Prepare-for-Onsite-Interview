package com.fishercoder.solutions;

import com.fishercoder.common.classes.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 *
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.*/

// Solution 1: Divide and Conquer 自顶向下 O(n log k), n is # of all ListNodes; O(log k) 比Heap快很多 13ms 89%
// 这题是21题的Follow Up，难度增加，变成合并k个有序链表了，但是不管合并几个，基本还是要两两合并。那么我们首先考虑的方法是能不能利用之前那道题的解法来解答此题。答案是肯定的，但是需要修改，怎么修改呢，最先想到的就是naive的两两合并，就是前两个先合并，合并好了再跟第三个，然后第四个直到第k个。这样的思路是对的，但是效率不高，时间复杂度是O(n * k)。所以我们只能换一种思路，这里就需要用到分治法。简单来说就是不停的对半划分，比如k个链表先划分为合并两个k/2个链表的任务，再不停的往下划分，直到划分成只有一个或两个链表的任务，开始合并。举个例子来说比如合并6个链表，那么按照分治法，我们首先分别合并1和4,2和5,3和6。这样下一次只需合并3个链表，我们再合并1和3，最后和2合并就可以了。
// T(k) = 2T(k/2) + O(n)，O(n)=O(a*k)，a是链表的平均长度，由主定理可以知道时间复杂度为O(n log k)



 public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) { //LeetCode少有的有空数组这个corner case的题目
            return null; //边界判断写这里比较好，若写在divide函数，每次递归都要判断，低效
        }
        return divide(lists, 0, lists.length - 1);
    }

    private ListNode divide(ListNode[] lists, int s, int e) {
        if (s == e) {
            return lists[s];
        }
        return merge(divide(lists, s, s + (e - s) / 2),
                     divide(lists, s + (e - s) / 2 + 1, e));
    }

    private ListNode merge(ListNode l1, ListNode l2) { //和21题的代码完全一样
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

//     Solution 2: Heap O(n log k); O(k) 略慢 21ms 38%
// 因为合并k个排序链表实际上是每次要从k个数中选出最小的数，这种要求自然想到用heap。
// 首先把k个链表的首元素都加入最小堆中，即建堆。然后我们每次取出最小的那个元素加入我们最终结果的链表中，然后把取出元素的下一个元素再加入堆中，下次仍从堆中取出最小的元素做相同的操作，以此类推，直到堆中没有元素了，此时k个链表也合并为了一个链表，返回首节点即可。


public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        Queue<ListNode> heap = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) { //corner case
                heap.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!heap.isEmpty()) {
            cur.next = heap.poll(); //O(log k)
            if (heap.size() == 0) { //a small optimization，有面试官会问到这个优化
                return dummy.next;
            }
            cur = cur.next;
            if (cur.next != null) {
                heap.offer(cur.next); //O(log k)
            }
        }
        return dummy.next;
    }