package com.basics.sort;

public class BubbleSort {

	public static void main(String args[]) {
		int a[] = {4,1,5,2,3};
		bubbleSort(a);
		for (int i=0;i<a.length;i++) {
			System.out.println(a[i]);
		}
	}

	private static void bubbleSort(int[] a) {
		int n = a.length;
		for (int i=0;i<n-1;i++) {
			for (int j=0;j<n-i-1;j++) {
				if (a[j] > a[j+1]) {
					swap(a, j, j+1);
				}
			}
		}
	}

	private static void swap(int[] a, int j, int i) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
}