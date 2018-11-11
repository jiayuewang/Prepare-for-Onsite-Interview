public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        int size = nums.length;
        if (size == 0 || size < k) {
            return result;
        }

        PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(11, Collections.reverseOrder());

        int median = nums[0];
        int j = 0;
        if (k == 1) {
            result.add(median);
        }

        for (int i = 1; i < size; i++) {
            if (nums[i] > median) {
                minPQ.offer(nums[i]);
            } else {
                maxPQ.offer(nums[i]);
            }

            if (i > k - 1) {
                if (nums[j] > median) {
                    minPQ.remove(nums[j]);
                } else if (nums[j] < median) {
                    maxPQ.remove(nums[j]);
                } else {
                    median = Integer.MIN_VALUE;
                }
                j++;
            }

            if (median == Integer.MIN_VALUE) {
                median = minPQ.size() > maxPQ.size() ? minPQ.poll() : maxPQ.poll();
            } else {
                while (minPQ.size() >= maxPQ.size() + 2) {
                    maxPQ.offer(median);
                    median = minPQ.poll();
                }
                while (maxPQ.size() >= minPQ.size() + 1) {
                    minPQ.offer(median);
                    median = maxPQ.poll();
                }
            }
            if (i >= k - 1) {
                result.add(median);
            }
        }

        return result;
    }
}
