package com.basics.tree;

import java.util.ArrayList;

/**
 * https://www.geeksforgeeks.org/construct-all-possible-bsts-for-keys-1-to-n/
 * 
 * @author ashu
 *
 */
public class AllPossibleBst {

	public static void main(String args[]) {
		ArrayList<Node> totalTreesFrom1toN = constructTrees(1, 2);
		/* Printing preorder traversal of all constructed BSTs */
		System.out.println("Preorder traversals of all constructed BSTs are :");
		for (int i = 0; i < totalTreesFrom1toN.size(); i++) {
			preorder(totalTreesFrom1toN.get(i));
			System.out.println();
		}
	}

	private static void preorder(Node root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preorder(root.left);
			preorder(root.right);
		}
	}

	private static ArrayList<Node> constructTrees(int start, int end) {
		ArrayList<Node> list = new ArrayList<>();
		/*
		 * if start > end then subtree will be empty so returning NULL in the list
		 */
		if (start > end) {
			list.add(null);
			return list;
		}

		/*
		 * iterating through all values from start to end for constructing\ left and
		 * right subtree recursively
		 */
		for (int i = start; i <= end; i++) {
			/* constructing left subtree */
			ArrayList<Node> leftSubtree = constructTrees(start, i - 1);

			/* constructing right subtree */
			ArrayList<Node> rightSubtree = constructTrees(i + 1, end);

			/*
			 * now looping through all left and right subtrees and connecting them to ith
			 * root below
			 */
			for (int j = 0; j < leftSubtree.size(); j++) {
				Node left = leftSubtree.get(j);
				for (int k = 0; k < rightSubtree.size(); k++) {
					Node right = rightSubtree.get(k);
					Node node = new Node(i); // making value i as root
					node.left = left; // connect left subtree
					node.right = right; // connect right subtree
					list.add(node); // add this tree to list
				}
			}
		}
		return list;
	}

	private static class Node {
		Integer data;
		Node left, right;
		private Node() {}
		private Node(int d) {
			this.data = d;
		}
	}

}