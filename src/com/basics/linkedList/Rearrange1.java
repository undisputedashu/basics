package com.basics.linkedList;

import com.basics.linkedList.LinkedList.Node;


/**
 * https://www.geeksforgeeks.org/rearrange-a-given-linked-list-in-place/
 * @author ashu
 *
 */
public class Rearrange1 {

	public static void main(String args[]) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		Integer a[] = {1,2,3,4,5};
		ll.add(a);
		ll.trav();
		System.out.println("**************************************");
		Node<Integer> s = ll.getStart();
		Rearrange1 r = new Rearrange1();
		ll.setStart(r.rearrange(s));;
		ll.trav();
	}

	//Reverse recursively
	private Node<Integer> rev(Node<Integer> first) {
		if (first == null || first.next == null) return first;
		Node<Integer> second = first.next;
		first.next = null;
		Node<Integer> rest = rev(second);
		second.next = first;
		return rest;
	}

	private Node<Integer> rearrange(Node<Integer> s) {
		if (s == null || s.next == null || s.next.next == null) return s;
		Node<Integer> slow = s, fast = s.next;
		while (fast != null  && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		Node<Integer> h1 = s, h2 = rev(slow.next);
		slow.next = null;
		
		Node<Integer> curr = new Node<Integer>(), temp = curr;
		while (h1 != null || h2 != null) {
			if (h1 != null) {
				curr.next = h1;
				curr = h1;
				h1 = h1.next;
			}
			if (h2 != null) {
				curr.next = h2;
				curr = h2;
				h2 = h2.next;
			}
		}
		return temp.next;
	}
	
}