package com.basics.revise;

//TODO kth smallest remains and inorder predecessor
//add new package for advance data structure and add trie to it
public class Revise {
	
	public static void main(String args[]) {
		int a[][] = {
                {1,2,3},
                {4,5,6},
                {7,8,9},
                {-1,0,-1}
            };
		int m = a.length, n = a[0].length;
		int k = 2;
		int sum = 0;
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				if (a[i][j] != -1) {
					sum = sum + mk(a,i,j,k);
				}
			}
		}
		System.out.println(sum);
	}

	private static int mk(int[][] a, int i, int j, int k) {
		int m = a.length, n = a[0].length;
		if (i < 0 || i >= m || j < 0 || j >= n) return 0;
		if (a[i][j] == -1) return 0;
		if (k == 1) return 1;
		
		return mk(a, i+1, j, k-1) + 
			   mk(a, i-1, j, k-1) + 
			   mk(a, i, j+1, k-1) + 
			   mk(a, i, j-1, k-1) + 
			   mk(a,i,j,k-1);
	}
	
}