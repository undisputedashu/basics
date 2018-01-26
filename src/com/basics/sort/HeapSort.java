package com.basics.sort;

public class HeapSort {

	public static void main(String args[]) {
		int a[] = {4,1,5,3,2};
		int n = a.length;
		heapSort(a, n);
		for (int i=0;i<n;i++) System.out.println(a[i]);
	}

	private static void heapSort(int[] a, int n) {
		heapify(a, n);
		int end = n;
		for (int i=0;i<n-1;i++) {
			end--;
			swap(a, 0, end);
			maxHeap(a, 0, end);
		}
	}
	
	private static void heapify(int a[], int n) {
		for (int i=n/2;i>=0;i--) {
			maxHeap(a, i, n);
		}
	}

	private static void maxHeap(int[] a, int i, int n) {
		int big = i, l = 2*i + 1, r = 2*i + 2;
		if (l<n && a[big]<a[l]) big = l;
		if (r<n && a[big]<a[r]) big = r;
		if (big != i) {
			swap(a, big, i);
			maxHeap(a, big, n);
		}
	}
	
	private static void swap(int[] a, int j, int i) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}