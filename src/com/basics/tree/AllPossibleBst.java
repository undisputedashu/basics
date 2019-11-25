package com.basics.tree;

import java.util.ArrayList;


/**
 * https://www.geeksforgeeks.org/construct-all-possible-bsts-for-keys-1-to-n/
 * 
 * @author ashu
 *
 */
public class AllPossibleBst {

	public static void main(String args[]) {
		ArrayList<Node> list = ct(1,3);
		for (Node p : list) {
			System.out.println();
			trav(p);
		}
	}

	private static ArrayList<Node> ct(int s, int e) {
		ArrayList<Node> list = new ArrayList<Node>();
		
		if (s > e) {
			list.add(null);
			return list;
		}
		
		for (int i=s;i<=e;i++) {
			ArrayList<Node> lefts = ct(s,i-1);
			ArrayList<Node> rights = ct(i+1,e);
			
			for (Node left: lefts) {
				for (Node right:rights) {
					Node root = new Node(i);
					root.left = left;
					root.right = right;
					list.add(root);
				}
			}
		}
		return list;
	}
	
	private static void trav(Node p) {
		if (p != null) {
			System.out.print(p.data + "  ");
			trav(p.left);
			trav(p.right);
		}
	}
	
	private static class Node {
		int data;
		Node left, right;
		private Node(int data) {
			this.data = data;
		}
	}

}