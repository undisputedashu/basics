package com.basics.linkedList;

public class LinkedList<T> {

	private Node<T> start = null;
	
	LinkedList() {
		start = new Node<T>();
	}
	
	Node<T> getStart() {
		return start;
	}
	
	void setStart(Node<T> start) {
		this.start = start;
	}
	
	void add(T a[]) {
		for (int i=0;i<a.length;i++) {
			add(start, a[i]);
		}
	}
	
	void add(Node<T> p, T data) {
		if (start.data == null) {
			start.data = data;
		} else if (p.next == null) {
			p.next = new Node<T>(data);
		} else {
			add(p.next, data);
		}
	}

	void trav() {
		trav(start);
	}

	void trav(Node<T> p) {
		if (p != null) {
			System.out.println(p.data);
			trav(p.next);
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