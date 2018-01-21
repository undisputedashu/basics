package com.basics.search;

public class LinearSearch {

	public static void main(String args[]) {
		int a[] = {2,3,1,5};
		int n = a.length;
		int k = 4;
		int ind = linearSearch(a, n, k);
		System.out.println(ind);
	}

	private static int linearSearch(int[] a, int n, int k) {
		for (int i=0;i<n;i++) {
			if (a[i] == k) return i;
		}
		return -1;
	}
}