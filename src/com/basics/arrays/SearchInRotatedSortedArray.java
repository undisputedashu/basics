package com.basics.arrays;

/**
 * https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
 * @author ashu
 *
 */
public class SearchInRotatedSortedArray {

	public static void main(String args[]) {
		int a[] = {5, 6, 7, 8, 9, 10,1,2,3};
		int n = a.length;
		int p = getPivot(a, 0, n-1);
		System.out.println(p);
		int k = 3;
		int ind = -1;
		if (p == a.length-1) {
			ind = bs(a, 0, n-1, k);
		} else {
			ind = bs(a, 0, p, k);
			ind = ind != -1 ? ind : bs(a,p+1,n-1,k);
		}
		System.out.println(ind);
		
		ind = find(a, 0, n-1, k);
		System.out.println(ind);
	}
	
	private static int find(int[] a, int l, int r, int k) {
		if (l > r) return -1;
		int mid = l + (r-l)/2;
		if (a[mid] == k) return mid;
		if (a[0] < a[mid]) {
			if (k >= a[0] && k <= a[mid])
				return find(a, l, mid, k);
			return find(a, mid+1, r, k);
		}
		if (k >= a[mid] && k <= a[r]) return find(a, mid+1, r, k);
		return find(a, l, mid-1, k);
	}

	private static int bs(int[] a, int l, int r, int k) {
		if (l > r) return -1;
		int mid = l + (r-l)/2;
		if (a[mid] == k) return mid;
		if (a[mid]<k) return bs(a, mid+1, r, k);
		return bs(a, l, mid-1, k);
	}

	private static int getPivot(int a[], int l, int r) {
		if (l > r) return -1;
		if (l == r) return l;
		int mid = l + (r-l)/2;
		if (mid+1 < a.length && a[mid] > a[mid+1]) return mid;
		else if (mid-1 > 0 && a[mid-1] > a[mid]) return mid-1;
		else if (a[mid] > a[l]) return getPivot(a, mid+1, r);
		else return getPivot(a, l, mid-1);
	}
	
}
