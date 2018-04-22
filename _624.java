package com.fishercoder.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 624. Maximum Distance in Arrays
 *
 * Given m arrays, and each array is sorted in ascending order.
 * Now you can pick up two integers from two different arrays (each array picks one)
 * and calculate the distance. We define the distance between two
 * integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

 Example 1:
 Input:
 [[1,2,3],
 [4,5],
 [1,2,3]]

 Output: 4

 Explanation:

 One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.

 Note:
 Each given array will have at least 1 number. There will be at least two non-empty arrays.
 The total number of the integers in all the m arrays will be in the range of [2, 10000].
 The integers in the m arrays will be in the range of [-10000, 10000].
 */
public class solution {

public int maxDistance(int[][] arrays) {
		if (arrays == null || arrays.length == 0) return 0;
		
		int diff = Integer.MIN_VALUE;
		int m = arrays.length;
		
		int min =  arrays[0][0], max = arrays[0][arrays[0].length-1];
		for (int i = 1; i < m; i++) {
			int head = arrays[i][0];
			int tail = arrays[i][arrays[i].length-1];
			diff = Math.max(Math.abs(max-head), diff);
                        diff = Math.max(Math.abs(tail-min), diff);
			max = Math.max(tail, max);
			min = Math.min(head, min);
		}
		
		return diff;
    }
