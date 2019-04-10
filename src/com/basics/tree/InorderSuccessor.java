package com.basics.tree;

import com.basics.tree.Tree.Node;

public class InorderSuccessor {

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
        Node i = ins(root, 20);
        System.out.println(i.data);
        System.out.println("************************");
        Node q = new Node(-1);
        i = inss(root, q, 28);
        System.out.println(i.data);
    }
    
    //This method is written assuming all elements are positive
    //will improve this method
    private static Node inss(Node p,Node q, int d) {
    	if (p != null) {
    		inss(p.left, q, d);
    		if (q.data == -2) {
    			q.data = p.data;
    		}
    		if (p.data == d) {
    			if (p.right != null) q = inss(p.right);
    			else q.data = -2;
    		}
    		inss(p.right, q, d);
    	}
		return q;
	}

	private static Node inss(Node p) {
		while(p.left != null) p = p.left;
		return p;
	}

	private static boolean found = false;
    //Find out what is wrong with this method :D
    //I did this mistake in one of the interview
    //even in my case found = false was not there in if block
    //this is intended to give a better understanding of recursion
	private static Node ins(Node p, int i) {
		if (p != null) {
			ins(p.left, i);
			if (found) {
				found = false;
				return p;
			}
			if (p.data == i) found = true;
			ins(p.right, i);
		}
		return null;
	}
    
}