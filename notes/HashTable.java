import java.util.*;

class HashNode<K,V> {
  K key;
  V value;
  HashNode<K,V> next;

  public HashNode(K key, V value) {
    this.key = key;
    this.value = value;
  }
}

class HashTable<K,V> {
  private ArrayList<HashNode<K,V>> bucket;
  private int numBuckets;
  private int size;

  public HashTable(int numBuckets) {
    bucket = new ArrayList<>();
    this.numBuckets = numBuckets;

    // Create empty chains
    for (int i = 0; i < numBuckets; i++) {
      bucket.add(null);
    }
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return getSize() == 0;
  }

  // Hash function
  private int hash(K key) {
    int hashCode = key.hashCode();
    int index = hashCode % numBuckets;
    return index;
  }

  // Remove given the key
  public V remove (K key) {
    // Apply hash function
    int index = hash(key);
    // Get head of chain
    HashNode<K,V> head = bucket.get(index);

    // Search for key in its chain
    HashNode<K,V> prev = null;
    while (head != null) {
      // found key
      if (head.key.equals(key)) {
        break;
      }

      // else keep moving up the chain
      prev = head;
      head = head.next;
    }
    // if not found at end of the linked-list
    if (head == null) {
      prev.next = head.next;
    }

    // if found, remove key
    if (prev != null) {
      prev.next = head.next;
    }
    else {
      prev.next = head.next;
    }

    // size down
    size--;
    return head.value;
  }

  // Returns value for a key
  public V get(K key) {
    // Find head of chain for given key
    int index = hash(key);
    HashNode<K,V> head = bucket.get(index);

    // Search key in chain
    while(head != null) {
      if (head.key.equals(key)) {
        return head.value;
      }
      head = head.next;
    }
    // Not found
    return null;
  }

  // Add a key value pair to hash
  public void add(K key, V value) {
    int index = hash(key);
    HashNode<K,V> head = bucket.get(index);

    // Check if key is already present if so, update value
    while (head != null) {
      if (head.key.equals(key)) {
        head.value = value;
        return;
      }
      head = head.next;
    }

    // insert new key in chain
    head = bucket.get(index);
    HashNode<K,V> newNode = new HashNode<K,V>(key, value);
    newNode.next = head;
    bucket.set(index, newNode);

    // size up
    size++;
  }
}
