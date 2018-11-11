/**
 *
 * Enhance the stack implementation to support min() operation.
 * min() should return the current minimum value in the stack.
 * If the stack is empty, min() should return -1.
 *
 * pop() - remove and return the top element, if the stack is empty, return -1
 * push(int element) - push the element to top
 * top() - return the top element without remove it, if the stack is empty, return -1
 * min() - return the current min value in the stack.
 *
 **/

public class StackWithMin {

  static class Element {
    int value;
    int size;
    Element(int value, int size) {
      this.value = value;
      this.size = size;
    }
  }

  // stack
  Deque<Integer> stack;
  // minStack to store current min
  Deque<Element> minStack;

  // Initialization
  public StackWithMin() {
    stack = new LinkedList<>();
    minStack = new LinkedList<>();
  }

  // Pop from stack
  public int pop() {
    if (stack.isEmpty()) {
      return -1;
    }
    // pop from minStack if possible
    if (minStack.peekLast().size == stack.size()) {
      minStack.pollLast();
    }
    return stack.pollLast();
  }

  // Push into stack
  public void push(int element) {
    stack.offerLast(element);
    // push to minStack if possible
    if (minStack.isEmpty() || element < minStack.peekLast().value) {
      minStack.offerLast(new Element(element, stack.size()));
    }
  }

  public int top() {
    if (stack.isEmpty()) {
      return -1;
    }
    return stack.peekLast();
  }

  public int min() {
    if (minStack.isEmpty()) {
      return -1;
    }
    return minStack.peekLast().value;
  }

}
