package com.basics.tree;

public class Tree {

    private static Node root = new Node();
    public static void main(String args[]) {
        Tree t = new Tree();
        t.add(root, 25);
        t.add(root, 15);
        t.add(root, 35);
        t.add(root, 28);
        t.add(root, 20);
        t.trav(root);
    }
    
    void trav(Node p) {
        if (p != null) {
            trav(p.left);
            System.out.println(p.data);
            trav(p.right);
        }
    }
    
    void add(Node p, int d) {
        if (p.data == null) {
            p.data = d;
        } else if (d < p.data) {
            if (p.left == null) p.left = new Node(d);
            else add(p.left, d);
        } else if (d > p.data){
            if (p.right == null) p.right = new Node(d);
            else add(p.right, d);
        } else {
            System.out.println("Element already exists.");
        }
    }
    
    static class Node {
        Integer data;
        Node left, right;
        Node() {}
        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }
    
}