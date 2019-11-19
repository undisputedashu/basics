package com.basics.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.basics.revise.Revise;

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
		djikOptimized(graph, 0);//it has bug 
		System.out.println("Djik proper optimized");
		djik(graph, 0);
	}

	private static void djik(Graph graph, int src) {
		boolean vis[] = new boolean[graph.vertices];
		int dist[] = new int[graph.vertices];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(graph.vertices, new VertexComparator());
		pq.add(new Vertex(src, 0));
		
		while (!pq.isEmpty()) {
			int u = pq.poll().v;
			vis[u] = true;
			
			for (Edge e:graph.adj[u]) {
				if (!vis[e.vertex] && dist[u] + e.weight < dist[e.vertex]) {
					dist[e.vertex] = dist[u] + e.weight;
					pq.add(new Vertex(e.vertex, dist[e.vertex]));
				}
			}
		}
		
		printSolution(dist);
	}

	private static class Vertex {
		int v;
		int d;
		Vertex(int v, int d) {
			this.v = v;
			this.d = d;
		}
	}
	
	private static class VertexComparator implements Comparator<Vertex> {
		
		@Override
		public int compare(Vertex v1, Vertex v2) {
			return v1.d < v2.d ? -1 : 1;
		}
		
	}

	//It has bug to be reviewed
	private static void djikOptimized(Graph graph, int src) {
		int vertices = graph.vertices;
		boolean visited[] = new boolean[vertices];
		int dist[] = new int[vertices];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		PriorityQueue<Edge> minheap = new PriorityQueue<Edge>(vertices, new EdgeComparator());
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
	
	private static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight < o2.weight ? -1 : 1;
		}
		
	}
	
}