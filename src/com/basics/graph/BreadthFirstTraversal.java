package com.basics.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class BreadthFirstTraversal {

	public static void main(String args[]) {
		Graph g = new Graph(4);
		g.addEdge(0, 1); 
        g.addEdge(0, 2); 
        g.addEdge(1, 2); 
        g.addEdge(2, 0); 
        g.addEdge(2, 3); 
        g.addEdge(3, 3);
        bfs(g, 2);
	}
	
	private static void bfs(Graph g, int s) {
		boolean visited[] = new boolean[g.getVertices()]; 
		LinkedList<Integer> queue = new LinkedList<>();
		visited[s] = true;
		queue.add(s);
		while (!queue.isEmpty()) {
			s = queue.poll();
			System.out.print(s + " ");
			Iterator<Integer> it = g.getAdj()[s].iterator();
			while (it.hasNext()) {
				s = it.next();
				if (!visited[s]) {
					visited[s] = true;
					queue.add(s);
				}
			}
		}
	}

	private static class Graph {
		int v;
		public int getVertices() {
			return v;
		}

		public LinkedList<Integer>[] getAdj() {
			return adj;
		}

		LinkedList<Integer> adj[];
		
		Graph(int n) {
			v = n;
			adj = new LinkedList[v];
			for (int i=0;i<v;i++)
				adj[i] = new LinkedList<>();
		}
		
		private void addEdge(int s, int d) {
			adj[s].add(d);
		}
	}
	
}