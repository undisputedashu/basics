package com.basics.string;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/0000000000209a9f
 */
public  class NestingDepth {

	private static final String NEWLINE = "\n";
	private static final String SPACE = " ";
	private static final String COLON = ":";
	private static final String CASE = "Case #";

	public static void main(String args[]) throws IOException {
//		Reader in = new Reader();
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int t = 1;
		StringBuilder sb = new StringBuilder();
		while (t <= T) {
			String s = in.next();
			String res = get(s);
			sb.append(CASE).append(t).append(COLON).append(SPACE).append(res).append(NEWLINE);
			t++;
		}
		System.out.println(sb);
		in.close();
	}

	private static String get(String s) {
		StringBuilder sb = new StringBuilder();
		int n = s.length();
		int l = 0;
		int curr = 0;
		while (l<n) {
			char c = s.charAt(l);
			int x = c-'0';
			while(curr<x) {
				sb.append("(");
				curr++;
			}
			while (curr>x) {
				sb.append(")");
				curr--;
			}
			sb.append(c);
			l++;
		}
		while (curr > 0) {
			sb.append(")"); 
			curr--;
		}
		return sb.toString();
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