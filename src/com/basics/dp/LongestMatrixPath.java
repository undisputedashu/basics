package com.basics.dp;

/**
 * https://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/
 * @author asasjha
 *
 */
public class LongestMatrixPath {
	
	private static Coordinate co[] = new Coordinate[4];
	static {
		co[0] = new Coordinate(1, 0);
		co[1] = new Coordinate(-1, 0);
		co[2] = new Coordinate(0, 1);
		co[3] = new Coordinate(0, -1);
	}

	public static void main(String args[]) {
		int a[][] = {
				{1, 2, 9},
				{5, 3, 8},
				{5, 6, 7}
				};
		int m = a.length, n = a[0].length;
		int max = 0;
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				int k = lp2(a,i,j);
				if (k > max) max = k;
			}
		}
		System.out.println(max);
		//Memoization
		max = 0;
		int t[][] = new int[m][n];
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				int k = t[i][j];
				if (k == 0) {
					k = lpd(a,i,j, t);
				}
				if (k > max) max = k;
			}
		}
		System.out.println(max);
	}

	private static int lp2(int a[][], int i, int j) {
		int m = a.length, n = a[0].length;
		if (i<0 || i >= m) return 0;
		if (j<0 || j>=n) return 0;
		
		int max = 1;
		for (int k=0;k<co.length;k++) {
			int x = i + co[k].x , y = j + co[k].y;
			if (x>=0 && x<m && y>=0 && y<n && a[x][y] == a[i][j]+1) {
				int cnt = 1 + lp2(a,x,y);
				if (cnt > max) max = cnt;
			}
		}
		return max;
	}
	
	private static class Coordinate {
		private int x,y;
		Coordinate(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int lpd(int[][] a, int i, int j, int[][] t) {
		int m = a.length, n = a[0].length;
		if (i < 0 || j < 0) return 0;
		if (i >= m || j >= n) return 0;
		
		int p = 1, q = 1, r = 1, s = 1;
		if (i>0 && a[i-1][j]-1 == a[i][j]) p+= lpd(a,i-1,j,t);
		if (i+1<m && a[i+1][j]-1 == a[i][j]) q+= lpd(a,i+1,j,t);
		if (j>0 && a[i][j-1]-1 == a[i][j]) r+= lpd(a,i,j-1,t);
		if (j+1<n && a[i][j+1]-1 == a[i][j]) s+= lpd(a,i,j+1,t);
		t[i][j] = max(p,q,r,s);
		return t[i][j];
	}

	private static int lp(int[][] a, int i, int j) {
		int m = a.length, n = a[0].length;
		if (i < 0 || j < 0) return 0;
		if (i >= m || j >= n) return 0;
		
		int p = 1, q = 1, r = 1, s = 1;
		if (i>0 && a[i-1][j]-1 == a[i][j]) p+= lp(a,i-1,j);
		if (i+1<m && a[i+1][j]-1 == a[i][j]) q+= lp(a,i+1,j);
		if (j>0 && a[i][j-1]-1 == a[i][j]) r+= lp(a,i,j-1);
		if (j+1<n && a[i][j+1]-1 == a[i][j]) s+= lp(a,i,j+1);
		return max(p,q,r,s);
	}

	private static int max(int p, int q, int r, int s) {
		if (p >= max(q,r,s)) return p;
		if (q >= max(p,r,s)) return q;
		if (r >= max(p,q,s)) return r;
		return s;
	}

	private static int max(int q, int r, int s) {
		return q>r && q>s ? q : r > s ? r : s;
	}
	
}