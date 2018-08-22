package com.basics.string;

/**
 * Given two string s and p
 * Find indexes of occurrence of p or its anagram in s
 * @author ashu
 *
 */
public class FindOccurenceOfString {

	public static void main(String args[]) {
		String s = "AABAAAAB";//0,1,2,4 not at 3 as from 3rd index of array its AAAA only B is not present
		String p = "ABAA";
		printOccurrence(s, p);
	}

	private static void printOccurrence(String s, String p) {
		int n = s.length(), m = p.length();
		if (n < m) return;
		
		int ts[] = new int[255];
		int tp[] = new int[255];
		
		for (int i=0;i<m;i++) {
			tp[p.charAt(i)]++;
			ts[s.charAt(i)]++;
		}
		
		for (int i=m;i<n;i++) {
			if (check(tp, ts)) {
				System.out.println(i-m);
			}
			ts[s.charAt(i)]++;
			ts[s.charAt(i-m)]--;
		}
		if (check(tp, ts)) {
			System.out.println(n-m);
		}
	}

	private static boolean check(int[] tp, int[] ts) {
		for (int i=0;i<tp.length;i++) {
			if (tp[i] != ts[i]) {
				return false;
			}
		}
		return true;
	}
	
}