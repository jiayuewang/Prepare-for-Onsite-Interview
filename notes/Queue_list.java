class Queue_list {
  // Linked list implementation
  Node first, last;

  public static class Node {
    Object data;
    Node next;
    public Node(Object d) {
      data = d;
      next = null;
    }
  }

  void enqueue(Object item) {
    // If queue is empty, set front = newNode and last = newNode
    if (first == null) {
      last = new Node(item);
      first = last;
    }
    // if not empty, last.next = newNode and last = newNode
    else {
      last.next = new Node(item);
      last = last.next;
    }
  }

  Object dequeue() {
    if (first == null) {
      System.out.println("Queue is empty!");
      return null;
    }
    // If not empty, make create temp to hold first, then set first.next = first
    else {
      Node temp = first;
      first = first.next;
      return temp.data;
    }
  }

  public Queue_list() {
    Node first = new Node(null);
    Node last = new Node(null);
  }
 }
