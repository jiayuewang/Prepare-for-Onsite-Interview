package com.fishercoder.solutions;

import com.fishercoder.common.classes.ListNode;

/**
 * 86. Partition List
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

 You should preserve the original relative order of the nodes in each of the two partitions.

 For example,
 Given 1->4->3->2->5->2 and x = 3,
 return 1->2->2->4->3->5.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode cur=head;
        
        ListNode smaller_sentinel=new ListNode(0);
        ListNode smaller_cur=smaller_sentinel;
        ListNode larger_sentinel=new ListNode(0);
        ListNode larger_cur=larger_sentinel;
//Now, go along the list, partitioning into two halves.        
        while(cur!=null){
            if(cur.val<x){
                    smaller_cur.next=cur;
                    smaller_cur=smaller_cur.next;
                
            }else{
                    larger_cur.next=cur;
                    larger_cur=larger_cur.next;
            }
            cur=cur.next;
        }
//Now, do the concatenation of two havles. Make sure the last node points to null 
        larger_cur.next=null;
        smaller_cur.next=larger_sentinel.next;
        return smaller_sentinel.next;
    }
}      
            //小于3的用samll接，大于的用big接
            //两个链表相连接
