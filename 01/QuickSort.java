/*
 *
 * Given an array of integers, sort the elements in the array in ascending order.
 * The quick sort algorithm should be used to solve this problem.
 *
 * Examples
 *    {1} is sorted to {1}
 *    {1, 2, 3} is sorted to {1, 2, 3}
 *    {3, 2, 1} is sorted to {1, 2, 3}
 *    {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
 * Corner Cases
 *    What if the given array is null? In this case, we do not need to do anything.
 *    What if the given array is of length zero? In this case, we do not need to do anything.
 */

public class QuickSort {

  // Partition: 
  //    randomly select an index, the value of the selected index is pivot,
  //    put the index to the end of current array, scan through [left, right - 1],
  //    elements that are smaller than pivot are put on the left of index left,
  //    elements that are larger than pivot are put on the right of index right.
  //    When index right < index left, swap index left and index right, put pivot back to index left.
  //    Now, every elements on the left of pivot are smaller than pivot,
  //    every elements on the right of pivot are larger than pivot;
  // Quick select left of pivot and right of pivot separately, until there are only one elements in the array

  // Time: average O(nlogn), worst O(n^2)
  // Space: average O(logn), worst O(n), the height of recursion tree
  public int[] quickSort(int[] array) {
    // Corner Cases
    if (array == null || array.length <= 1) {
      return array;
    }

    quickSort(array, 0, array.length - 1);

    return array;
  }

  // helper function: quickSort
  // left: sort starts at index left
  // right: sort ends at index right
  private void quickSort(int[] array, int left, int right) {
    // base case
    if (right <= left) {
      return;
    }

    // Partition
    int pivotIndex = partition(array, left, right);
    // Quicksort left half and right half separately
    quickSort(array, left, pivotIndex);
    quickSort(array, pivotIndex + 1, right);
  }

  // helper function: quickSelect
  // left: partition starts at index left
  // right: partition ends at index right
  private int partition(int[] array, int left, int right) {
    // Randomly select a pivot index
    int pivotIndex = new Random().nextInt(right - left + 1) + left;
    // get pivot value
    int pivot = array[pivotIndex];
    // Swap pivot to the right index
    swap(array, pivotIndex, right);

    // partition starts at index left
    int leftIndex = left;
    // partition ends at index right - 1
    int rightIndex = right - 1;

    while (leftIndex <= rightIndex) {
      if (array[leftIndex] < pivot) {
        leftIndex++;
      } else if (array[rightIndex] >= pivot) {
        rightIndex--;
      } else {
        swap(array, leftIndex++, rightIndex--);
      }
    }

    // swap back pivot to leftIndex
    swap(array, leftIndex, right);

    return leftIndex;
  }

  // helper function: swap
  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}
