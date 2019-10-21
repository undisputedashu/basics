package com.basics.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

//In graph questions when to mark visited true is key
public class DjikstraByAdjacencyList {

	public static void main(String args[]) {
		Graph graph = new Graph(4);
		graph.addEdge(0, new Edge(1, 4));
		graph.addEdge(1, new Edge(0, 4));
		graph.addEdge(0, new Edge(2, 7));
		graph.addEdge(2, new Edge(0, 7));
		graph.addEdge(1, new Edge(2, 2));
		graph.addEdge(2, new Edge(1, 2));
		
		graph.addEdge(2, new Edge(3, 2));
		graph.addEdge(1, new Edge(3, 5));
		djikstra(graph, 0);
		System.out.println("Optimizing find min in djikstra::");
		djikOptimized(graph, 0);
	}

	private static void djikOptimized(Graph graph, int src) {
		int vertices = graph.vertices;
		boolean visited[] = new boolean[vertices];
		int dist[] = new int[vertices];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		PriorityQueue<Edge> minheap = new PriorityQueue<Edge>(vertices, new VertexComparator());
		minheap.add(new Edge(src, 0));
		while (!minheap.isEmpty()) {
			int u = minheap.poll().vertex;
			visited[u] = true;
			
			for (Edge v : graph.adj[u]) {
				if (!visited[v.vertex]) {
					int newDist = dist[u] + v.weight;
					if (newDist < dist[v.vertex]) {
						dist[v.vertex] = newDist;
						minheap.add(v);
					}
				}
			}
		}
		
		printSolution(dist);
	}

	private static void djikstra(Graph graph, int src) {
		int vertices = graph.vertices;
		boolean visited[] = new boolean[vertices];
		int dist[] = new int[vertices];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		
		for (int i=0;i<vertices;i++) {
			int u = getMinIndex(dist, visited);
			visited[u] = true;
			
			for (Edge v : graph.adj[u]) {
				if (!visited[v.vertex]) {
					int newDist = dist[u] + v.weight;
					if (newDist < dist[v.vertex]) {
						dist[v.vertex] = newDist;
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

	private static int getMinIndex(int[] dist, boolean[] visited) {
		int minIndex = -1, minDist = Integer.MAX_VALUE;
		for (int i=0;i<dist.length;i++) {
			if (!visited[i] && dist[i] < minDist) {
				minIndex = i;
				minDist = dist[i];
			}
		}
		return minIndex;
	}

	private static class Graph {
		int vertices;
		List<Edge> adj[];
		Graph(int vertices) {
			this.vertices = vertices;
			adj = new LinkedList[vertices];
			for (int i=0;i<vertices;i++) {
				adj[i] = new LinkedList<>();
			}
		}
		
		private void addEdge(int source, Edge vertex) {
			adj[source].add(vertex);
		}
	}
	
	private static class Edge {
		int vertex;
		int weight;
		Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	private static class VertexComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight < o2.weight ? -1 : 1;
		}
		
	}
	
}
