package com.basics.miscellaneous;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 *https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/0000000000209a9e
 */
public class InteractiveQuestion {

	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt();
		int b = in.nextInt();
		int t = 1;
		while (t <= T) {
			test2(in, b);
			String verdict = in.next();
			if (!verdict.equals("Y")) break;
			t++;
		}
		in.close();
	}

	private static void test2(Scanner in, int b) {
		int a[] = new int[b];
		Arrays.fill(a, -1);
		int same = -1, diff = -1;
		int sm[] = new int[2];
		int df[] = new int[2];
		
		int cnt = 0;
		for (int i=0;i<b/2;i++) {
			ask(i);
			int d = in.nextInt();
			a[i] = d;
			cnt++;
			
			ask(b-i-1);
			int e = in.nextInt();
			a[b-i-1] = e;
			cnt++;

			if (same == -1 && d == e) {
				same = i;
				sm[0] = sm[1] = d;
			}
			if (diff == -1 && d != e){
				diff = i;
				df[0] = d;
				df[1] = e;
			}

			if (cnt % 10 == 0) {
				update(in, a, same, diff, sm, df,i);
				if (diff == -1 || same == -1) {
					//ask dummy question to keep query count even
					//helps to keep code clean, else we need to add condition after ask(i)
					ask(0);
					d = in.nextInt();
				}
				cnt = cnt + 2;
			}			

		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<b;i++) sb.append(a[i]);
		System.out.println(sb);
	}

	private static void update(Scanner in, int[] a, int same, int diff, int[] sm, int[] df, int i) {
		Operation op = get(same, sm, diff, df, in);
		op.operate(a, i);
	}

	private static Operation get(int same, int[] sm, int diff, int[] df, Scanner in) {
		int d = -1, e = -1;
		if (same == -1) {
			ask(diff);
			d = in.nextInt();
			if (d == df[0]) return Operation.SAME;
			else {
				toggle(df);
				return Operation.COMPLEMENT;
			}
		}
		if (diff == -1) {
			ask(same);
			e = in.nextInt();
			if (e == sm[0]) return Operation.SAME;
			else {
				toggle(sm);
				return Operation.COMPLEMENT;
			}
		}
		//If both are present
		ask(diff);
		d = in.nextInt();
		ask(same);
		e = in.nextInt();
		
		if (e == sm[0]) {
			if (d == df[0]) return Operation.SAME;
			else {
				toggle(df);
				return Operation.REVERSE;
			}
		} else {
			toggle(sm);
			if (d == df[0]) return Operation.COMPREV;
			else {
				toggle(df);
				return Operation.COMPLEMENT;
			}
		}
	}

	private static void toggle(int[] ar) {
		ar[0] = 1 - ar[0];
		ar[1] = 1 - ar[1];
	}

	private static enum Operation {
		COMPLEMENT {
			@Override
			void operate(int a[], int n) {
				int m = a.length;
				for (int i=0;i<=n;i++) {
					a[i] = 1-a[i];
					a[m-i-1] = 1 - a[m-i-1];
				}
			}
		},
		REVERSE {
			@Override
			void operate(int a[], int n) {
				int m = a.length;
				for (int i=0;i<=n;i++) {
					int t = a[i];
					a[i] = a[m-i-1];
					a[m-i-1] = t;
				}
			}
		},
		COMPREV {
			@Override
			void operate(int a[], int n) {
				int m = a.length;
				for (int i=0;i<=n;i++) {
					a[i] = 1-a[i];
					a[m-i-1] = 1 - a[m-i-1];
					int t = a[i];
					a[i] = a[m-i-1];
					a[m-i-1] = t;
				}
			}
		},
		SAME {
			@Override
			void operate(int a[], int n) {
				//DO NOTHING
			}
		};
		
		abstract void operate(int a[], int n);
	}
	
	private static void ask(int i) {
		System.out.println(i+1);
	}
	
}