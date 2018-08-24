package com.basics.dp;

public class LongestCommonSubsequence {

	public static void main(String args[]) {
		String s1 = "abced", s2 = "aaead";
		int k = lcs(s1, s1.length(), s2, s2.length());
		System.out.println(k);
	}

	private static int lcs(String s1, int m, String s2, int n) {
		if (m == 0 || n == 0) return 0;
		if (s1.charAt(m-1) == s2.charAt(n-1)) return 1 + lcs(s1, m-1, s2, n-1);
		return Math.max(lcs(s1, m-1, s2, n), lcs(s1, m, s2, n-1));
	}
}