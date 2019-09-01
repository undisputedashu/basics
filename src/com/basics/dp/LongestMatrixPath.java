package com.basics.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/override-equalsobject-hashcode-method/
 * @author asasjha
 *
 */
public class LongestMatrixPath {

	public static void main(String args[]) {
		int a[][] = {
				{1, 2, 9},
				{5, 3, 8},
				{4, 6, 7}
				};
		int max = Integer.MIN_VALUE;
		int m = a.length, n = a[0].length;
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				Set<Coord> set = new HashSet<>();
				int k = gpv(a, i, j, set);
				if (k > max) max = k;
			}
		}
		System.out.println(max);
	}

	private static int gpv(int[][] a, int i, int j, Set<Coord> set) {
		if (set.contains(new Coord(i,j))) return 0;
		if (i < 0 || i >= a.length) return 0;
		if (j < 0 || j >= a[0].length) return 0;
		set.add(new Coord(i,j));
		
		int p=1,q=1,r=1,s=1;
		if (i+1 < a.length && a[i+1][j]-1 == a[i][j])  p = 1 + gpv(a,i+1,j,set);
		if (i-1 >= 0 && a[i-1][j]-1 == a[i][j]) q = 1 + gpv(a,i-1,j,set);
		
		if (j+1 < a[0].length && a[i][j+1]-1 == a[i][j]) r = 1 + gpv(a,i,j+1,set);
		if (j-1 >= 0 && a[i][j-1]-1 == a[i][j]) s = 1 + gpv(a,i,j-1,set);
		
		return max(p, q, r, s);
	}
	
	private static class Coord {
		int x, y;
		public Coord(int i, int j) {
			x = i;
			y = j;
		}
		@Override
		public boolean equals(Object o) {
			Coord co = (Coord) o;
			if (this.x == co.x && this.y == co.y) return true;
			return false;
		}
		@Override
	    public int hashCode() {
			return this.x * this.y;
		}
	}
	
	private static int max(int a, int b,int c, int d) {
		if (a >= max(b,c,d)) return a;
		if (b >= max(a,c,d)) return b;
		if (c >= max(a,b,d)) return c;
		return d;
	}
	
	private static int max(int a,int b, int c) {
		return a > b && a > c ? a : b>c ? b : c;
	}
	
}