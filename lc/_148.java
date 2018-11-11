package com.fishercoder.solutions;

import com.fishercoder.common.classes.ListNode;

/**
 * 148. Sort List
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 */


public class Solution{
    private ListNode findMiddle(ListNode head){
        ListNode slow= head;
        ListNode fast=head.next;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
        }
        return slow;//???
    }
    private ListNode merge(ListNode head1,ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;
        
        while(head1!=null&&head2!=null){
            if(head1.val<head2.val){
             tail.next=head1;
             head1=head1.next;
            }
            else{
                tail.next=head2;
                head2=head2.next;
                
            }
            tail=tail.next;//??
        }
        if(head1!=null){
            tail.next=head1;
        }else{
            tail.next=head2;
        }
        return dummy.next;
    }
    
    public ListNode sortList(ListNode head){
        if(head==null||head.next==null){// at the end of the list
            return head;
        }
        ListNode mid= findMiddle(head);
        ListNode right=sortList(mid.next);//??
        mid.next=null;
        ListNode left= sortList(head);
             return merge(left, right);
    }
}

