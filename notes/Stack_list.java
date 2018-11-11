class Stack_list {
  Node top;

  public static class Node {
    Object data;
    Node next;
    public Node(Object d) {
      data = d;
      next = null;
    }
  }

  Object pop() {
    if (top != null) {
      Object item = top.data;
      top = top.next;
      return item;
    }
    return null;
  }

  void Push(Object item) {
    Node newNode = new Node(item);
    newNode.next = top;
    top = newNode;
  }

  Object peek() {
    return top.data;
  }

  public Stack_list() {
    Node top = null;
  }
}
