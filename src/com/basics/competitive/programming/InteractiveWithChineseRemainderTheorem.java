package com.basics.competitive.programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 * https://codingcompetitions.withgoogle.com/codejam/round/0000000000051635/0000000000104f1a
 * https://www.geeksforgeeks.org/chinese-remainder-theorem-set-2-implementation/
 */
public class InteractiveWithChineseRemainderTheorem {

	private static final Object SPACE = " ";

	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt(), n = in.nextInt(), m = in.nextInt();
		int t = 1;
		while (t <= T) {
			int div[] = {5,7,9,11,13,16,17};
			int rem[] = new int[n];
			int q[] = new int[18];
			//create div and rem
			for (int i=0;i<div.length;i++) {
				for (int j=0;j<q.length;j++) {
					q[j] = div[i];
				}
				ask(q);
				int sum = 0;
				for (int j=0;j<q.length;j++) {
					sum = (sum + in.nextInt())%div[i];
				}
				rem[i] = sum;
			}
			
			//find number using chinese remainder theorem
			int res = findMinX(div, rem, div.length);
			System.out.println(res);
			String ans = in.next();
			if (!"1".equals(ans)) break;
			t++;
		}
		in.close();
	}
	
	static int findMinX(int num[], int rem[], int k) 
    { 
        // Compute product of all numbers 
        int prod = 1; 
        for (int i = 0; i < k; i++) 
            prod *= num[i]; 
      
        // Initialize result 
        int result = 0; 
      
        // Apply above formula 
        for (int i = 0; i < k; i++) 
        { 
            int pp = prod / num[i]; 
            result += rem[i] * inv(pp, num[i]) * pp; 
        } 
      
        return result % prod; 
    } 
	
	static int inv(int a, int m) 
    { 
        int m0 = m, t, q; 
        int x0 = 0, x1 = 1; 
      
        if (m == 1) 
        return 0; 
      
        // Apply extended Euclid Algorithm 
        while (a > 1) 
        { 
            // q is quotient 
            q = a / m; 
      
            t = m; 
      
            // m is remainder now, process 
            // same as euclid's algo 
            m = a % m;a = t; 
      
            t = x0; 
      
            x0 = x1 - q * x0; 
      
            x1 = t; 
        } 
      
        // Make x1 positive 
        if (x1 < 0) 
        x1 += m0; 
      
        return x1; 
    } 
	
	private static void ask(int[] q) {
		StringBuilder sb = new StringBuilder();
		sb.append(q[0]);
		for (int i=1;i<q.length;i++) sb.append(SPACE).append(q[i]);
		System.out.println(sb);
	}
	
}