package com.basics.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 
t = 1
n,k = 4,5
a[] = 1 2 2 8
1 2 2 has sum 5 divisible by 5, length of subarray 3
2 8 has sum 10 divisible by 5 , length of subarray 2
so ans is 2

return -1 if there is no subarray with sum divisible by k
 */
public class MinimumSubArrayWithSumDivisibleByK {
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- > 0) {
			int n = in.nextInt();
			int k = in.nextInt();
			int a[] = new int[n];
			for (int i=0;i<n;i++) a[i] = in.nextInt();
			int ans = optimalSolution(n, k, a);
			System.out.println(ans);
		}
		in.close();
	}
	
	//O(n)
    private static int optimalSolution(int n, int k, int[] a) {
	if (n == 0) return -1;
        if (a[0] % k == 0) return 1;
	
    	long t[] = new long[n];
    	t[0] = a[0];
    	for (int i=1;i<n;i++) {
            t[i] = a[i] + t[i-1];
            if (t[i]%k == 0) min = Math.min(min, i+1);
        }
    	
    	int min = Integer.MAX_VALUE;
    	Map<Long, Integer> map = new HashMap<Long, Integer>();
    	for (int i=0;i<n;i++) {
    		t[i] = t[i] % k;
    		int diff = Integer.MAX_VALUE;
    		if (map.containsKey(t[i])) {
    			diff = i - map.get(t[i]);
    			if (diff < min) min = diff;
    		}
    		map.put(t[i], i);
    	}
	
    	if (min == Integer.MAX_VALUE) return -1;
	
	return min;
   }

    //O(n^2)
   private static int subOptimalSolution(int a[], int n, int k) {
	        if (n == 0) return -1;
	        if (a[0] % k == 0) return 1;
	        
	        int min = Integer.MAX_VALUE;
	        int t[] = new int[n];
	        t[0] = a[0];
	        for (int i=1;i<n;i++) {
	            t[i] = a[i] + t[i-1];
	            if (t[i]%k == 0) min = Math.min(min, i+1);
	        }
	        
	        for (int i=0;i<n;i++) {
	            for (int j=i+1;j<n;j++) {
	                int diff = t[j] - t[i];
	                if (diff % k == 0) min = Math.min(min, j-i);
	            }
	        }
	        
	        return min == Integer.MAX_VALUE ? -1 : min;
    }

}
