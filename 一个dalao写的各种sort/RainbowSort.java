/**
 *
 * Given an array of balls, where the color of the balls can only be Red,
 * Green or Blue, sort the balls such that all the Red balls are grouped on the left side,
 * all the Green balls are grouped in the middle and all the Blue balls are grouped on the right side.
 * (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by 1).
 *
 * Examples
 *    {0} is sorted to {0}
 *    {1, 0} is sorted to {0, 1}
 *    {1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}
 * Assumptions
 *    The input array is not null.
 * Corner Cases
 *    What if the input array is of length zero? In this case, we should not do anything as well.
 */

public class RainbowSort {

  // Start with three indexes, i, j, k
  // elements in the range of [j, k] haven't been scanned
  // elements on the left of index i are -1
  // elements on the right of index k are 1
  // elements in the range of [i, j) are 0
  // Scan from j to k,
  //    if array[j] == -1, swap(array, i++, j++)
  //    if array[j] == 0, j++
  //    if array[j] == 1, swap(array, j, k--)

  // Time: O(n), scan through array once
  // Space: O(1), three indexes(i, j, k)
  public int[] rainbowSort(int[] array) {
    // Corner Cases
    if (array == null || array.length <= 1) {
      return array;
    }

    // elements in the range of [j, k] haven't been scanned
    // elements on the left of index i are -1
    // elements on the right of index k are 1
    // elements in the range of [i, j) are 0
    int i = 0, j = 0, k = array.length - 1;

    while (j <= k) {
      if (array[j] == -1) {
        swap(array, i++, j++);
      } else if (array[j] == 0) {
        j++;
      } else {
        swap(array, j, k--);
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
