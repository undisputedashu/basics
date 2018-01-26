package com.basics.sort;

public class MergeSort {

	public static void main(String args[]) {
		int a[] = {4,1,5,3,2};
		int n = a.length;
		int t[] = new int[n];
		ms(a, t, 0, n-1);
		
		for (int i=0;i<n;i++) System.out.println(a[i]);
	}

	private static void ms(int[] a, int[] t, int l, int r) {
		if (l < r) {
			int mid = l + (r-l)/2;
			ms(a, t, l, mid);
			ms(a, t, mid+1, r);
			merge(a, t, l, mid, r);
		}
	}

	private static void merge(int[] a, int[] t, int beg, int mid, int end) {
		int lb = beg, rb = mid+1, tb = beg;
		while(lb<=mid && rb<=end) {
			if (a[lb]<=a[rb]) t[tb++] = a[lb++];
			else t[tb++] = a[rb++];
		}
		while (lb<=mid) t[tb++] = a[lb++];
		while (rb<=end) t[tb++] = a[rb++];
		for (int i=beg;i<=end;i++) a[i] = t[i];
	}
}