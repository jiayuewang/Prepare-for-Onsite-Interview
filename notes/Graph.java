import java.io.*;
import java.util.*;

class Graph {
  private int V; //No. of vertices
  private LinkedList<Integer> adj[];

  public Graph(int v) {
    V = v;
    adj = new LinkedList[v];
    // fill the linked list
    for (int i = 0; i < v; i++) {
      adj[i] = new LinkedList();
    }
  }

  // This is like a dictionary where v -> w
  void addEdge(int v, int w) {
    adj[v].add(w);
  }

  // Node can also have a visited property. Then we dont need this array see below.
  void DFSUtil(int v, boolean visited[]) {

    // Mark node as visited and print;
    visited[v] = true;
    System.out.print(v + " ");

    // Recur for all the vertices adjacent to this vertex
    Iterator<Interger> i = adj[v].listIterator();
    while (i.hasNext()) {
      int n = i.next();
      if (!visited[n]) {
        DFSUtil(n, visited);
      }
    }
  }

  // Above code traverses only the vertices reachable from a given source vertex.
  // To do complete DFS traversal of disconnected graphs, we must call DFSUtil()
  // for every vertex.4
  void DFS(int v) {
    // mark all the vertices as not visited
    boolean visited[] = new boolean[V];
    DFSUtil(v, visited);
  }

  // If node has a visited property, no need for array
  // Non iterative approach
  void DFS_alt() {
    Stack stack = new Stack();

    stack.push(this.root);
    root.visited = true;
    printNode(root);

    while(!stack.isEmpty()) {
      Node node = (Node)s.peek();
      Node child = getUnvisitedChildNode(n);
      if (child != null) {
        child.visited = true;
        printNode(child);
        stack.push(child);
      }
      else {
        stack.pop();
      }
    }
    clearVisitedProperty();
  }

  void BFS(int v) {
    boolean visited[] = new boolean[V];

    Queue queue = new Queue();
    visited[v] = true;
    queue.enqueue(v); //add to end of the queue

    while (!queue.isEmpty()) {
      Node r = queue.dequeue(); //Remove from front of the queue
      printNode(r);

      // While loop goes through each item in the adjaceny list
      Iterator<Integer> i = adj[v].listIterator();
      while (i.hasNext()) {
        int n = i.next();
        if (!visited[n]) {
          visited[n] = true;
          queue.enqueue(n);
        }
      }
    }
  }

}
