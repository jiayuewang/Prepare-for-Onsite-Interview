package com.fishercoder.solutions;

/**
 * 11. Container With Most Water
 *
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
*/
 public int maxArea(int[] height) {
        // ²ÎÊýÐ£Ñé
        if (height == null || height.length < 2) {
            return 0;
        }


        int result = 0;

        int left = 0;
        // 
        int right = height.length - 1;

        while (left < right) {
            result = Math.max(result, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                int k = left;
                while (k < right && height[k] <= height[left]) {
                    k++;
                }

                left = k;
            }
            else {
                int k = right;
                while (k > left && height[k] <= height[right]) {
                    k--;
                }

                right = k;
            }
        }

        return result;
    }
}
