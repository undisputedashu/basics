package com.basics.revise;

//TODO kth smallest remains and inorder predecessor
public class Revise {

	public static void main(String args[]) {
		int g[][] = new int[][] {
			{0,2,7},
			{2,0,3},
			{7,3,0}
		};
		djikstra(g, 0);
	}

	private static void djikstra(int[][] g, int src) {
		int vertices = g.length;
		boolean spt[] = new boolean[vertices];
		int dist[] = new int[vertices];
		for (int i=0;i<vertices;i++) {
			spt[i] = false;
			dist[i] = Integer.MAX_VALUE;
		}
		
		dist[src] = 0;
		
		for (int cnt = 0;cnt<vertices;cnt++) {
			int u = minDistVertex(dist, spt);
			spt[u] = true;
			
			for (int v=0;v<vertices;v++) {
				if (!spt[v] && g[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + g[u][v] < dist[v])
					dist[v] = dist[u] + g[u][v];
			}
		}
		
		printSolution(dist);
	}
	
	private static void printSolution(int dist[]) {
		System.out.println("Vertex   Distance from Source");
		for (int i = 0; i < dist.length; i++)
			System.out.println(i + " tt " + dist[i]);
	}


	private static int minDistVertex(int[] dist, boolean[] spt) {
		int minDist = Integer.MAX_VALUE, minIndex = -1;
		for (int i=0;i<dist.length;i++) {
			if (!spt[i] && dist[i]<minDist) {
				minDist = dist[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	
}