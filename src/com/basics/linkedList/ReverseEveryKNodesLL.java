package com.basics.linkedList;

public class ReverseEveryKNodesLL {

	public static void main(String args[]) {
		Node s = createLL();
		trav(s);
		System.out.println("Reverse every k nodes 1st::");
		s = reverseKelements(s, 4);
		trav(s);
		System.out.println("Reverse every k nodes 2nd::");
		s = createLL();
		s = reverseKelements2(s, 4);
		trav(s);
	}

	private static Node reverseKelements2(Node s, int k) {
		//1 2 3 4 5 6 7 , k = 4
		//4 3 2 1 7 6 5
		if (s == null || s.next == null || k<2 || k > getLength(s)) {
			trav(s);
			return s;
		}
		
		Node prev = null, next = null, p = s, q = null;
		while (p != null) {
			q = p;
			int ctr = 1;
			while (q != null && ctr < k) {
				q = q.next;
				ctr++;
			}
			if (q == null) {
				p = rev(p);
				prev.next = p;
				break;
			}
			next = q.next;
			q.next = null;
			rev(p);
			if (prev == null) {
				s = q;
			} else {
				prev.next = q;
			}
			prev = p;
			p = next;
		}
		return s;
	}

	private static Node reverseKelements(Node s, int k) {
		//1 2 3 4 5 6 7 , k = 4
		//4 3 2 1 5 6 7
		if (s == null || s.next == null || k<2 || k > getLength(s)) {
			trav(s);
			return s;
		}
		Node prev = null, next = null, p = s, q = null;
		while (p != null) {
			int ctr = 1;
			q = p;
			while(ctr < k && q != null) {
				q = q.next;
				ctr++;
			}
			if (q == null) {
				prev.next = next;
				break;
			}
			next = q.next;
			q.next = null;
			rev(p);
			if (prev == null) {
				s = q;
			} else {
				prev.next = q;
			}
			prev = p;
			p = next;
		}
		return s;
	}

	private static Node rev(Node p) {
		if (p == null || p.next == null) return p;
		Node second = p.next;
		p.next = null;
		Node rest = rev(second);
		second.next = p;
		return rest;
	}

	private static int getLength(Node s) {
		int ctr = 0;
		Node q = s;
		while (q != null) {
			ctr++;
			q = q.next;
		}
		return ctr;
	}

	private static Node createLL() {
		Node s = new Node();
		add(s,1);
		add(s,2);
		add(s,3);
		add(s,4);
		add(s,5);
		add(s,6);
		add(s,7);
		return s;
	}

	private static void trav(Node s) {
		if (s != null) {
			System.out.println(s.data);
			trav(s.next);
		}
	}

	private static void add(Node s, int d) {
		if (s.data == null) {
			s.data = d;
		} else if (s.next == null) {
			s.next = new Node(d);
		} else {
			add(s.next, d);
		}
	}

	private static class Node {
		Integer data;
		Node next;
		private Node() {}
		private Node(int d) {
			this.data = d;
		}
	}
	
}