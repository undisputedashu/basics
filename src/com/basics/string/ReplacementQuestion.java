package com.basics.string;

/**
 * Input:
 * 
 * str = “Hello Hello World!”
 * 
 * replacement = [
 * 
 * {index: 0, before: “Hello”, after: “Good”},
 * 
 * {index: 12, before: “World”, after: “Bye”}
 * 
 * ]
 * 
 * 
 * Output: Good Hello Bye!
 * 
 * @author asasjha
 *
 */
public class ReplacementQuestion {

	public static void main(String args[]) {
		String str = "Hello Hello World!";
		Replacement[] replacement = { new Replacement(6, "Hello", "Good"), new Replacement(12, "World", "Bye") };
		String s2 = replace(str, replacement);
		System.out.println(s2);
	}

	public static String replace(String str, Replacement[] replacement) {
		if (replacement == null || str == null) {
			throw new RuntimeException("Invalid Input for replacement");
		}

		if (replacement.length == 0) {
			return str;
		}

		StringBuilder sb = new StringBuilder();
		int ctr = 0, n = str.length();

		for (int i = 0; i < replacement.length; i++) {
			Replacement rep = replacement[i];
			int ind = rep.index;
			if (ctr > ind) {
				throw new RuntimeException("Invalid Input");
			}
			sb.append(str.substring(ctr, ind));
			String before = rep.before;
			sb.append(rep.after);
			ctr = ind + before.length();
		}
		sb.append(str.substring(ctr, n));
		
		return sb.toString();
	}

	private static class Replacement {
		public Replacement(int i, String s1, String s2) {
			index = i;
			before = s1;
			after = s2;
		}

		int index;
		String before;
		String after;
	}
	
}