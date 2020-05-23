package com.basics.competitive.programming.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class HopcroftKarp {

	public static void main(String args[]) {
		Graph graph = new Graph(4, 4);
		graph.addEdge(1, 2); 
	    graph.addEdge(1, 3); 
	    graph.addEdge(2, 1); 
	    graph.addEdge(3, 2); 
	    graph.addEdge(4, 2); 
	    graph.addEdge(4, 4); 
	    System.out.println(graph.hopcroftKarp());
	}

	private static class Graph {
		private static final int NIL = 0;
		private static final int INF = Integer.MAX_VALUE;
		List<Integer> adj[];
		int m,n;
		int pu[],pv[],dist[];
		
		private int hopcroftKarp() {
			pu = new int[m+1];
			pv = new int[n+1];
			dist = new int[m+1];
			
			for (int u=0; u<=m; u++) pu[u] = NIL; 
		    for (int v=0; v<=n; v++) pv[v] = NIL;
		    
		    int result = 0;
		    while (bfs()) {
		    	for (int u=1;u<=m;u++) {
		    		if (pu[u]==NIL && dfs(u)) {
		    			result++;
		    		}
		    	}
		    }
			return result;
		}
		
		private boolean bfs() {
			Deque<Integer> qu = new LinkedList<Integer>();
			for (int u = 1; u <= m; u++) {
				if (pu[u] == NIL) {
					// u is not matched
					dist[u] = 0;
					qu.add(u);
				}

				// Else set distance as infinite so that this vertex
				// is considered next time
				else
					dist[u] = INF;
			}

			dist[NIL] = INF;

			// Q is going to contain vertices of left side only.
			while (!qu.isEmpty()) {
				// Dequeue a vertex
				int u = qu.poll();

				// If this node is not NIL and can provide a shorter path to NIL
				if (dist[u] < dist[NIL]) {
					// Get all adjacent vertices of the dequeued vertex u
					for (int i = 0; i < adj[u].size(); i++) {
						int v = adj[u].get(i);
						// If pair of v is not considered so far
						// (v, pairV[V]) is not yet explored edge.
						if (dist[pv[v]] == INF) {
							// Consider the pair and add it to queue
							dist[pv[v]] = dist[u] + 1;
							qu.add(pv[v]);
						}
					}
				}
			}

			// If we could come back to NIL using alternating path of distinct
			// vertices then there is an augmenting path
			return (dist[NIL] != INF);
		}
		
		private boolean dfs(int u) {
			if (u != NIL) {
				for (int i = 0; i < adj[u].size(); i++) {
					// Adjacent to u
					int v = adj[u].get(i);

					// Follow the distances set by BFS
					if (dist[pv[v]] == dist[u] + 1) {
						// If dfs for pair of v also returns
						// true
						if (dfs(pv[v]) == true) {
							pv[v] = u;
							pu[u] = v;
							return true;
						}
					}
				}

				// If there is no augmenting path beginning with u.
				dist[u] = INF;
				return false;
			}
			return true;
		}

		private Graph(int m, int n) {
			this.m = m;
			this.n = n;
			adj = new ArrayList[m+1];
			for (int i=0;i<=m;i++) adj[i] = new ArrayList<>();
		}
		
		private void addEdge(int u, int v) {
			adj[u].add(v);
		}
		
	}
	
}