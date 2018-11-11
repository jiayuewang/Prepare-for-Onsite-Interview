class BinaryTree {
  public static class Node {
    int key;
    Node left;
    Node right;

    public Node(int data) {
      this.key = data;
      left = right = null;
    }
  }

  Node root;

  public BinaryTree(int key) {
    root = new Node(key);
  }

  public BinaryTree() {
    root = null;
  }

  public void print_inOrder(Node node) {
    if (node == null) {
      return;
    }
    // Traverse left-subtree
    print_inOrder(node.left);
    // print root
    System.out.print(node.key + " ");
    // Traverse right-subtree
    print_inOrder(node.right);
  }

  public void print_preOrder(Node node) {
    if (node == null) {
      return;
    }
    // Visit root
    System.out.print(node.key + " ");
    // traverse left
    print_preOrder(node.left);
    // traverse right
    print_preOrder(node.right);
  }

  public void print_postOrder(Node node) {
    if (node == null) {
      return;
    }
    // left
    print_postOrder(node.left);
    // right
    print_postOrder(node.right);
    // root
    System.out.print(node.key + " ");
  }

  public int maxDepth(Node node) {
    if (node == null) {
      return 0;
    }
    else {
      // compute depth of each subtree
      int lDepth = maxDepth(node.left);
      int rDepth = maxDepth(node.right);
      return lDepth > rDepth ? lDepth + 1 : rDepth + 1;
    }
  }


  // For binary search trees
  public Node search(Node root, int key) {
    // Base cases
    if (root == null || root.key == key) {
      return root;
    }
    if (root.key > key) {
      return search(root.left, key);
    }
    // else
    return search(root.right, key);
  }

  public void insert(int key) {
    root = insert_Recursive(root, key);
  }

  public Node insert_Recursive(Node root, int key) {
    // new key is always inserted at leaf.
    // search a key until we hit a leaf node. once we hit leaf, add a node
    if (root == null) {
      root = new Node(key);
    }

    if (key < root.key) {
      root.left = insert_Recursive(root.left, key);
    }
    else if (key > root.key) {
      root.right = insert_Recursive(root.right, key);
    }

    return root;
  }

  public void deleteKey(int key) {
    root = delete_Recursive(root, key);
  }

  public Node delete_Recursive(Node root, int key) {
    if (root == null) {
      return root;
    }

    if (key < root.key) {
      root.left = delete_Recursive(root.left, key);
    }
    else if (key > root.key) {
      root.right= delete_Recursive(root.right, key);
    }
    // Found the node to be deleted
    else {
      // Node only has one child or no child, copy the child to the node
      if (root.left == null) {
        return root.right;
      }
      else if (root.right == null) {
        return root.left;
      }

      // node with two children: Get the inorder successor (smallest in right subtree)
      root.key = minValue(root.right);

      // Delete inorder successor
      root.right = delete_Recursive(root.right, root.key);
    }
    return root;
  }

  public int minValue(Node root) {
    // Just keep going as left as possible. Ans return value
    int minv = root.key;
    while (root.left != null) {
      minv = root.left.key;
      root = root.left;
    }
    return minv;
  }

  // approach is to check if the max of left subtree of node is smaller than the node
  // and check if the min of right subtree is greater than the node
  // a correct but INefficient way is to use minValue and Max value helpers

  // a correct and efficient way is to write a utility helper that traverses down
  // the tree keeping track of the narrowing min and max values as it goes

  public boolean isBST(Node root) {
    return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public boolean isBSTUtil(Node node, int min, int max) {
    if (node == null) {
      return true;
    }

    if (node.key < min || node.key > max) {
      return false;
    }

    return (isBSTUtil(node.left, min, node.key - 1) &&
            isBSTUtil(node.right, node.key+1, max));
  }
}
