package com.basics.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/word-break-problem-dp-32/
 * @author ashu
 *
 */
public class WordBreak {

    public static void main(String args[]) {
        String s[] = {"i","like","am","sung"};
        int n = s.length;
        Set<String> words = new HashSet<String>();
        for (int i=0;i<n;i++) words.add(s[i]);
        String in = "ami";
        boolean exist = check(in, 0, in.length(), words);
        System.out.println(exist);
        System.out.println("Another soln need to check if there is bug::");
        exist = wb(in, words);
        System.out.println(exist);
    }

	private static boolean wb(String s, Set<String> words) {
		if (words.contains(s))
			return true;
		int n = s.length();
		for (int i=1;i<n;i++) {
			if (wb(s.substring(0,i), words) && wb(s.substring(i,n), words))
				return true;
		}
		return false;
	}

    private static boolean check(String in, int ind, int n, Set<String> words) {
        if (words.contains(in.substring(ind, n))) return true;
        
        boolean res = false;
        for (int i=ind+1;i<n;i++) {
            boolean curr = check(in, ind, i, words) && check(in, i, n, words);
            if (curr) res = curr;
        }
        return res;
    }

}