package com.basics.competitive.programming;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
/**
 * https://codingcompetitions.withgoogle.com/codejam/round/000000000019fef2/00000000002d5b62
 * Think about tradeoff with dfs.
 */
public class BreadthFirstSearch {

	private static final String SPACE = " ";
	private static final String COLON = ":";
	private static final String CASE = "Case #";

	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt();
		int t = 1;
		while (t <= T) {
			StringBuilder sb = new StringBuilder();
			int x = in.nextInt(); 
			int y = in.nextInt();
			sb.append(CASE).append(t).append(COLON).append(SPACE);
			String res = getPathFromBfs(x, y);
			sb.append(res);
			System.out.println(sb);
			t++;
		}
		in.close();
	}

	private static String getPathFromBfs(int x, int y) {
		int n = 101;
		Queue<Data> qu = new LinkedList<BreadthFirstSearch.Data>();
		qu.add(new Data("", new Pair(0,0), 0));
		
		while (!qu.isEmpty()) {
			Data dt = qu.poll();
			Pair pr = dt.pr;
			int xi = pr.x, yi = pr.y;
			if (xi == x && yi == y) return dt.path;
			int d = (int)(Math.pow(2, dt.step));
			//east
			Pair p = new Pair(xi+d, yi);
			if (Math.abs(xi+d)<n && Math.abs(yi)<n) {
				String path = dt.path + "E";
				qu.add(new Data(path, p, dt.step+1));
			}
			//west
			p = new Pair(xi-d, yi);
			if (Math.abs(xi-d)<n && Math.abs(yi)<n) {
				String path = dt.path + "W";
				qu.add(new Data(path, p, dt.step+1));
			}
			//north
			p = new Pair(xi, yi+d);
			if (Math.abs(xi)<n && Math.abs(yi+d)<n) {
				String path = dt.path + "N";
				qu.add(new Data(path, p, dt.step+1));
			}
			//south
			p = new Pair(xi, yi-d);
			if (Math.abs(xi)<n && Math.abs(yi-d)<n) {
				String path = dt.path + "S";
				qu.add(new Data(path, p, dt.step+1));
			}
		}
		
		return "IMPOSSIBLE";
	}

	private static class Data {
		String path;
		Pair pr;
		int step;
		Data(String path, Pair pr, int step) {
			this.path = path;
			this.pr = pr;
			this.step = step;
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
	
	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

}