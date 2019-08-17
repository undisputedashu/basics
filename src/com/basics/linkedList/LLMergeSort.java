package com.basics.linkedList;

public class LLMergeSort {

	private static Node s = new Node();
	public static void main(String args[]) {
		add(s, 4);
		add(s, 1);
		add(s, 3);
		add(s, 2);
		trav(s);
		System.out.println("********************");
		s = ms(s);
		trav(s);
	}
	
	private static Node ms(Node p) {
		if (p == null || p.next == null) return p;
		Node slow = p, fast = p.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		Node h1 = p, h2 = slow.next;
		slow.next = null;
		
		Node l = ms(h1);
		Node r = ms(h2);
		
		return mergeRecursive(l, r);
	}

	private static Node mergeRecursive(Node l, Node r) {
		if (l == null) return r;
		if (r == null) return l;
		Node temp = null;
		if (l.data <= r.data) {
			temp =  l;
			temp.next = mergeRecursive(l.next, r);
		} else {
			temp = r;
			temp.next = mergeRecursive(l, r.next);
		}
		return temp;
	}

	private static Node mergeIterative(Node l, Node r) {
		Node temp = new Node(), cache = temp;
		while (l != null && r != null) {
			if (l.data <= r.data) {
				temp.next = l;
				temp = temp.next;
				l = l.next;
			} else {
				temp.next = r;
				temp = temp.next;
				r = r.next;
			}
		}
		
		while (l != null) {
			temp.next = l;
			temp = temp.next;
			l = l.next;
		}
		while (r != null) {
			temp.next = r;
			temp = temp.next;
			r = r.next;
		}
		return cache.next;
	}

	private static void trav(Node p) {
		if (p != null) {
			System.out.println(p.data);
			trav(p.next);
		}
	}

	private static void add(Node p, int d) {
		if (p.data == null) {
			p.data = d;
		} else if (p.next == null) {
			p.next = new Node(d);
		} else {
			add(p.next, d);
		}
	}

	private static class Node {
		Integer data;
		Node next;
		private Node() {}
		private Node(Integer d) {
			this.data = d;
		}
	}

}