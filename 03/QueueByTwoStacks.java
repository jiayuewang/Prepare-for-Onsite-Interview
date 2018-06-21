/**
 * By Jiapei Liang
 * Java: Implement a queue by using two stacks. The queue should provide size(),
 * isEmpty(), offer(), poll() and peek() operations. When the queue is empty,
 * poll() and peek() should return null.
 *
 * Assumptions
 *    The elements in the queue are all Integers.
 *    size() should return the number of elements buffered in the queue.
 *    isEmpty() should return true if there is no element buffered in the queue, false otherwise.
 *
 **/

public class QueueByTwoStacks {

  // Using Two stacks to implement one queue

  // in stack is for offer operations
  private Deque<Integer> in;
  // out stack is for peek and poll operations
  private Deque<Integer> out;

  // Initialize in and out stacks
  public QueueByTwoStacks() {
    in = new LinkedList<>();
    out = new LinkedList<>();
  }

  public Integer poll() {
    // if out stack is empty, move all elements from in stack to out stack
    inToOut();
    // Poll element from out stack and return, if out stack is empty, return null
    return out.pollLast();
  }


  public void offer(int element) {
    // offer element into in stack
    in.offerLast(element);
  }

  public Integer peek() {
    // if out stack is empty, move all elements from in stack to out stack
    inToOut();
    // return out stack's peek, if empty, return null
    return out.peekLast();
  }

  public int size() {
    return in.size() + out.size();
  }

  public boolean isEmpty() {
    return in.isEmpty() && out.isEmpty();
  }

  // Move all elements from in stack to out stack if needed
  private void inToOut() {
    if (out.isEmpty()) {
      while (!in.isEmpty()) {
        out.offerLast(in.pollLast());
      }
    }
  }

}
