package com.basics.arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd74/00000000002b1355
 */
public class SquareDance {

	private static final String NEWLINE = "\n";
	private static final String SPACE = " ";
	private static final String COLON = ":";
	private static final String CASE = "Case #";
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt();
		int t = 1;
		while (t <= T) {
			StringBuilder sb = new StringBuilder();
			int r = in.nextInt(), c = in.nextInt();
			int[][] a = new int[r][c];
			for (int i=0;i<r;i++) {
				for (int j=0;j<c;j++) {
					a[i][j] = in.nextInt();
				}
			}
			long res = get(a, r, c);
			sb.append(CASE).append(t).append(COLON).append(SPACE).append(res).append(NEWLINE);
			System.out.print(sb);
			t++;
		}
		in.close();
	}

	private static long get(int[][] a, int m, int n) {
		List<Data> rows = new ArrayList<Data>();
		List<Data> cols = new ArrayList<Data>();
		for (int i=0;i<m;i++) rows.add(new Data());
		for (int i=0;i<n;i++) cols.add(new Data());
		
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				int d = a[i][j];
				Data row = rows.get(i);
				row.add(j, d);
				
				Data col = cols.get(j);
				col.add(i, d);
			}
		}
		
		
		long sum = getSum(a,m,n);
		long prev = sum;
		Set<Pair> els = checkAndEliminate(rows, cols, m, n, null);
		while (!els.isEmpty()) {
			for (Pair p : els) {
				prev = prev - a[p.x][p.y];
			}
			sum = sum + prev;
			els = checkAndEliminate(rows, cols, m, n, els);
		}
		return sum;
	}

	private static Set<Pair> checkAndEliminate(List<Data> rows, List<Data> cols, int m, int n, Set<Pair> els) {
		Set<Pair> res = new HashSet<SquareDance.Pair>();
		if (els == null) {
			res = populateEls(rows,cols,m,n);
		} else {
			res = eliminateAndPopulateEls(rows,cols,m,n,els);
		}
		return res;
	}

	private static Set<Pair> eliminateAndPopulateEls(List<Data> rows, List<Data> cols, int m, int n, Set<Pair> els) {
		Set<Pair> res = new HashSet<SquareDance.Pair>();

		Set<Pair> candidates = new HashSet<>();
		for (Pair pr : els) {
			int r = pr.x, c = pr.y;
			Node row = rows.get(r).map.get(c), col = cols.get(c).map.get(r);

			if (row.prev != null) candidates.add(new Pair(r, row.prev.ind));
			if (row.next != null) candidates.add(new Pair(r, row.next.ind));
			if (col.prev != null) candidates.add(new Pair(col.prev.ind, c));
			if (col.next != null) candidates.add(new Pair(col.next.ind, c));
			
			rows.get(r).del(row);
			cols.get(c).del(col);
		}

		for (Pair p : candidates) {
			checkAndPopulate(p, rows, cols, res);
		}
		
		return res;
	}

	private static void checkAndPopulate(Pair p, List<Data> rows, List<Data> cols, Set<Pair> res) {
		int r = p.x, c = p.y;
		Node row = rows.get(r).map.get(c);
		double cnt = 0, sum = 0;
		//left
		if (row != null && row.prev != null) {
			sum = sum + row.prev.val;
			cnt++;
		}
		//right
		if (row != null && row.next != null) {
			sum = sum + row.next.val;
			cnt++;
		}
		
		Node col = cols.get(c).map.get(r);
		//up
		if (col != null && col.prev != null) {
			sum = sum + col.prev.val;
			cnt++;
		}
		//down
		if (col != null && col.next != null) {
			sum = sum + col.next.val;
			cnt++;
		}
		
		if (row != null && row.val < Math.ceil(sum/cnt)) {
			res.add(new Pair(r,c));
		}
	}

	private static Set<Pair> populateEls(List<Data> rows, List<Data> cols, int m, int n) {
		Set<Pair> res = new HashSet<SquareDance.Pair>();
		
		for (int i=0;i<rows.size();i++) {
			Data d = rows.get(i);
			Node temp = d.front;
			
			while (temp != null) {
				int r = i, c = temp.ind;
				double cnt = 0, sum = 0;
				//up
				if (r-1>=0) {
					int val = cols.get(c).map.get(r-1).val;
					sum = sum + val;
					cnt++;
				}
				//down
				if (r+1<m) {
					int val = cols.get(c).map.get(r+1).val;
					sum = sum + val;
					cnt++;
				}
				//left
				if (c-1>=0) {
					int val = rows.get(r).map.get(c-1).val;
					sum = sum + val;
					cnt++;
				}
				//right
				if (c+1<n) {
					int val = rows.get(r).map.get(c+1).val;
					sum = sum + val;
					cnt++;
				}
				if (temp.val < Math.ceil(sum/cnt)) {
					res.add(new Pair(r,c));
				}
				temp = temp.next;
			}
			
		}
		
		return res;
	}

	private static long getSum(int[][] a, int r, int c) {
		long sum = 0;
		for (int i=0;i<r;i++) {
			for (int j=0;j<c;j++) {
				sum = sum + a[i][j];
			}
		}
		return sum;
	}

	private static class Data {
		Node front, rear;
		Map<Integer, Node> map;
		private Data() {
			map = new LinkedHashMap<Integer, SquareDance.Node>();
		}
		
		private void add(int ind, int val) {
			Node temp = new Node(ind, val);
			if (front == null) {
				front = rear = temp;
			} else {
				rear.next = temp;
				temp.prev = rear;
				rear = temp;
			}
			map.put(ind, temp);
		}
		
		private void del(Node p) {
			if (front == null || rear == null || p == null) return;
			if (p.ind == front.ind) {
				front = front.next;
				if (front == null) rear = null;
				else front.prev = null;
			} else if (p.ind == rear.ind) {
				rear = rear.prev;
				if (rear == null) front = null;
				else rear.next = null;
			} else {
				Node next = p.next;
				Node prev = p.prev;
				prev.next = next;
				next.prev = prev;
			}
			map.remove(p.ind);
		}
	}
	
	private static class Node {
		int ind, val;
		Node next, prev;
		private Node(int ind, int val) {
			this.ind = ind;
			this.val = val;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof Node))
				return false;
			Node nd = (Node)o;
			return this.ind == nd.ind && this.val == nd.val;
		}
	}
	
	static class Pair {
		Integer x;
		Integer y;
		private Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof Pair))
				return false;
			Pair cor = (Pair)o;
			return x.equals(cor.x) && y.equals(cor.y);
		}
		
		@Override
		public int hashCode() {
			int result = 17;
			result = result + 31*x;
			result = result + 31*y;
			return result;
		}
		
		static class PairComparatorX implements Comparator<Pair> {

			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.x.compareTo(o2.x);
			}
			
		}

		static class PairComparatorY implements Comparator<Pair> {

			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.y.compareTo(o2.y);
			}
			
		}

	}

}