package com.basics.arrays;

public class IntersectionUnionSortedArrays {

	public static void main(String args[]) {
		int a[] = {1, 2, 4, 5, 6};
		int b[] = {2, 3, 5, 7};
		System.out.println("Union::");
		union(a, b);
		System.out.println("Intersection::");
		intersection(a, b);
	}

	private static void intersection(int[] a, int[] b) {
		int m = a.length, n = b.length;
		int l = 0, r = 0;
		
		while (l<m && r<n) {
			if (a[l] == b[r]) {
				System.out.println(a[l]);
				l++;
				r++;
			} else if (a[l] < b[r]) {
				l++;
			} else {
				r++;
			}
		}
	}

	private static void union(int[] a, int[] b) {
		int m = a.length, n = b.length;
		int l = 0, r = 0;
		while (l<m && r<n) {
			if (a[l] == b[r]) {
				System.out.println(a[l]);
				l++;
				r++;
			} else if (a[l] < b[r]) {
				System.out.println(a[l++]);
			} else {
				System.out.println(b[r++]);
			}
		}
		
		while (l<m) System.out.println(a[l++]);
		while (r<n) System.out.println(b[r++]);
	}
	
}