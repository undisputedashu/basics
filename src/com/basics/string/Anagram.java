package com.basics.string;

/**
 * https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
 * @author ashu
 *
 */
public class Anagram {

	public static void main(String args[]) {
		String s1 = "abc";
		String s2 = "bac";
		System.out.println(isAnagram(s1, s2));
		s1 = "abc";
		s2 = "bad";
		System.out.println(isAnagram(s1, s2));
	}

	private static boolean isAnagram(String s1, String s2) {
		if (s1.length() != s2.length()) return false;
		int n = s1.length();
		int t[] = new int[255];
		for (int i=0;i<n;i++) {
			t[s1.charAt(i)]++;
			t[s2.charAt(i)]--;
		}
		for (int i=0;i<t.length;i++) {
			if (t[i] != 0) return false;
		}
		return true;
	}
	
}