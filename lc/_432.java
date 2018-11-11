package com.fishercoder.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 432. All O`one Data Structure
 * Implement a data structure supporting the following operations:
*/
 // 1. Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 // 2. Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 // 3. GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 // 4. GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".

 // Challenge: Perform all these in O(1) time complexity.

 // */
// 题解
// 1，2只需要一个Map来记录key对应的count。但是3，4需要keep一个count的order，这样头和尾分别就能直接读出min和max（需要keep head和tail pointer）。于是需要使用一个linear data structure，包括array，LinkedList，和array实现的queue和stack。但因为1，2要求O(1) random access，add，delete，只有LinkedList可以实现后面两点；然后因为要建立count的order，自然可以用count作为key，ListNode作为value来用map在constant time实现random access。因为每一个node由一个key index，因此这个node需要存放对应这个count的所有key。
// 大方向定了以后，难点或者说细节在于怎么keep这个order。可以列举所有情况：
// 设count目前Key的count

// 1. Inc(Key)：

// if count+1对应的node存在，直接放入
// else，创建count+1对应的node，插入到Key对应的node之后；此时如果这个新node为List尾，则更新tail pointer。要处理Key=0，即对应node为整个List的第一个这种base case。
// 最后要从Key对应的node中去掉这个count对应的node

// 2. Dec(Key)：

// if Key-1对应的node存在，直接放入
// if count-1对应的node存在，直接放入
// else，创建count-1对应的node，插入到Key对应的node之后；此时如果这个新node为List尾，则更新tail pointer。要处理Key=0，即对应node为整个List的第一个这种base case。
// 最后要从Key对应的node中去掉这个

public class _432 {


    /**
     * credit: https://discuss.leetcode.com/topic/65634/java-ac-all-strict-o-1-not-average-o-1-easy-to-read/2
     */
    class AllOne {
        // maintain a doubly linked list of Buckets
        private Bucket head;
        private Bucket tail;
        // for accessing a specific Bucket among the Bucket list in O(1) time
        private Map<Integer, Bucket> countBucketMap;
        // keep track of count of keys
        private Map<String, Integer> keyCountMap;

        // each Bucket contains all the keys with the same count
        private class Bucket {
            int count;
            Set<String> keySet;
            Bucket next;
            Bucket pre;

            public Bucket(int cnt) {
                count = cnt;
                keySet = new HashSet<>();
            }
        }

        /**
         * Initialize your data structure here.
         */
        public AllOne() {
            head = new Bucket(Integer.MIN_VALUE);
            tail = new Bucket(Integer.MAX_VALUE);
            head.next = tail;
            tail.pre = head;
            countBucketMap = new HashMap<>();
            keyCountMap = new HashMap<>();
        }

        /**
         * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
         */
        public void inc(String key) {
            if (keyCountMap.containsKey(key)) {
                changeKey(key, 1);
            } else {
                keyCountMap.put(key, 1);
                if (head.next.count != 1) {
                    addBucketAfter(new Bucket(1), head);
                }
                head.next.keySet.add(key);
                countBucketMap.put(1, head.next);
            }
        }

        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
         */
        public void dec(String key) {
            if (keyCountMap.containsKey(key)) {
                int count = keyCountMap.get(key);
                if (count == 1) {
                    keyCountMap.remove(key);
                    removeKeyFromBucket(countBucketMap.get(count), key);
                } else {
                    changeKey(key, -1);
                }
            }
        }

        /**
         * Returns one of the keys with maximal value.
         */
        public String getMaxKey() {
            return tail.pre == head ? "" : (String) tail.pre.keySet.iterator().next();
        }

        /**
         * Returns one of the keys with Minimal value.
         */
        public String getMinKey() {
            return head.next == tail ? "" : (String) head.next.keySet.iterator().next();
        }

        // helper function to make change on given key according to offset
        private void changeKey(String key, int offset) {
            int count = keyCountMap.get(key);
            keyCountMap.put(key, count + offset);
            Bucket curBucket = countBucketMap.get(count);
            Bucket newBucket;
            if (countBucketMap.containsKey(count + offset)) {
                // target Bucket already exists
                newBucket = countBucketMap.get(count + offset);
            } else {
                // add new Bucket
                newBucket = new Bucket(count + offset);
                countBucketMap.put(count + offset, newBucket);
                addBucketAfter(newBucket, offset == 1 ? curBucket : curBucket.pre);
            }
            newBucket.keySet.add(key);
            removeKeyFromBucket(curBucket, key);
        }

        private void removeKeyFromBucket(Bucket bucket, String key) {
            bucket.keySet.remove(key);
            if (bucket.keySet.size() == 0) {
                removeBucketFromList(bucket);
                countBucketMap.remove(bucket.count);
            }
        }

        private void removeBucketFromList(Bucket bucket) {
            bucket.pre.next = bucket.next;
            bucket.next.pre = bucket.pre;
            bucket.next = null;
            bucket.pre = null;
        }

        // add newBucket after preBucket
        private void addBucketAfter(Bucket newBucket, Bucket preBucket) {
            newBucket.pre = preBucket;
            newBucket.next = preBucket.next;
            preBucket.next.pre = newBucket;
            preBucket.next = newBucket;
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
