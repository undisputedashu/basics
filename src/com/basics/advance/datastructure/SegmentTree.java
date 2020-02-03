package com.basics.advance.datastructure;

import java.util.Scanner;

public class SegmentTree {

	private static int st[];
	public static void main(String args[]) {
		driver();
//		below for testing at gfg
//		forTests();
	}

	private static void driver() {
		int a[] = {1, 3, 2, 7}; 
        int n = a.length;
        construct(a, n);
        
        int min = findMinimum(n, 1, 2);
        System.out.println(min);
	}

	private static int findMinimum(int n, int s, int e) {
		if (s<0 || e >= n || s>e) {
			return -1;
		}
		return find(0,n-1,s,e,0);
	}

	private static int find(int as, int ae, int qs, int qe, int ind) {
		if (qs<=as && qe>=ae) 
			return st[ind];
		if (ae<qs || as>qe)
			return Integer.MAX_VALUE;
		int mid = as + (ae - as)/2;
		return Math.min(find(as,mid,qs,qe,2*ind+1), find(mid+1,ae,qs,qe,2*ind+2));
	}

	private static void construct(int[] a, int n) {
		int x = (int)Math.ceil(Math.log(n)/Math.log(2));
		int size = (int)Math.pow(2, x+1);
		st = new int[size];
		construct(a, 0, n-1, 0);
	}

	private static int construct(int[] a, int start, int end, int ind) {
		if (start == end) {
			st[ind] = a[start];
			return a[start];
		}
		
		int mid = start + (end-start)/2;
		st[ind] = Math.min(construct(a,start,mid,ind*2+1), construct(a,mid+1,end,ind*2+2));
		return st[ind];
	}

	@SuppressWarnings("unused")
	private static void forTests() {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int a[] = new int[n];
			for (int i=0;i<n;i++) a[i] = in.nextInt();
			
			construct(a,n);
			
			int q = in.nextInt();
			for (int i=0;i<q;i++) {
				int l = in.nextInt(), r = in.nextInt();
				int min = findMinimum(n, l, r);
				System.out.println(min);
			}
		}
		in.close();
	}

}