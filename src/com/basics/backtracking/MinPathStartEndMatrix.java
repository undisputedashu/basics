package com.basics.backtracking;

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
		System.out.println("Backtracking 1 :");
		int cnt = cnt(mat,0,0);
		System.out.println(cnt);
		System.out.println("Backtracking 2 :");
		co[0] = new Coordinate(1,0);
		co[1] = new Coordinate(-1, 0);
		co[2] = new Coordinate(0, 1);
		co[3] = new Coordinate(0, -1);
		cnt = find(mat,0,0);
		System.out.println(cnt);
		System.out.println("Print path::");
		Deque<String> qu = new LinkedList<>();
		cnt(mat,0,0,qu);
		System.out.println(que);
	}
	
	private static int MAX = Integer.MAX_VALUE;
	private static Coordinate co[] = new Coordinate[4];
	
	private static int find(String mat[][], int i, int j) {
		int m = mat.length, n = mat[0].length;
		if (i == m-1 && j == n-1) return 0;
		if (i<0 || i >= m) return MAX;
		if (j<0 || j >= n) return MAX;
		if (mat[i][j].equals("x")) return MAX;
		if (mat[i][j].equals("y")) return MAX;
		
		mat[i][j] = "y";
		int min = MAX;
		for (int k=0;k<co.length;k++) {
			int sum = find(mat, i+co[k].x, j+co[k].y);
			if (sum != MAX && 1 + sum < min) {
				min = 1 + sum;
			}
		}
		
		mat[i][j] = "";
		return min;
	}
	
	private static class Coordinate {
		int x,y;
		Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
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