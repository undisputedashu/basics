package com.basics.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;


public class DepthFirstTraversal {

	public static void main(String args[]) {
		Graph g = new Graph(4);
		g.addEdge(0, 1); 
        g.addEdge(0, 2); 
        g.addEdge(1, 2); 
        g.addEdge(2, 0); 
        g.addEdge(2, 3); 
        g.addEdge(3, 3);
        dfsUsingStack(g, 2);
        System.out.println("\nDfs recursive::");
        dfsRecursive(g,2);
	}
	
	private static void dfsRecursive(Graph g, int s) {
		boolean visited[] = new boolean[g.vertices];
		dfs(g, visited, s);
	}

	private static void dfs(Graph g, boolean[] visited, int s) {
		System.out.println(s + " ");
		visited[s] = true;
		Iterator<Integer> it = g.adj[s].iterator();
		while (it.hasNext()) {
			s = it.next();
			if (!visited[s])
				dfs(g,visited, s);
		}
	}

	private static void dfsUsingStack(Graph g, int s) {
		int v = g.vertices;
		boolean visited[] = new boolean[v];
		visited[s] = true;
		Stack<Integer> stack = new Stack<>();
		stack.add(s);
		while (!stack.isEmpty()) {
			s = stack.pop();
			System.out.print(s + " ");
			Iterator<Integer> it = g.adj[s].iterator();
			while (it.hasNext()) {
				s = it.next();
				if (!visited[s]) {
					visited[s] = true;
					stack.push(s);
				}
			}
		}
	}

	private static class Graph {
		int vertices;
		LinkedList<Integer> adj[];
		
		private Graph(int v) {
			vertices = v;
			adj = new LinkedList[vertices];
			for (int i=0;i<vertices;i++)
				adj[i] = new LinkedList<>();
		}
		
		private void addEdge(int s, int d) {
			adj[s].add(d);
		}
	}
	
}