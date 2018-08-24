package com.basics.dp;

public class CoinChhange {

	public static void main(String args[]) {
		int c[] = {1,3,4};
		int n = c.length;
		int s = 5;
		int k = cc(c, n, s);
		System.out.println(k);
	}

	private static int cc(int[] c, int n, int s) {
		if (s == 0) return 1;
		if (s < 0 || n == 0) return 0;
		
		return cc(c, n, s-c[n-1]) + cc(c, n-1, s);
	}
	 
}