package com.basics.dp;

public class MinimumEdit {

	public static void main(String args[]) {
		String s1 = "geek", s2 = "gesek";
		int k = me(s1, s1.length(), s2, s2.length());
		System.out.println(k);
	}

	private static int me(String s1, int m, String s2, int n) {
		if (m == 0) return n;
		if (n == 0) return m;
		
		if (s1.charAt(m-1) == s2.charAt(n-1)) return me(s1,m-1,s2,n-1);
		return 1 + min(me(s1, m, s2 , n-1), me(s1, m-1, s2, n), me(s1, m-1, s2, n-1));
	}

	private static int min(int a, int b, int c) {
		return a < b && a < c ? a : b < c ? b : c;
	}
	
}