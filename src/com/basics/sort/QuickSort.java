package com.basics.sort;

public class QuickSort {

	/**
	 * Many of you who are new to algorithms will think it's tough i cannot understand it.
	 * I challenge you to take time day week or even month, try yourself, discuss with friend and debug it.
	 * If you understand this quick sort later you will easily understand many questions.
	 */
	public static void main(String args[]) {
		int a[] = {4,1,5,2,3};
		int n = a.length;
		quick(a, 0, n-1);
		for (int i=0;i<n;i++) System.out.println(a[i]);
	}

	private static void quick(int[] a, int p, int q) {
		if (p >= q) return;
		int i = p, x = a[p];
		for (int j=i+1;j<=q;j++) {
			if (a[j]<=x) {
				i++;
				swap(a, i, j);
			}
		}
		if (i != p) swap(a, i, p);
		quick(a,p,i-1);
		quick(a,i+1,q);
	}
	
	private static void swap(int[] a, int j, int i) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}