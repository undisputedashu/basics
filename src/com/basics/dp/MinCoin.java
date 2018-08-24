package com.basics.dp;

public class MinCoin {

	public static void main(String[] args) {
		int c[] = {1,2,3};
		int n = c.length;
		int s = 4;
		int k = mc(c, n, s);
		System.out.println(k);
	}

	private static int mc(int[] c, int n, int s) {
		if (s == 0) return 0;
		
		int res = Integer.MAX_VALUE;
		for (int i=0;i<n;i++) {
			if (s-c[i]>=0) {
				int curr = mc(c, n, s-c[i]);
				if (curr != Integer.MAX_VALUE && curr + 1 < res)
					res = curr + 1;
			}
		}
		
		return res;
	}

}