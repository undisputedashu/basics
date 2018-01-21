package com.basics.search;

/**
 * Binary search
 * It can only be applied when array is sorted.
 * If array is not sorted, sort it first then apply binary search.
 * @author ashu
 *
 */
public class BinarySearch {

	public static void main(String args[]) {
		int a[] = {2,5,7,9,11};
		int n = a.length;
		int k = 19;
		int ind = binarySearchIterative(a, n, k);
		System.out.println("Iterative result = " + ind);
		
		ind = bs(a, 0, n-1, k);
		System.out.println("Recursive result = " + ind);
	}

	private static int bs(int[] a, int l, int r, int k) {
		if (l > r) return -1;
		int mid = l + (r-l)/2;
		if (a[mid] == k) return mid;
		else if (a[mid] < k) return bs(a, mid+1, r, k);
		else return bs(a, l, mid-1, k);
	}

	private static int binarySearchIterative(int[] a, int n, int k) {
		int l = 0, r = n-1;
		while (l<=r) {
			int mid = l + (r-l)/2;//think about this line, why it's written like this
			if (a[mid] == k) return mid;
			if (a[mid] < k) l = mid+1;
			else r = mid-1;
		}
		return -1;
	}
	
}