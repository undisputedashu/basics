package com.basics.string;

public class AllPermutationForString {

	public static void main(String args[]) {
		String s = "abc";
		print(s, "");
	}
	
	private static void print(String s1, String s2) {
		if (s1.length() == 0) {
			System.out.println(s2);
		} else {
			for (int i=0;i<s1.length();i++) {
				print(s1.substring(0, i) + s1.substring(i+1, s1.length()), s2 + s1.charAt(i));
			}
		}
	}
	
}