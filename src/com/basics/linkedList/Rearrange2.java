package com.basics.linkedList;

/**
 * Rearrange so that first odd nodes then even
 * 1 2 3 4 5 -> 1 3 5 2 4
 * @author ashu
 *
 */
public class Rearrange2 {

	private static Node root = new Node();
	public static void main(String[] args) {
		int a[] = {1,2,3,4,5};
		for (int i=0;i<a.length;i++) add(root, a[i]);
		trav(root);
		System.out.println("*************************");
		root = rearrange(root);
		trav(root);
	}
	
	private static Node rearrange(Node p) {
		if (p == null || p.next == null || p.next.next == null) return p;
		
		Node odd = p, even = p.next, temp = even;
		odd.next = odd.next.next;
		odd = odd.next;
		even.next = null;
		while (odd != null && odd.next != null) {
			Node t = odd.next.next;
			
			even.next = odd.next;
			even = even.next;
			even.next = null;
			
			odd.next = t;
			if (t != null) {
				odd = t;
			}
		}
		odd.next = temp;
		return p;
	}

	private static void trav(Node p) {
		if (p != null) {
			System.out.println(p.data);
			trav(p.next);
		}
	}

	private static void add(Node p, int d) {
		if (p.data == null) p.data = d;
		else if (p.next == null) p.next = new Node(d);
		else add(p.next, d);
	}
	
	private static class Node {
		Integer data;
		Node next;
		private Node(){}
		private Node(int data) {
			this.data = data;
		}
	}

}
