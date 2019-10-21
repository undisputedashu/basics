package com.basics.tree;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/number-of-unique-bst-with-a-given-key-dynamic-programming/
 * @author ashu
 *
 */
public class AllPossibleBstCount {

	public static void main(String args[]) {
		int n = 4;
		int cnt = cnt(n);
		System.out.println(cnt);
		cnt = dp(n);
		System.out.println(cnt);
	}

	private static int dp(int n) {
		int t[] = new int[n+1];
		Arrays.fill(t, 0);
		t[0] = t[1] = 1;
		
		for (int i=2;i<=n;i++) {
			for (int j=1;j<=i;j++) {
				t[i] = t[i] + (t[j-1] * t[i-j]);
			}
		}
		return t[n];
	}

	//First think how problem is being solved
	//Don't add in every case, here it was multiplying 
	//number of left subtree to number of right subtree possible
	private static int cnt(int n) {
		if (n <= 1) return 1;
		if (n == 2) return 2;
		
		int sum = 0;
		for (int i=1;i<=n;i++) {
			sum = sum + (cnt(i-1) * cnt(n-i));
		}
		return sum;
	}
	
}