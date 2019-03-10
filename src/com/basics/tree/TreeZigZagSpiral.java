package com.basics.tree;

import java.util.Stack;

import com.basics.tree.Tree.Node;

public class TreeZigZagSpiral {

    public static void main(String args[]) {
        Node root = new Node();
        Tree t = new Tree();
        t.add(root, 25);
        t.add(root, 15);
        t.add(root, 35);
        t.add(root, 28);
        t.add(root, 20);
        t.add(root, 40);
        t.add(root, 10);
        t.trav(root);
        System.out.println("*************zigzag*******************");
        zigzag(root);
        System.out.println("*************spiral*******************");
        spiral(root);
    }

    private static void zigzag(Node p) {
        if (p == null) return;
        Stack<Node> s1 = new Stack<Tree.Node>();
        s1.push(p);
        Stack<Node> s2 = new Stack<Tree.Node>();
        while (!s1.isEmpty() || !s2.isEmpty()) {
            System.out.println();
            while (!s1.isEmpty()) {
                Node q = s1.pop();
                System.out.print(q.data + " ");
                if (q.left != null) s2.push(q.left);
                if (q.right != null) s2.push(q.right);
            }
            System.out.println();
            while (!s2.isEmpty()) {
                Node q = s2.pop();
                System.out.print(q.data + " ");
                if (q.left != null) s1.push(q.left);
                if (q.right != null) s1.push(q.right);
            }
        }
    }
    
    private static void spiral(Node p) {
        if (p == null) return;
        Stack<Node> s1 = new Stack<Tree.Node>();
        s1.push(p);
        Stack<Node> s2 = new Stack<Tree.Node>();
        while (!s1.isEmpty() || !s2.isEmpty()) {
            System.out.println();
            while (!s1.isEmpty()) {
                Node q = s1.pop();
                System.out.print(q.data + " ");
                if (q.right != null) s2.push(q.right);
                if (q.left != null) s2.push(q.left);
            }
            System.out.println();
            while (!s2.isEmpty()) {
                Node q = s2.pop();
                System.out.print(q.data + " ");
                if (q.left != null) s1.push(q.left);
                if (q.right != null) s1.push(q.right);
            }
        }
    }

}