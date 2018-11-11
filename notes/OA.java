class Solution {
    public int maxSubarraySumCircular(int[] A) {
        // Java program for maximum contiguous circular sum problem 

		int n = A.length; 

		// Case 1: get the maximum sum using standard kadane' 
		// s algorithm 
		int max_kadane = kadane(A); 

		// Case 2: Now find the maximum sum that includes 
		// corner elements. 
		int max_wrap = 0; 
		for (int i = 0; i < n; i++) 
		{ 
			max_wrap += A[i]; // Calculate array-sum 
			A[i] = -A[i]; // invert the array (change sign) 
		} 

		// max sum with corner elements will be: 
		// array-sum - (-max subarray sum of inverted array) 
		max_wrap = max_wrap + kadane(A); 

		// The maximum circular sum will be maximum of two sums 
		return (max_wrap > max_kadane)? max_wrap: max_kadane; 
	} 

	// Standard Kadane's algorithm to find maximum subarray sum 
	// See https://www.geeksforgeeks.org/archives/576 for details 
	static int kadane (int[] A) 
	{ 
		int n = A.length; 
		int cur = 0, pre = 0; 
		for (int i = 0; i < n; i++) 
		{ 
            
			pre = pre + A[i]; 
			if (pre < 0) 
				pre = 0; 
			if (cur < pre) 
				cur = pre; 
		} 
		return cur; 
	} 

// 	public static void main (String[] args) 
// 	{ 
// 		int a[] = {11, 10, -20, 5, -3, -5, 8, -13, 10}; 
// 			System.out.println("Maximum circular sum is " + 
// 							maxCircularSum(a)); 
// 	} 
// } /* This code is contributed by Devesh Agrawal*/

    }
