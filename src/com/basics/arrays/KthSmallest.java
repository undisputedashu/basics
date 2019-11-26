package com.basics.arrays;

public class KthSmallest {

	private static int min = Integer.MIN_VALUE;
	private static int max = Integer.MAX_VALUE;
	
	public static void main(String args[]) {
		int a[] = {2, 3, 6, 7, 9}; 
	    int b[] = {1, 4, 8, 10}; 
	    int k = 9; 
	    int m = a.length, n = b.length;
	    k = kth(a,0,m,b,0,n,k);
	    System.out.println(k);
	}
	
	private static int kth(int a[], int alow, int alen, int b[], int blow, int blen, int k) {
		int i = (int)((double)((k-1)*alen)/(alen+blen));
		int j = k -i -1;
		
		int ai_1 = i == 0 ? min : a[alow+i-1];
		int ai = i == alen ? max : a[alow+i];
		int bj_1 = j == 0 ? min : b[blow+j-1];
		int bj = j == blen ? max : b[blow+j];
		
		if (ai_1 < bj && bj < ai) return bj;
		if (bj_1 < ai && ai < bj) return ai;
		
		if (ai < bj_1) {
			return kth(a, alow+i+1, alen-i-1, b, blow, j, k-i-1);
		} else {
			return kth(a, alow, i, b, blow+j+1, blen-j-1, k-j-1);
		}
	}
	
}