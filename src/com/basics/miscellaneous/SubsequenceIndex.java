package com.basics.miscellaneous;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * https://codeforces.com/contest/1399/problem/D
 */
public class SubsequenceIndex {

	public static void main(String args[]) throws IOException {
//		Reader in = new Reader();
		FastReader in = new FastReader();
		int t = in.nextInt();
		while (t-->0) {
			StringBuilder sb = new StringBuilder();
			int n = in.nextInt();
			String s = in.next();
			
			int a[] = new int[n];
			Arrays.fill(a, 0);
			
			int col = 1;
			Deque<Integer> zero = new LinkedList<Integer>();
			Deque<Integer> one = new LinkedList<Integer>();
			for (int i=0;i<n;i++) {
				int x = s.charAt(i)-'0';
				if (x == 0) {
					zero.addLast(i);
					if (one.isEmpty()) {
						a[i] = col++;
					} else {
						a[i] = a[one.poll()];
					}
				} else {
					one.addLast(i);
					if (zero.isEmpty()) {
						a[i] = col++;
					} else {
						a[i] = a[zero.poll()];
					}
				}
			}
			
			sb.append(col-1).append("\n");
			for (int i=0;i<n;i++) sb.append(a[i]).append(" ");
			System.out.println(sb);
		}
		
	}

	static class Pair {
		Long x;
		Long y;

		private Pair(Long x, Long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof Pair))
				return false;
			Pair cor = (Pair) o;
			return x.equals(cor.x) && y.equals(cor.y);
		}

		@Override
		public int hashCode() {
			int result = 17;
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
		/**
		 * When reading millions of strings try using FastReader if getting tle
		 */
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;
		Scanner in = new Scanner(System.in);

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

		String next() {
			return in.next();
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
			if (in == null) 
				return;
			in.close();
		}

	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}

}