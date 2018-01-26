package com.basics.arrays;

/**
 * https://www.geeksforgeeks.org/program-for-array-rotation-continued-reversal-algorithm/
 * @author ashu
 *
 */
public class RotateArray {

	public static void main(String args[]) {
		int a[] = {1,2,3,4,5};
		int d = 3;
		rotate(a,d);
		for (int i=0;i<a.length;i++) System.out.println(a[i]);
	}

	private static void rotate(int[] a, int d) {
		int n = a.length;
		rotate(a, 0, d-1);
		rotate(a, d, n-1);
		rotate(a, 0, n-1);
	}

	private static void rotate(int[] a, int l, int r) {
		while (l<r) {
			swap(a, l, r);
			l++;
			r--;
		}
	}

	private static void swap(int[] a, int l, int r) {
		int t = a[l];
		a[l] = a[r];
		a[r] = t;
	}
	
}