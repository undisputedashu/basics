package com.basics.revise;


//TODO kth smallest remains and inorder predecessor
//reverse vowels in string
//Add some backtracking problems
//add iterative dfs
public class Revise {
	
	private static int max;
	public static void main(String args[]) {
		String mat[][] = {
				{"","x","", "",""},
				{"","x","", "",""},
				{"","x","", "x",""},
				{"","","", "x",""},
		};
		int m = mat.length, n = mat[0].length;
		max = m*n + 10;
		boolean vis[][] = new boolean[m][n];
		int cnt = cnt(mat,0,0,vis);
		System.out.println(cnt);
	}
	
	private static int cnt(String[][] mat, int i, int j, boolean[][] vis) {
		int m = mat.length, n = mat[0].length;
		if (i == m-1 && j == n-1) {
			return 0;
		} else if (mat[i][j].equals("x")) {
			return max;
		}
		vis[i][j] = true;
		
		int p,q,r,s;
		p = q = r = s = max;
		if (i+1 < m && !vis[i+1][j]) p = 1 + cnt(mat,i+1,j,vis);
		if (i-1>=0 && !vis[i-1][j]) q = 1 + cnt(mat,i-1,j,vis);
		
		if (j+1<n && !vis[i][j+1]) r = 1 + cnt(mat,i,j+1,vis);
		if (j-1>=0 && !vis[i][j-1]) s = 1 + cnt(mat,i,j-1,vis);
		
		int k = min(p,q,r,s);
		vis[i][j] = false;
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