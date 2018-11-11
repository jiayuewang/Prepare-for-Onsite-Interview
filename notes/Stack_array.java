class Stack_array {
  int max;
  int top;
  int array[];

  public Stack_array(int max) {
    this.max = max;
    this.array = new int[this.max];
    this.top = -1;
  }

  boolean isEmpty() {
    return (top < 0);
  }

  boolean push(int x) {
    if (top >= max) {
      System.out.println("Stack overflow");
      return false;
    }
    else {
      // pre-increment top before accessing array
      array[++top] = x;
      return true;
    }
  }

  int pop() {
    if(this.isEmpty()) {
      System.out.println("Stack underflow");
      return 0;
    }
    else {
      // post-decrement top
      int temp = array[top--];
      return temp;
    }
  }
}
