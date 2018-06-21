/**
 *
 * Given an array of integers, move all the 0s to the right end of the array.
 * The relative order of the elements in the original array does not need to be maintained.
 *
 * Assumptions:
 *    The given array is not null.
 * Examples:
 *    {1} --> {1}
 *    {1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0}
 */

public class Move0sToTheEndI {

  // maintain two indexes left = 0 and right = array.length - 1
  // scan through [left, right], while (left <= right)
  //   if array[left] == 0, swap(array, left, right--);
  //   else left++;

  // Time: O(n)
  // Space: O(1)
  public int[] moveZero(int[] array) {
    // Corner Cases
    if (array == null || array.length <= 1) {
      return array;
    }

    // elements in the range of [left, right] haven't been scanned
    // elements on the left of index left are non-zero elements
    // elements on the right on index right are zeros
    int left = 0, right = array.length - 1;

    while (left <= right) {
      if (array[left] == 0) {
        swap(array, left, right--);
      } else {
        left++;
      }
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
