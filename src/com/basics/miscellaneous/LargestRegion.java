package com.basics.miscellaneous;

/**
 * https://www.geeksforgeeks.org/find-length-largest-region-boolean-matrix/
 * @author ashu
 */
public class LargestRegion {

	public static void main(String args[]) {
		int a[][] = { 
				{0, 0, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1} 
                };
		int m = a.length, n = a[0].length;
		
		int max = Integer.MIN_VALUE;
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				if (a[i][j] == 1) {
					int cnt = get(a, i, j);
					if (cnt > max) max = cnt;
				}
			}
		}
		System.out.println(max);
	}

	private static int get(int[][] a, int i, int j) {
		int m = a.length, n = a[0].length;
		if (i<0 || i >= m || j<0 || j >= n) return 0;
		if (a[i][j] != 1) return 0;
		
		a[i][j] = 2;
		
		return 1 + get(a,i+1,j) + get(a,i-1,j) + get(a,i,j-1) + get(a,i,j+1)
				 + get(a,i-1,j-1) + get(a,i+1,j+1) + get(a,i+1,j-1) + get(a,i-1,j+1);
	}
	
}