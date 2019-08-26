package com.basics.linkedList;

public class AddOneToLL {

	public static void main(String args[]) {
		Node s = new Node();
		add(s,8);
		add(s,9);
		add(s,9);
		trav(s);
		System.out.println("Add one:");
		int carry = add1(s);
		if (carry != 0) {
			Node temp = new Node(carry);
			temp.next = s;
			s = temp;
		}
		trav(s);
	}

	private static int add1(Node p) {
		if (p != null) {
			int carry = add1(p.next);
			int sum = p.data + carry;
			carry = sum/10;
			sum = sum % 10;
			p.data = sum;
			return carry;
		}
		//Returning one as we have to add 1 to linked list
		return 1;
	}

	private static void trav(Node s) {
		if (s != null) {
			System.out.println(s.data);
			trav(s.next);
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
		private Node(int d) {
			this.data = d;
		}
	}
	
}