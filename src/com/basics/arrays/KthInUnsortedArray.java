package com.basics.arrays;

/**
 * https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
 * @author ashu
 */
public class KthInUnsortedArray {

	public static void main(String args[]) {
		int a[] = {5,1,4,3,2};
		int n = a.length;
		int k = 4;
		if (k<0 || k>n) {
			System.out.println("Invalid value of k");
			return;
		}
		findKth(a, n, k);
	}

	private static void findKth(int[] a, int n, int k) {
		int l = 0, r = n-1;
		int ind = -1;
		//As arrays index start from 0 so comparing with k-1
		while (ind != k-1) {
			ind = partition(a, l, r);
			if (ind == k-1) break;
			if (ind > k-1) {
				r = ind-1;
			} else if (ind < k-1){
				l = ind + 1;
			}
		}
		System.out.println(a[k-1]);
	}

	private static int partition(int[] a, int p, int q) {
		int x = a[p], i = p;
		for (int j=i+1;j<=q;j++) {
			if (a[j]<=x) {
				i++;
				swap(a, i, j);
			}
		}
		if (i != p) swap(a, i, p);
		return i;
	}

	private static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
}