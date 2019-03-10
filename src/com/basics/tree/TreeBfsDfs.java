package com.basics.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.basics.tree.Tree.Node;

public class TreeBfsDfs {

    public static void main(String args[]) {
        Node root = new Node();
        Tree t = new Tree();
        t.add(root, 25);
        t.add(root, 15);
        t.add(root, 35);
        t.add(root, 28);
        t.add(root, 20);
        t.add(root, 10);
        t.trav(root);
        bfs(root);
        dfs(root);
    }
    
    private static void bfs(Node p) {
        System.out.println("***************Bfs****************");
        Queue<Node> qu = new LinkedList<Node>();
        qu.add(p);
        while (!qu.isEmpty()) {
            Node q = qu.poll();
            System.out.println(q.data);
            if (q.left != null) qu.add(q.left);
            if (q.right != null) qu.add(q.right);
        }
    }

    private static void dfs(Node p) {
        System.out.println("***************Dfs****************");
        Stack<Node> stack = new Stack<Node>();
        stack.add(p);
        while (!stack.isEmpty()) {
            Node st = stack.pop();
            System.out.println(st.data);
            if (st.right != null) stack.push(st.right);
            if (st.left != null) stack.push(st.left);
        }
    }
    
}