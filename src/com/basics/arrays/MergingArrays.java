package com.basics.arrays;

/**
 * https://www.geeksforgeeks.org/merge-one-array-of-size-n-into-another-one-of-size-mn/
 * @author ashu
 *
 */
public class MergingArrays {

	private static final Integer MIN = Integer.MIN_VALUE;

	public static void main(String args[]) {
		int a[] = {2, 8, MIN, MIN, MIN, 13, MIN, 15, 20};//size m+n
		int b[] = {5, 7, 9, 25};//size n
		
		moveToEnd(a);
		merge(a, b);
		for (int i=0;i<a.length;i++) System.out.println(a[i]);
	}

	private static void merge(int[] a, int[] b) {
		int n = a.length, m = b.length;
		int lb = 0, rb = m, tb = 0;
		while (lb<m && rb<n) {
			if (b[lb]<=a[rb]) a[tb++] = b[lb++];
			else a[tb++] = a[rb++];
		}
		while (lb<m) a[tb++] = b[lb++];
		while (rb<n) a[tb++] = a[rb++];
	}

	//Try to write this method yourself.
	private static void moveToEnd(int[] a) {
		int n = a.length;
		int j = n-1;
		for (int i=n-1;i>=0;i--) {
			if (a[i] != MIN) {
				a[j] = a[i];
				j--;
			}
		}
	}
	
}