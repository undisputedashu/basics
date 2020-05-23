package com.basics.competitive.programming.graph;

import java.util.Arrays;
import java.util.Scanner;
/**
 * https://www.geeksforgeeks.org/maximum-bipartite-matching/
 */
public class BipartiteMatching {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-->0) {
			int m = in.nextInt(), n = in.nextInt();
			int g[][] = new int[m][n];
			for (int i=0;i<m;i++)
				for (int j=0;j<n;j++)
					g[i][j] = in.nextInt();
			
			int ans = maxMatching(g, m, n);
			System.out.println(ans);
		}
		in.close();
	}

	private static int maxMatching(int[][] g, int m, int n) {
		int match[] = new int[n];
		Arrays.fill(match, -1);
		int res = 0;
		for (int u=0;u<m;u++) {
			boolean seen[] = new boolean[n];
			Arrays.fill(seen, false);
			if (has(g, u, match, seen)) res++;
		}
		
		return res;
	}

	private static boolean has(int[][] g, int u, int[] match, boolean[] seen) {
		int n = g[0].length;
		for (int v=0;v<n;v++) {
			if (g[u][v] == 1 && !seen[v]) {
				seen[v] = true;
				if (match[v]<0 || has(g,match[v],match,seen)) {
					match[v] = u;
					return true;
				}
			}
		}
		return false;
	}
	
}