package com.basics.competitive.programming.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * https://practice.geeksforgeeks.org/problems/find-minimum-s-t-cut-in-a-flow-network/0
 * https://www.geeksforgeeks.org/minimum-cut-in-a-directed-graph/?ref=rp
 */
public class MinCutAdjacencyMatrix {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-->0) {
			int n = in.nextInt(), m = n*n;
			int graph[][] = new int[n][n];
			for (int i=0;i<n;i++) Arrays.fill(graph[i], 0);
			
			for (int i=0;i<n;i++) {
				for (int j=0;j<n;j++) {
					graph[i][j] = in.nextInt();
				}
			}
			
			int s = in.nextInt(), st = in.nextInt();
			findMaxFlow(graph, n, s, st);
		}
		in.close();
	}

	private static void findMaxFlow(int[][] graph, int n, int s, int t) {
		int rg[][] = new int[n][n];
		for (int i=0;i<n;i++)
			for (int j=0;j<n;j++) rg[i][j] = graph[i][j];
		
		int parent[] = new int[n];
		int max = 0;
		while (bfs(rg, s, t, parent)) {
			int flow = Integer.MAX_VALUE;
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
		
		if (max == 0) {
			System.out.println(-1);
			return;
		}
		boolean vis[] = new boolean[n];
		Arrays.fill(vis, false);
		dfs(rg, s, vis);
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				if (graph[i][j]>0 && vis[i] && !vis[j]) {
					System.out.print(i + " " + j + " ");
				}
			}
		}
		System.out.println();
	}

	private static void dfs(int[][] rg, int s, boolean[] vis) {
		int n = rg.length;
		vis[s] = true;
		for (int i=0;i<n;i++) {
			if (!vis[i] && rg[s][i]>0) {
				dfs(rg, i, vis);
			}
		}
	}

	private static boolean bfs(int[][] rg, int s, int t, int[] parent) {
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