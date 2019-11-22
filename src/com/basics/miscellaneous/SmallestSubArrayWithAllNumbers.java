package com.basics.miscellaneous;

import java.util.HashSet;
import java.util.Set;

public class SmallestSubArrayWithAllNumbers {

	private static Node front, rear;
	
	public static void main(String args[]) {
		 int a[] = {1, 1, 2, 2, 3, 3, 1, 4, 5};
		 int n = a.length;
		 Set<Integer> set = new HashSet<Integer>();
		 for (int i=0;i<n;i++) set.add(a[i]);
		 find(a,n,set.size());
	}

	private static void find(int[] a, int n, int k) {
		Set<Integer> set = new HashSet<Integer>();
		
		int i=0, j=0, min = Integer.MAX_VALUE;
		while (i<n) {
			if (set.size() == k) {
				if (i-j < min) min = i-j;
			} 
			if (front != null && a[front.ind] == a[i]) {
				dequeFromFront();
				enqueAtRear(i);
				j++;
				while (front != null && front.next != null && a[front.ind] == a[front.next.ind]) {
					dequeFromFront();
					j++;
				}
			} else {
				enqueAtRear(i);
			}
			set.add(a[i]);
			i++;
		}
		
		System.out.println(front.ind + "   " + rear.ind);
		System.out.println("Smallest subarray size : " + min);
	}
	
	private static void enqueAtRear(int d) {
		Node temp = new Node(d);
		if (front == null) {
			front = rear = temp;
		} else {
			rear.next = temp;
			temp.prev = rear;
			rear = rear.next;
		}
	}

	private static void dequeFromFront() {
		if (front == null) return;
		front = front.next;
		if (front == null) rear = null;
		else front.prev = null;
	}

	private static class Node {
		int ind;
		Node prev,next;
		private Node(int ind) {
			this.ind = ind;
		}
	}
	
}