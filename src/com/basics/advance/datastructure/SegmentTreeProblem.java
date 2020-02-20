package com.basics.advance.datastructure;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
//https://www.codechef.com/problems/CHEFLKJ
public class SegmentTreeProblem {

	private static Node root = null;
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int q = in.nextInt();
		int a[] = new int[n];
		for (int i=0;i<n;i++) a[i] = in.nextInt();
		
		root = null;
		root = build(root, a, 0, n-1);
		
		for (int i=0;i<q;i++) {
			int op = in.nextInt();
			if (op == 1) {
				int ind = in.nextInt();
				int value = in.nextInt();
				ind--;
				update(root, ind, a[ind], value);
				a[ind] = value;
			} else {
				int l = in.nextInt();
				int r = in.nextInt();
				if (checkForDominatingArray(--l, --r)) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
				}
			}
		}

		in.close();
	}
	
	private static boolean checkForDominatingArray(int l, int r) {
		List<Node> nodes = new ArrayList<Node>();
		query(root, l, r, nodes);
		int min = 0;
		if (l == r) {
			min = 1;
		} else if (l+1 == r) {
			min = 2;
		} else {
			int diff = r+1 - l;
			min = diff/2 + 1;
		}
		
		for (Node node : nodes) {
			int freq = 0;
			int max = node.maxFreq;
			for (Node no : nodes) {
				freq = freq + no.map.getOrDefault(max, 0);
			}
			if (freq >= min) return true;
		}
		return false;
	}

	private static void update(Node p, int ind, int old, int val) {
		if (p == null) return;
		if (p.l>ind || p.r<ind) return;
		if (p.l== ind && p.r==ind) {
			p.map.remove(old);
			p.map.put(val, 1);
			p.maxFreq = val;
			return;
		}
		update(p.left, ind, old, val);
		update(p.right, ind, old, val);

		p.map.put(old, p.map.getOrDefault(old, 1)-1);
		p.map.put(val, p.map.getOrDefault(val, 0) + 1);
		int min = p.r + 1- p.l;
		min = min/2+1;
		
		if (p.map.getOrDefault(p.left.maxFreq, 0) >= min) p.maxFreq = p.left.maxFreq;
		else if (p.map.getOrDefault(p.right.maxFreq, 0) >= min) p.maxFreq = p.right.maxFreq;
		else p.maxFreq = -1;
	}

	private static void query(Node p, int l, int r, List<Node> nodes) {
		if (p == null) return;
		if (p.l>r || p.r<l) return;
		if (p.l>=l && p.r<=r) {
			nodes.add(p);
			return;
		}
		query(p.left,l,r,nodes);
		query(p.right,l,r,nodes);
	}

	private static Node build(Node p, int a[], int l, int r) {
		if (p == null) {
			p = new Node(l,r);
		}
		
		if (l<r) {
			int mid = l + (r-l)/2;
			p.left = build(p.left,a,l,mid);
			p.right = build(p.right,a,mid+1,r);

			if (p.left != null) {
				for (Entry<Integer, Integer> e : p.left.map.entrySet()) {
					p.map.put(e.getKey(), e.getValue() + p.map.getOrDefault(e.getKey(), 0));
				}
			}
			if (p.right != null) {
				for (Entry<Integer, Integer> e : p.right.map.entrySet()) {
					p.map.put(e.getKey(), e.getValue() + p.map.getOrDefault(e.getKey(), 0));
				}
			}
			
		} else if (l == r) {
			p.map.put(a[l], 1);
			p.maxFreq = a[l];
		} else {
			return null;
		}
		
		updateMaxFreq(p);
		return p;
	}

	private static void updateMaxFreq(Node p) {
		long max = 0;
		p.maxFreq = -1;
		for (Entry<Integer, Integer> e : p.map.entrySet()) {
			if (max < e.getValue()) {
				p.maxFreq = e.getKey();
				max = e.getValue();
			}
		}
	}
	
	private static class Node {
		int l,r;
		Node left, right;
		Map<Integer, Integer> map;
		int maxFreq;
		private Node(int l, int r) {
			this.l = l;
			this.r = r;
			this.map = new HashMap<Integer, Integer>();
			maxFreq = 0;
		}
	}
	
}