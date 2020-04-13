package com.basics.string;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd74/00000000002b3034
 */
public class PatternMatching {

	private static final String STAR = "*";
	private static final String NEWLINE = "\n";
	private static final String SPACE = " ";
	private static final String COLON = ":";
	private static final String CASE = "Case #";

	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt();
		int t = 1;
		StringBuilder sb = new StringBuilder();
		while (t <= T) {
			int n = in.nextInt();
			String s[] = new String[n];
			for (int i=0;i<n;i++) {
				s[i] = in.next();
			}
			String res = get(s);
			sb.append(CASE).append(t).append(COLON).append(SPACE).append(res).append(NEWLINE);
			t++;
		}
		System.out.print(sb);
		in.close();
	}

	private static String get(String[] s) {
		int n = s.length;
		String lp = "", ls = "";
		for (int i=0;i<n;i++) {
			lp = getLp(s[i], lp);
			if (STAR.equals(lp)) return STAR;
			
			ls = getLs(s[i], ls);
			if (STAR.equals(ls)) return STAR;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(lp);
		for (int i=0;i<n;i++) {
			int fi = s[i].indexOf('*');
			int li = s[i].lastIndexOf('*');
			if (fi == li) continue;
			for (int j=fi+1;j<li;j++) {
				char ch = s[i].charAt(j);
				if (ch != '*') sb.append(ch);
			}
		}
		sb.append(ls);
		
		return sb.toString();
	}

	private static String getLs(String s, String ls) {
		int m = s.length(), l = m-1;
		while (l>=0 && s.charAt(l) != '*') {
			l--;
		}
		
		if (l == m-1) return ls;
		
		int n = ls.length();
		int p = m-1, q = n-1;
		while (p>l && q>=0) {
			if (s.charAt(p) != ls.charAt(q)) return STAR;
			p--;
			q--;
		}
		
		if (m-l>n) return s.substring(l+1);
		return ls;
	}

	private static String getLp(String s, String lp) {
		int m = s.length(), l = 0;
		while (l<m && s.charAt(l) != '*') {
			l++;
		}
		
		if (l == 0) return lp;
		
		int n = lp.length();
		int p = 0, q = 0;
		while (p<l && q<n) {
			if (s.charAt(p) != lp.charAt(q)) return STAR;
			p++;
			q++;
		}
		
		if (l>n) return s.substring(0, l);
		return lp;
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