package com.basics.sort;

public class SelectionSort {

	public static void main(String args[]) {
		int a[] = {4,1,5,2,3};
		selectionSort(a);
		for (int i=0;i<a.length;i++) {
			System.out.println(a[i]);
		}
	}

	private static void selectionSort(int[] a) {
		int n = a.length;
		for (int i=0;i<n;i++) {
			int minIndex = i;
			
			for (int j=i+1;j<n;j++) {
				if (a[j] < a[minIndex]) 
					minIndex = j;
			}
			
			swap(a, i, minIndex);
		}
	}
	
	private static void swap(int[] a, int j, int i) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}