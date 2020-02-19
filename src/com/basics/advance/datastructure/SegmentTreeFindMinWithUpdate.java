package com.basics.advance.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SegmentTreeFindMinWithUpdate {

	private static Node root = null;
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int a[] = {1,3,2,4,6};
		int n = a.length;
		root = build(root, a, 0, 3);
		int min = findMinimum(n, 0, n-1);
		System.out.println(min);
		int ind = 0, val = 4;
		update(root, ind, a[ind], val);
		min = findMinimum(n, 0, n-1);
		System.out.println(min);
		in.close();
	}
	
	private static void update(Node p, int ind, int old, int val) {
		if (p == null) return;
		if (p.l>ind || p.r<ind) return;
		if (p.l== ind && p.r==ind) {
			p.min = val;
			return;
		}
		p.min = Integer.MAX_VALUE;
		update(p.left, ind, old, val);
		update(p.right, ind, old, val);
		
		if (p.min > p.left.min) p.min = p.left.min;
		if (p.min > p.right.min) p.min = p.right.min; 
	}

	private static int findMinimum(int n, int l, int r) {
		List<Node> nodes = new ArrayList<SegmentTreeFindMinWithUpdate.Node>();
		query(root, l, r, nodes);
		int min = Integer.MAX_VALUE;
		for (Node node : nodes) {
			if (node.min < min) min = node.min;
		}
		return min;
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
				if (p.min > p.left.min) p.min = p.left.min;
			}
			if (p.right != null) {
				if (p.min > p.right.min) p.min = p.right.min;
			}
		} else if (l == r) {
			p.min = a[l];
		} else {
			return null;
		}
		return p;
	}
	
	private static class Node {
		int l,r,min;
		Node left, right;
		private Node(int l, int r) {
			this.l = l;
			this.r = r;
			this.min = Integer.MAX_VALUE;
		}
	}

}