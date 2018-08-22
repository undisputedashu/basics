package com.basics.linkedList;

public class LinkedList<T> {

	private Node<T> start = null;
	
	LinkedList() {

	}
	
	Node<T> getStart() {
		return start;
	}
	
	void setStart(Node<T> start) {
		this.start = start;
	}
	
	void add(T a[]) {
		for (int i=0;i<a.length;i++) {
			add(a[i]);
		}
	}
	
	void add(T data) {
		Node<T> temp = new Node<T>(data);
		if (start == null) {
			start = temp;
		} else {
			Node<T> q = start;
			while (q.next != null) {
				q = q.next;
			}
			q.next = temp;
		}
	}
	
	void trav() {
		Node<T> p = start;
		while (p != null) {
			System.out.println(p.data);
			p = p.next;
		}
	}
	
	static class Node<T> {
		T data;
		Node<T> next;
		Node() {}
		Node(T data) {
			this.data = data;
			this.next = null;
		}
	}
	
}