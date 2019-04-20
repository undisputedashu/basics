package com.basics.dp;

import java.util.Arrays;

public class CoinChhange {

	public static void main(String args[]) {
		int c[] = {1,3,4};
		int n = c.length;
		int s = 4;
		int k = cc(c, n, s);
		System.out.println("Recursive result = " + k);
		k = ccDp(c, n, s);
		System.out.println("Dp result = " + k);
	}

	private static int cc(int[] c, int n, int s) {
		if (s == 0) return 1;
		if (s < 0 || n == 0) return 0;
		
		return cc(c, n, s-c[n-1]) + cc(c, n-1, s);
	}

	private static int ccDp(int[] c, int n, int s) {
		int t[][] = new int[s+1][n];
		Arrays.fill(t[0], 1);
		
		for (int i=1;i<=s;i++) {
			for (int j=0;j<n;j++) {
				int x = i - c[j] >= 0 ? t[i-c[j]][j] : 0;
				int y = j>0 ? t[i][j-1] : 0;
				t[i][j] = x + y;
			}
		}
		return t[s][n-1];
	}

}