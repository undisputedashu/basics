package com.basics.arrays;

import java.util.Collections;
import java.util.PriorityQueue;
/**
 * https://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/
 * @author ashu
 *
 */
public class MedianStreamOfIntegers {
	private static PriorityQueue<Integer> minheap = new PriorityQueue<Integer>();
	private static PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(Collections.reverseOrder());
	
	public static void main(String args[]) {
		int a[] = {5,15,1,3};
		int n = a.length;
		for (int i=0;i<n;i++) {
			insert(a[i]);
			balance();
			double m = getMedian();
			System.out.println(m);
		}
	}

	private static double getMedian() {
		double m;
		if (minheap.size() > maxheap.size()) m = minheap.peek();
		else if (maxheap.size() > minheap.size()) m = maxheap.peek();
		else m = (maxheap.peek() + minheap.peek()) / 2.0;
		return m;
	}

	private static void balance() {
		int diff = Math.abs(minheap.size() - maxheap.size());
		if (diff < 2) return;
		
		if (minheap.size() > maxheap.size()) {
			maxheap.add(minheap.poll());
		} else {
			minheap.add(maxheap.poll());
		}
	}

	private static void insert(int n) {
		if (minheap.size() == 0 || n > minheap.peek()) minheap.add(n);
		else maxheap.add(n);
	}
	
}