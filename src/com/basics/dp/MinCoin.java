package com.basics.dp;

import java.util.Arrays;

public class MinCoin {

	public static void main(String[] args) {
		int c[] = {1,2,3,4};
		int n = c.length;
		int s = 4;
		int k = mc(c, s);
		System.out.println(k);
		k = mcdp(c,n,s);
		System.out.println(k);
	}

	private static int mc(int[] c, int s) {
		if (s == 0) return 0;
		
		int n = c.length;
		int res = Integer.MAX_VALUE;
		for (int i=0;i<n;i++) {
			if (s-c[i]>=0) {
				int curr = mc(c, s-c[i]);
				if (curr != Integer.MAX_VALUE && curr + 1 < res)
					res = curr + 1;
			}
		}
		
		return res;
	}
	
	private static int mcdp(int[] c, int n, int s) {
		int t[] = new int[s+1];
		Arrays.fill(t, Integer.MAX_VALUE);
		for (int i=0;i<n;i++) {
			if (c[i]<=s) t[c[i]] = 1;
		}
		
		for (int i=1;i<=s;i++) {
			for (int j=0;j<n;j++) {
				if (c[j]<=i) {
					if (t[i-c[j]] != Integer.MAX_VALUE && t[i-c[j]] + 1 < t[i])
						t[i] = t[i-c[j]] + 1;
				}
			}
		}
		return t[s];
	}

}