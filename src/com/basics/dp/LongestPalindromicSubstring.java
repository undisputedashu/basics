package com.basics.dp;

public class LongestPalindromicSubstring {

	public static void main(String args[]) {
		String str = "caabaad";
		int n = str.length();
		String lpss = get(str, n);
		System.out.println(lpss);
		lpss = getDp(str,n);
		System.out.println(lpss);
	}

	private static String getDp(String s, int n) {
		if (s == null || n < 2) return s;
		int t[][] = new int[n][n];
		
		for (int i=0;i<n;i++) t[i][i] = 1;
		
		int max = Integer.MIN_VALUE;
		String res = null;
		int len = 2;
		while (len <= n) {
			int i = 0, j = len - i - 1;
			while (i<n && j <n) {
				if (s.charAt(i) == s.charAt(j)) {
					if (len == 2) {
						t[i][j] = 1;
					} else {
						if (t[i+1][j-1] == 1) {
							t[i][j] = 1;
							if (j-i+1 > max) {
								max = j-i+1;
								res = s.substring(i,j+1);
							}
						}
					}
				}
				i++;
				j++;
			}
			len++;
		}
		return res;
	}

	private static String get(String s, int n) {
		if (s == null || n < 2) return s;
		int max = Integer.MIN_VALUE;
		String res = null;
		for (int i=1;i<=n;i++) {
			for (int j=0;j<i;j++) {
				String sub = s.substring(j, i);
				if (isPalindrome(sub) && sub.length() > max) {
					max = sub.length();
					res = sub;
				}
			}
		}
		return res;
	}

	private static boolean isPalindrome(String s) {
		int l = 0, r = s.length()-1;
		while (l < r) {
			if (s.charAt(l++) != s.charAt(r--)) 
				return false;
		}
		return true;
	}
	
}