package com.basics.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/bottom-view-binary-tree/
 * @author ashu
 */
public class BottomView {

	public static void main(String args[]) {
		Node root = new Node();
		add(root, 25);
        add(root, 15);
        add(root, 35);
        add(root, 20);
        add(root, 10);
        add(root, 12);
        trav(root);
        System.out.println("Bottom view:");
        bottomView(root);
        System.out.println("Bottom view2:");
        bottomView2(root);
	}
	
	private static void bottomView2(Node root) {
		TreeMap<Integer, Integer> t = new TreeMap<>();
		Queue<Holder> q = new LinkedList<BottomView.Holder>();
		q.add(new Holder(root, 0));
		//Note here i am using depth as distance from root in order to reuse same class
		//not as depth, dont get confused by name
		while (!q.isEmpty()) {
			Holder h = q.poll();
			Node r = h.node;
			t.put(h.depth, r.data);
			
			if (r.left != null) q.add(new Holder(r.left, h.depth-1));
			if (r.right != null) q.add(new Holder(r.right, h.depth+1));
		}
		
		for (Entry<Integer, Integer> e : t.entrySet()) {
			System.out.println(e.getValue());
		}
	}

	private static void bottomView(Node root) {
		Map<Integer, Holder> distMap = new HashMap<Integer, BottomView.Holder>();
		bottom(root,0,0, distMap);
		for (Entry<Integer, Holder> e : distMap.entrySet()) {
			System.out.println(e.getValue().node.data);
		}
	}

	private static void bottom(Node p, int depth, int dist, Map<Integer, Holder> distMap) {
		if (p != null) {
			Holder q = distMap.get(dist);
			if (q == null) {
				distMap.put(dist, new Holder(p, depth));
			} else {
				if (depth > q.depth) distMap.put(dist, new Holder(p, depth));
			}
			
			bottom(p.left,depth+1, dist-1, distMap);
			bottom(p.right, depth+1,dist+1,distMap);
		}
	}

	private static void trav(Node p) {
		if (p != null) {
			trav(p.left);
			System.out.println(p.data);
			trav(p.right);
		}
	}
	
	private static void add(Node p, int d) {
		if (p.data == null) p.data = d;
		else if (d < p.data) {
			if (p.left == null) p.left = new Node(d);
			else add(p.left, d);
		} else if (d > p.data) {
			if (p.right == null) p.right = new Node(d);
			else add(p.right, d);
		}
	}
	
	private static class Holder {
		Node node;
		int depth;
		Holder(Node data, int depth) {
			this.node = data;
			this.depth = depth;
		}
	}

	private static class Node {
		Integer data;
		Node left, right;
		Node() {}
		Node(Integer data) {
			this.data = data;
		}
	}
	
}