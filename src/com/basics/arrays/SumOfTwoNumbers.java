package com.basics.arrays;

public class SumOfTwoNumbers {

	public static void main(String args[]) {
		int a[] = {4,1,5,3,2};
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
	
}