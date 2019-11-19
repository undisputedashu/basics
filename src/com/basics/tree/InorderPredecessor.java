package com.basics.tree;

import com.basics.tree.Tree.Node;

public class InorderPredecessor {

	private static Node root = new Node();
    public static void main(String args[]) {
        Tree t = new Tree();
        t.add(root, 25);
        t.add(root, 15);
        t.add(root, 35);
        t.add(root, 28);
        t.add(root, 20);
        t.add(root, 18);
        t.trav(root);
        System.out.println("**********************");
        Node i = inp(root, 25);
        if (i == null) System.out.println("No successor exist!!");
        else System.out.println(i.data);
    }
    
    private static Node inp(Node p, int d) {
    	Node par = null;
    	
    	while (p != null && p.data != d) {
    		if (d > p.data) {
    			par = p;
    			p = p.right;
    		} else {
    			p = p.left;
    		}
    	}
    	
    	if (p == null) return null;
    	if (p.left != null) return max(p.left);
    	return par;
    }
    
    private static Node max(Node p) {
    	while (p.right != null) p = p.right;
    	return p;
    }

}
