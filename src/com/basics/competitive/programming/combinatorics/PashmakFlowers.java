package com.basics.competitive.programming.combinatorics;

import java.util.Arrays;
import java.util.Scanner;

public class PashmakFlowers {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-->0) {
			int n = in.nextInt(), k = in.nextInt();
			long a[] = new long[n];
			long pref[] = new long[n];
			for (int i=0;i<n;i++) {
				a[i] = in.nextLong();
				if (i == 0) pref[i] = a[i];
				else pref[i] = a[i] + pref[i-1];
			}
			
			//process k queries
			int d[] = new int[n];
			Arrays.fill(d, 0);
			long sum = 0;
			for (int i=0;i<k;i++) {
				int x = in.nextInt(), y = in.nextInt();
				x--;y--;
				//in your first submission don't use prefix array
				//calculate sum inside j loop using sum =  sum + a[j];
				//it will give tle. then make second using pref[]
				if (x == 0) sum = sum + pref[y];
				else sum = sum + pref[y]-pref[x-1];
				for (int j=x;j<=y;j++) {
					d[j]++;
				}
			}
			
			//get max sum
			Arrays.parallelSort(d);
			Arrays.parallelSort(a);
			long max = 0;
			for (int i=n-1;i>=0 && d[i]>0;i--) {
				max = max + a[i]*d[i];
			}
			
			//answer is difference between max and sum
			long diff = max - sum;
			System.out.println(diff);
		}
		in.close();
	}
	
}