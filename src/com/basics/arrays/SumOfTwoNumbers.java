package com.basics.arrays;

/**
 * https://www.geeksforgeeks.org/write-a-c-program-that-given-a-set-a-of-n-numbers-and-another-number-x-determines-whether-or-not-there-exist-two-elements-in-s-whose-sum-is-exactly-x/
 * @author ashu
 *
 */
public class SumOfTwoNumbers {

	public static void main(String args[]) {
		int a[] = {4,1,5,3,2};
		quick(a, 0, a.length-1);
		int k = 9;
		printNumbers(a, k);
	}

	private static void printNumbers(int[] a, int k) {
		int l = 0, r = a.length-1;
		
		while (l<r) {
			if (a[l] + a[r] == k) {
				System.out.println(a[l] + "  " + a[r]);
				return;
			}
			if (a[l] + a[r] > k) r--;
			else if (a[l] + a[r] < k) l++;
		}
		
		System.out.println("No such pair found whose sum is " + k);
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