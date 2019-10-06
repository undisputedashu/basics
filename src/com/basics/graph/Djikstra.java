package com.basics.graph;

/**
 * https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 * @author ashu
 *
 */
public class Djikstra {
	
	public static void main(String args[]) {
		int g[][] = new int[][] { 
			{ 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } 
            }; 
		djikstra(g, 0);
	}

	private static void djikstra(int[][] g, int src) {
		int vertices = g.length;
		boolean visited[] = new boolean[vertices];
		int dist[] = new int[vertices];
		
		for (int i=0;i<vertices;i++) {
			visited[i] = false;
			dist[i] = Integer.MAX_VALUE;
		}
		dist[src] = 0;
		
		for (int i=0;i<vertices;i++) {
			int u = findMin(dist, visited);
			visited[u] = true;
			
			for (int v=0;v<vertices;v++) {
				if (!visited[v] && g[u][v] != 0) {
					int newDist = dist[u] + g[u][v];
					if (newDist < dist[v]) {
						dist[v] = newDist;
					}
				}
			}
			
		}
		
		printSolution(dist);
	}

	private static void printSolution(int dist[]) {
		System.out.println("Vertex   distance from Source");
		for (int i = 0; i < dist.length; i++)
			System.out.println(i + " => " + dist[i]);
	}

	private static int findMin(int[] dist, boolean[] visited) {
		int minIndex = -1, minDist = Integer.MAX_VALUE;
		for (int i=0;i<dist.length;i++) {
			if (!visited[i] && dist[i]<minDist) {
				minIndex = i;
				minDist = dist[i];
			}
		}
		return minIndex;
	}
	
}