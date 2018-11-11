


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

// /**
//  * 284. Peeking Iterator
//  *
//  * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

//  Here is an example. Assume that the iterator is initialized to the beginning of the queue: [1, 2, 3].

//  Call next() gets you 1, the first element in the queue.

//  Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

//  You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

//  Follow up: How would you extend your design to be generic and work with all types, not just integer?
//  */
public class _284 {
    public static class PeekingIterator implements Iterator<Integer> {

        private Queue<Integer> queue;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            queue = new LinkedList<>();
            while (iterator.hasNext()) {
                queue.add(iterator.next());//poll 删除顶层的值
            }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return queue.peek();
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            return queue.poll();
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }


offer，add区别：



一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。

这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，而只是得到由 offer() 返回的 false。 

 

poll，remove区别：

remove() 和 poll() 方法都是从队列中删除第一个元素。remove() 的行为与 Collection 接口的版本相似，

但是新的 poll() 方法在用空集合调用时不是抛出异常，只是返回 null。因此新的方法更适合容易出现异常条件的情况。

 

peek，element区别：

element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null
}
