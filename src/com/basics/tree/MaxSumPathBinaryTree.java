package com.basics.tree;

/**
 * https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
 * @author ashu
 *
 */
public class MaxSumPathBinaryTree {

	public static void main(String args[]) {
		Node root = new Node(10); 
        root.left = new Node(2); 
        root.right = new Node(10); 
        root.left.left = new Node(20); 
        root.left.right = new Node(1); 
        root.right.right = new Node(-25); 
        root.right.right.left = new Node(1000); 
        root.right.right.right = new Node(4); 
        
        int maxPathSum = getSum(root);
        System.out.println(maxPathSum);
	}
	
	private static int getSum(Node root) {
		Res res = new Res();
        res.val = Integer.MIN_VALUE;
        getSum(root, res);
        return res.val;
	}
	
    private static int getSum(Node p, Res res) {
    	if (p == null) return 0;
    	int l = getSum(p.left,res);
    	int r = getSum(p.right,res);
    	
    	int max_Path = Math.max(p.data, p.data + Math.max(l, r));
    	
    	int max_root_path = Math.max(max_Path, p.data + l + r);
    	
    	res.val = Math.max(res.val, max_root_path);
    	
    	return max_Path;
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

	private static class Res {
		int val;
	}

}