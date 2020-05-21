package com.basics.advance.datastructure;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * https://codeforces.com/blog/entry/61364
 * https://codeforces.com/contest/1354/problem/D
 * @author Lenovo
 *
 */
public class BinaryIndexedTreeWithBinaryLifting {

	public static void main(String args[]) throws IOException {
		Reader in = new Reader();
		solve(in);
	}
 
	private static void solve(Reader in) throws IOException {
		int n = in.nextInt(), q = in.nextInt();
		int a[] = new int[n+1];
		Arrays.fill(a, 0);
		for (int i=0;i<n;i++) {
			int d = in.nextInt();
			a[d]++;
		}
		BinaryIndexedTree bit = new BinaryIndexedTree();
		bit.constructBITree(a, n+1);
		
		for (int i=0;i<q;i++) {
			int ind = in.nextInt();
			if (ind>0) {
				bit.updateBIT(n+1, ind, 1);
			} else {
				ind = Math.abs(ind);
				int index = bit.search(ind, a.length);
				bit.updateBIT(a.length, index, -1);
			}
		}
		
		if (bit.getSum(n) == 0) {
			System.out.println(0);
		} else {
			System.out.println(bit.search(1, n));
		}
	}
 
	static class BinaryIndexedTree {

		final static int MAX = 1048576;
		static int bit[] = new int[MAX];

		int search(int sum, int n) {
			int x = 0;
			for(int i = 22; i >= 0; i--) {
				int newX = x + (1 << i);
				if(newX <= n && sum > bit[newX]) {
					x = newX;
					sum -= bit[x];
				}
			}
			//returning x as while updating, updae code is incrementing index by 1
			//ideally x+1 should be returned
			return x;
		}
		
		int getSum(int index) {
			int sum = 0;
			for (int i=index+1;i>0;i-=i&(-i)) {
				sum = sum + bit[i];
			}
			return sum;
		}

		void constructBITree(int arr[], int n) {
			for (int i = 1; i <= n; i++)
				bit[i] = 0;
			for (int i = 0; i < n; i++)
				updateBIT(n, i, arr[i]);
		}

		public void updateBIT(int n, int index, int val) {
			for (int i=index+1;i<=n;i+=i&(-i)) {
				bit[i] = bit[i] + val;
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