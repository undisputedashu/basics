package com.basics.string;

public class SwapVowels {

	public static void main(String args[]) {
		String s = "dacdefogik";
		int l = 0, r = s.length()-1;
		char c[] = s.toCharArray();
		while (l<r) {
			if (isVowel(c[l]) && isVowel(c[r])) {
				swap(c,l,r);
				l++;
				r--;
			} else if (isVowel(c[l])) {
				r--;
			} else {
				l++;
			}
		}
		System.out.println(c);
	}

	private static void swap(char[] c, int l, int r) {
		char t = c[l];
		c[l] = c[r];
		c[r] = t;
	}

	private static boolean isVowel(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
			return true;
		return false;
	}
}