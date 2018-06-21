/**
 *
 * Given an array of integers, sort the elements in the array in ascending order.
 * The selection sort algorithm should be used to solve this problem.
 *
 * Examples
 *    {1} is sorted to {1}
 *    {1, 2, 3} is sorted to {1, 2, 3}
 *    {3, 2, 1} is sorted to {1, 2, 3}
 *    {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
 *
 * Corner Cases
 *    What if the given array is null? In this case, we do not need to do anything.
 *    What if the given array is of length zero? In this case, we do not need to do anything.
 */

public class SelectionSort {

  // Go through every index (i) from index 0 to the second last index
  // start with each index (j = i + 1) to the end, find the min value's index, swap the min value to index i

  // Time: O(n^2)
  // Space: O(1)
  public int[] selectionSort(int[] array) {
    // Corner Cases
    if (array == null || array.length <= 1) {
      return array;
    }

    // find the min value to put at index i from index 0 to index length - 1,
    for (int i = 0; i < array.length - 1; i++) {
      // Initialize minInde as i
      int minIndex = i;
      // find the min value's index from i + 1 to the end of array
      for (int j = i + 1; j < array.length; j++) {
        // found value < current min
        if (array[j] < array[minIndex]) {
          // update minIndex
          minIndex = j;
        }
      }
      // swap minIndex to current i
      swap(array, minIndex, i);
    }

    return array;
  }

  // helper function: swap
  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}
