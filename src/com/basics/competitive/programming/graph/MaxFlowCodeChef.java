package com.basics.competitive.programming.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * https://www.codechef.com/problems/IITK1P04
 */
public class MaxFlowCodeChef {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), m = in.nextInt();
		long g[][] = new long[n][n];
		for (int i=0;i<n;i++) Arrays.fill(g[i], 0);
		
		for (int i=0;i<m;i++) {
			int u = in.nextInt()-1, v = in.nextInt()-1;
			long w = in.nextLong();
			g[u][v] = w;
		}
		
		int t = in.nextInt();
		while (t-->0) {
			int a = in.nextInt(), b = in.nextInt();
			long ans = findMaxFlow(g, n, a-1, b-1);
			System.out.println(ans);
		}
		in.close();
	}
	
	private static long findMaxFlow(long[][] graph, int n, int s, int t) {
		long rg[][] = new long[n][n];
		for (int i=0;i<n;i++)
			for (int j=0;j<n;j++) rg[i][j] = graph[i][j];
		
		int parent[] = new int[n];
		long max = 0;
		while (bfs(rg, s, t, parent)) {
			long flow = Integer.MAX_VALUE;
			for (int v=t;v!=s;v=parent[v]) {
				int u = parent[v];
				flow = Math.min(flow, rg[u][v]);
			}
			
			for (int v=t;v!=s;v=parent[v]) {
				int u = parent[v];
				rg[u][v]-=flow;
				rg[v][u]+=flow;
			}
			
			max = max + flow;
		}
		
		return max;
	}

	private static boolean bfs(long[][] rg, int s, int t, int[] parent) {
		int n = rg.length;
		boolean vis[] = new boolean[n];
		Arrays.fill(vis, false);
		Deque<Integer> qu = new LinkedList<Integer>();
		qu.add(s);
		vis[s] = true;
		parent[s] = -1;
		
		while (!qu.isEmpty()) {
			int u = qu.poll();
			for (int v=0;v<n;v++) {
				if (!vis[v] && rg[u][v]>0) {
					parent[v] = u;
					vis[v] = true;
					qu.add(v);
				}
			}
		}
		
		return vis[t];
	}

}