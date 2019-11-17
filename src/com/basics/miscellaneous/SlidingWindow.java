package com.basics.miscellaneous;

/**
 * 
 * @author ashu
 * https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
 */
public class SlidingWindow {

	private static Node front, rear;
	
	public static void main(String args[]) {
		int a[] = {5,2,3,4,3};
		int n = a.length, w = 3;
		printMinInSlidingWindow(a,n,w);
	}
	
	private static void printMinInSlidingWindow(int[] a, int n, int w) {
		
		for (int i=0;i<w && i<n;i++) {
			while (rear != null && a[i]<a[rear.ind]) dequeFromRear();
			enqueAtRear(i);
		}
		
		for (int i=w;i<n;i++) {
			System.out.println(a[front.ind]);
			while (front != null && i-w>=front.ind) dequeFromFront();
			while(rear != null && a[i]<a[rear.ind]) dequeFromRear();
			enqueAtRear(i);
		}
		System.out.println(a[front.ind]);
	}

	private static void dequeFromFront() {
		if (front == null) return;
		front = front.next;
		if (front == null) rear = null;
		else front.prev = null;
	}
	
	private static void dequeFromRear() {
		if (rear == null) return;
		rear = rear.prev;
		if (rear == null) front = null;
		else rear.next = null;
	}
	
	private static void enqueAtRear(int i) {
		Node temp = new Node(i);
		if (front == null) {
			front = rear = temp;
		} else {
			rear.next = temp;
			temp.prev = rear;
			rear = rear.next;
		}
	}
	
	private static class Node {
		int ind;
		Node next, prev;
		private Node(int ind) {
			this.ind = ind;
		}
	}
	
}