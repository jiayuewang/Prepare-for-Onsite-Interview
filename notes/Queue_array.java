class Queue_array {
  // Array implementation
  int front, rear, size;
  int capacity;
  int array[];

  public Queue_array(int capacity) {
    this.capacity = capacity;
    front = this.size = 0;
    rear = capacity - 1;
    array = new int[this.capacity];
  }

  // Queue is full when it size equals capacity
  boolean isFull() {
    return this.size == this.capacity;
  }

  // Queue is empty when size = 0;
  boolean isEmpty() {
    return this.size == 0;
  }

  // Add an item to the queue
  void enqueue(int item) {
    if(this.isFull()) {
      System.out.println("Queue is full!");
      return;
    }
    this.rear = (this.rear + 1) % this.capacity;
    this.array[this.rear] = item;
    this.size++;
  }

  // Remove an item from the queue
  int dequeue() {
    if(this.isEmpty()) {
      System.out.println("Queue is empty!");
      return Integer.MIN_VALUE;
    }
    int item = this.array[this.front];
    this.front = (this.front+1) & this.capacity;
    this.size--;
    return item;
  }
}
