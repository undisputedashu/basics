package com.basics.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

	public static void main(String args[]) {
		Graph g = new Graph(6); 
        g.addEdge(5, 2); 
        g.addEdge(5, 0); 
        g.addEdge(4, 0); 
        g.addEdge(4, 1); 
        g.addEdge(2, 3); 
        g.addEdge(3, 1); 
        
        topoSort(g);
	}
	
	private static void topoSort(Graph g) {
		boolean visited[] = new boolean[g.v];
		Stack<Integer> stack = new Stack<>();
		
		for (int i=0;i<g.v;i++) {
			if (!visited[i]) {
				topo(i, g, visited, stack);
			}
		}
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

	private static void topo(int i, Graph g, boolean[] visited, Stack<Integer> stack) {
		visited[i] = true;
		
		Iterator<Integer> it = g.adj[i].iterator();
		while (it.hasNext()) {
			int n = it.next();
			if (!visited[n]) {
				topo(n, g, visited, stack);
			}
		}
		
		stack.push(i);
	}

	private static class Graph {
		int v;
		List<Integer> adj[];
		Graph(int v) {
			this.v = v;
			adj = new LinkedList[v];
			for (int i=0;i<v;i++)
				adj[i] = new LinkedList<>();
		}
		
		private void addEdge(int s, int d) {
			adj[s].add(d);
		}
	}
	
}