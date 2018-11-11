/*
 *
 * Given an array of integers, sort the elements in the array in ascending order.
 * The merge sort algorithm should be used to solve this problem.
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

public class MergeSort {

  // Divide and conquer
  //    Divide:
  //      divide array into left half and right half, mergeSort them separately,
  //      until every half array contains only one element.
  //    Conquer:
  //      Compare left half and right half, put the element into helper array one by one,
  //      now elements in the helper array are sorted in ascending order.
  //      put the elements back to original array from helper array

  // Time: O(nlogn), during merge, need to compair n / 2 times for every level, there are logn levels
  // Space: O(n), helper array with size n
  public int[] mergeSort(int[] array) {
    // Corner Cases
    if (array == null || array.length <= 1) {
      return array;
    }

    // helper array to help merge
    int[] helper = new int[array.length];

    mergeSort(array, 0, array.length - 1, helper);

    return array;
  }

  // helper function: mergeSort
  // left: sort from index left
  // right: sort to index right
  private void mergeSort(int[] array, int left, int right, int[] helper) {
    // base case
    if (right <= left) {
      return;
    }

    int mid = left + (right - left) / 2;
    // Divide: divide array to half size and mergeSort them separately
    mergeSort(array, left, mid, helper);
    mergeSort(array, mid + 1, right, helper);
    // Conquer: merge
    merge(array, left, mid, right, helper);
  }

  // helper function: merge
  // left: merge starts at index left
  // mid: left half array ends at index mid
  // right: merge ends at index right
  // helper: helper array to help merge
  private void merge(int[] array, int left, int mid, int right, int[] helper) {
    // copy elements to helper array from left to right
    for (int i = left; i <= right; i++) {
      helper[i] = array[i];
    }

    // start index of left half array
    int leftIndex = left;
    // start index of right half array
    int rightIndex = mid + 1;

    // Compare left half and right half, put the smaller element back to original array one by one
    while (leftIndex <= mid && rightIndex <= right) {
      if (helper[leftIndex] < helper[rightIndex]) {
        array[left++] = helper[leftIndex++];
      } else {
        array[left++] = helper[rightIndex++];
      }
    }

    // While there are some elements left in left half, append them to array
    while (leftIndex <= mid) {
      array[left++] = helper[leftIndex++];
    }

    // While there are some elements left in right half, don't need to append them to array
    // Since they are already at correct postions
  }

}
