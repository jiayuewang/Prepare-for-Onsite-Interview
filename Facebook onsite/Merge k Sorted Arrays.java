//merge k sorted arrays
public class Solution {
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public List<Integer> mergekSortedArrays(int[][] arrays) {
        List<Integer> res = new ArrayList<>();
        if (arrays == null || arrays[0] == null) {
            return null;
        }
        if (arrays.length == 0 || arrays[0].length == 0) {
            return res;
        }
        int[] newArray = mergeHelper(arrays, 0, arrays.length - 1);
        for (int i = 0; i < newArray.length; i++) {
            res.add(newArray[i]);
        }
        return res;
    }
    
    private int[] mergeHelper(int[][] arrays, int start, int end) {
        if (start == end) {
            return arrays[start];
        }
        int mid = start + (end - start) / 2;
        int[] left = mergeHelper(arrays, start, mid);
        int[] right = mergeHelper(arrays, mid + 1, end);
        return mergeTwoArrays(left, right);
    }
    
    private int[] mergeTwoArrays(int[] array1, int[] array2) {
        int a1 = array1.length;
        int a2 = array2.length;
        int[] newArray = new int[a1 + a2];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a1 && j < a2) {
            if (array1[i] < array2[j]) {
                newArray[k++] = array1[i++];
            } else {
                newArray[k++] = array2[j++];
            }
        }
        while (i < a1) {
            newArray[k++] = array1[i++];
        }
        while (j < a2) {
            newArray[k++] = array2[j++];
        }
        return newArray;
    }
}

// iterator for merge k sorted arrays, heap: O(nlogk) time, O(k) space
public class iteratorForMergekSortedArrays {
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public class ArrayIterator {
        int val;//use val to store the next value of this array
        Iterator it;//store this array's iterator
        public ArrayIterator(Iterator i) {
            val = i.next();
            it = i;
        }
    }
    
    private Queue<ArrayIterator> heap;
    
    public iteratorForMergekSortedArrays(ArrayList<ArrayList<Integer>> arrays) {
        heap = new PriorityQueue<>(1, new Comparator<ArrayIterator>(){
            public int compare(ArrayIterator a, ArrayIterator b) {
                return a.val - b.val;
            }
        });
        for (ArrayList array : arrays) {
            if (!array.isEmpty()) {
                ArrayIterator i = new ArrayIterator(array.iterator());
                heap.offer(i);
            }
        }
    }
    
    public int next() {//assume next() will be called only if hasNext() is true
        ArrayIterator i = heap.poll();
        int res = i.val;
        if (i.it.hasNext()) {
            i.val = i.it.next();
            heap.offer(i);
        }
        return res;
    }

    public boolean hasNext() {
        return !heap.isEmpty();
    }
}