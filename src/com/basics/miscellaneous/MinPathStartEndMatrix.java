package com.basics.miscellaneous;

import java.util.Deque;
import java.util.LinkedList;

public class MinPathStartEndMatrix {

	private static int max;
	public static void main(String args[]) {
		String mat[][] = {
				{"","x","", "",""},
				{"","x","", "x",""},
				{"","x","", "x",""},
				{"","","", "x",""},
		};
		int m = mat.length, n = mat[0].length;
		max = m*n + 10;
		int cnt = cnt(mat,0,0);
		System.out.println(cnt);
		Deque<String> qu = new LinkedList<>();
		cnt(mat,0,0,qu);
		System.out.println(que);
	}
	
	private static Deque<String> que = new LinkedList<>();
	//Now we can apply dp 
	private static int cnt(String[][] mat, int i, int j, Deque<String> qu) {
		int m = mat.length, n = mat[0].length;
		if (i == m-1 && j == n-1) {
			if (que.isEmpty() || que.size() > qu.size()) {
				que.clear();
				for (String s : qu) {
					que.add(s);
				}
			}
			return 0;
		} else if (mat[i][j].equals("x")) {
			return max;
		}
		mat[i][j] = "y";
		qu.add(i + "" + j);
		
		int p,q,r,s;
		p = q = r = s = max;
		if (i+1 < m && !mat[i+1][j].equals("y")) p = 1 + cnt(mat,i+1,j,qu);
		if (i-1>=0 && !mat[i-1][j].equals("y")) q = 1 + cnt(mat,i-1,j,qu);
		
		if (j+1<n && !mat[i][j+1].equals("y")) r = 1 + cnt(mat,i,j+1,qu);
		if (j-1>=0 && !mat[i][j-1].equals("y")) s = 1 + cnt(mat,i,j-1,qu);
		
		int k = min(p,q,r,s);
		mat[i][j] = "";
		qu.removeLast();
		return k;
	}

	//It uses backtracking.
	private static int cnt(String[][] mat, int i, int j) {
		int m = mat.length, n = mat[0].length;
		if (i == m-1 && j == n-1) {
			return 0;
		} else if (mat[i][j].equals("x")) {
			return max;
		}
		mat[i][j] = "y";
		
		int p,q,r,s;
		p = q = r = s = max;
		if (i+1 < m && !mat[i+1][j].equals("y")) p = 1 + cnt(mat,i+1,j);
		if (i-1>=0 && !mat[i-1][j].equals("y")) q = 1 + cnt(mat,i-1,j);
		
		if (j+1<n && !mat[i][j+1].equals("y")) r = 1 + cnt(mat,i,j+1);
		if (j-1>=0 && !mat[i][j-1].equals("y")) s = 1 + cnt(mat,i,j-1);
		
		int k = min(p,q,r,s);
		mat[i][j] = "";
		return k;
	}

	private static int min(int p, int q, int r, int s) {
		if (p <= min(q,r,s)) return p;
		if (q <= min(p,r,s)) return q;
		if (r <= min(p,q,s)) return r;
		return s;
	}

	private static int min(int q, int r, int s) {
		return q<r && q < s ? q : r < s ? r : s;
	}

}