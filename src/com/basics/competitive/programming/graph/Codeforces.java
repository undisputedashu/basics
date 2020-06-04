package com.basics.competitive.programming.graph;

import java.io.IOException;
import java.util.*;



/**
 * https://codeforces.com/contest/1272/problem/E
 * Multi source bfs and adjacency list using 2-D array
 */
public class Codeforces {

	static int max = (int)1e7;
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		for (int i=0;i<n;i++) {
			a[i] = in.nextInt();
		}
		
		int d0[] = get(a, 0);
		int d1[] = get(a, 1);
		for (int i=0;i<n;i++) {
			if (a[i]%2 == 0) {
				if (d0[i]>=max) System.out.print(-1 + " ");
				else System.out.print(d0[i] + " ");
			} else {
				if (d1[i]>=max) System.out.print(-1 + " ");
				else System.out.print(d1[i] + " ");
			}
		}
		in.close();
	}
	
	private static int[] get(int[] a, int par) {
		int n = a.length;
		
		int from[] = new int[2*n], to[] = new int[2*n];
		int p = 0;
		for (int i=0;i<n;i++) {
			if (a[i]%2 == par) {
				if (i+a[i]<n) {
					from[p] = i + a[i];
					to[p] = i;
					p++;
				}
				if (i-a[i]>=0) {
					from[p] = i-a[i];
					to[p] = i;
					p++;
				}
			}
		}
		
		int g[][] = pack(from, to, n);

		int d[] = new int[n];
		Arrays.fill(d, max);
		Queue<Integer> qu = new ArrayDeque<Integer>();
		for (int i=0;i<n;i++) {
			if (a[i]%2 != par) {
				qu.add(i);
				d[i] = 0;
			}
		}
		
		while (!qu.isEmpty()) {
			int u = qu.poll();
			for (int v:g[u]) {
				if (d[v]>d[u]+1) {
					d[v] = d[u]+1;
					qu.add(v);
				}
			}
		}
		return d;
	}

	private static int[][] pack(int[] from, int[] to, int n) {
		int g[][] = new int[n][];
		int cnt[] = new int[n];
		Arrays.fill(cnt, 0);
		
		for (int i=0;i<from.length;i++) cnt[from[i]]++;
		for (int i=0;i<n;i++) g[i] = new int[cnt[i]];
		
		for (int i=0;i<from.length;i++) {
			g[from[i]][--cnt[from[i]]] = to[i];
		}
		return g;
	}
	
}