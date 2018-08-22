package com.basics.dp;

public class LongestIncreasingSubsequence {

	//if we don't use max, simply print value 'k' returned by lisRec
	//it will be wrong. analyse recursion why this is happening
	private static int max = Integer.MIN_VALUE;
	
	public static void main(String args[]) {
		int a[] = {1,3,5,4,2};
		int n = a.length;
		@SuppressWarnings("unused")
		int k = lisRec(a, n);
		System.out.println(max);
	}

	private static int lisRec(int[] a, int n) {
		if (n == 1) return 1;
		
		int res = 1;
		for (int i=1;i<n;i++) {
			int curr = lisRec(a, i);
			if (a[n-1] > a[i-1] && curr+1 > res)
				res = curr + 1;
		}
		
		if (res > max) max = res;
		return res;
	}
	
	@SuppressWarnings("unused")
	private static void lisdp(int[] a, int n) {
		//coming soon
	}
	
}