package com.basics.competitive.programming.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/feb14/challenges/bike-racers/problem
 * Application of Hopcroftkarp algorithm and binary search
 */
public class BikeRacers {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
		int bikers[][] = new int[n+1][2];
		for (int i=1;i<=n;i++) {
			for (int j=0;j<2;j++) {
				bikers[i][j] = in.nextInt();
			}
		}
		
		int bikes[][] = new int[m+1][2];
		for (int i=1;i<=m;i++) {
			for (int j=0;j<2;j++) {
				bikes[i][j] = in.nextInt();
			}
		}
		long ans = bikeRacers(bikers, bikes, n, m, k);
		System.out.println(ans);
		in.close();
	}
	
	static long dist[][] = new long[260][260];
    static long bikeRacers(int[][] bikers, int[][] bikes, int n, int m, int k) {
    	for (int i=1;i<=n;i++) {
    		for (int j=1;j<=m;j++) {
    			long x = bikers[i][0]-bikes[j][0];
    			long y = bikers[i][1]-bikes[j][1];
    			dist[i][j] = x*x + y*y;
    		}
    	}
    	
    	long low = 0, high = (long)(1e15);
    	while(low < high) {
            long mid = (low + high) / 2;
            if(check(mid, n, m, k)) {
                high = mid;
            } else {
                low = mid+1 ;
            }
        }
    	return low;
    }
    
	private static boolean check(long val, int n, int m, int k) {
		Graph g = new Graph(n, m);
		for(int i=1; i<=n ; i++) {
	        for( int j=1 ; j<=m ; j++) {
	            if(dist[i][j] <=val) {
	                g.addEdge(i, j);
	            }
	        }
	    }
		
		long match = g.hopcroftKarp();
		if (match>=k) return true;
		return false;
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