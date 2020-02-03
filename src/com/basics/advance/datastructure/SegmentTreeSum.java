package com.basics.advance.datastructure;

import java.util.Vector;
import java.util.Scanner;
//TODO https://www.codechef.com/viewsolution/21590766
public class SegmentTreeSum {

	private static Vector<SegmentTreeNode> queryIndexes;
	
	public static void main(String args[]) {
		demo();
	}
	
	private static void demo() {
		int a[] = {-1,1,0,3,-2};
		SegmentTree tree = new SegmentTree(a);
		int l=0, r=0;
		String test = "y";
		Scanner in = new Scanner(System.in);
		do {
			l = in.nextInt();
			r = in.nextInt();
			long sum = getSum(tree, l, r);
			System.out.println(sum);
			
			System.out.println("Test more::[y/n]");
			test = in.next();
		}while(test.equalsIgnoreCase("y"));
		in.close();
	}

	private static int getSum(SegmentTree tree, int l, int r) {
		queryIndexes = new Vector<>();
		tree.query(l, r);
		int sum = 0;
		for (SegmentTreeNode node : queryIndexes) {
			sum = sum + node.sum;
		}
		return sum;
	}

    private static class SegmentTree {
        private SegmentTreeNode root;
        
        public SegmentTree(int ar[]) {
            root = buildTree(ar, 0, ar.length - 1, root);
        }
        
        private SegmentTreeNode buildTree(int ar[], int l, int r, SegmentTreeNode segmentTreeNode) {
            if (segmentTreeNode == null) {
                segmentTreeNode = new SegmentTreeNode(l, r);
            }
            if (l < r) {
                int mid = (l + r) >> 1;
                segmentTreeNode.left = buildTree(ar, l, mid, segmentTreeNode.left);
                segmentTreeNode.right = buildTree(ar, mid + 1, r, segmentTreeNode.right);
                if (segmentTreeNode.left != null) {
                    segmentTreeNode.sum+=segmentTreeNode.left.sum;
                }
                if (segmentTreeNode.right != null) {
                	segmentTreeNode.sum+=segmentTreeNode.right.sum;
                }
            } else if (l == r) {
                segmentTreeNode.sum = ar[l];
            } else {
                return null;
            }
            return segmentTreeNode;
        }
        
        private void query(int l, int r) {
            queryTree(root, l, r);
        }
        
        private void queryTree(SegmentTreeNode node, int l, int r) {
            if (node == null)
                return;
            if (node.r < l || node.l > r) {
                return;
            }
            if (node.l >= l && node.r <= r) {
                queryIndexes.add(node);
                return;
            }
            queryTree(node.left, l, r);
            queryTree(node.right, l, r);
        }
    }

    private static class SegmentTreeNode {
        int l, r, sum;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int l, int r) {
            this.l = l;
            this.r = r;
            this.sum = 0;
        }
    }

}