package com.basics.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https://www.geeksforgeeks.org/stock-buy-sell/
 * @author asasjha
 *
 */
public class StockSellMultiple {

    public static void main(String args[]) {
        int a[] = {100, 180, 260, 310, 40, 535, 695};
        printMaxProfit(a);
    }

	private static void printMaxProfit(int[] a) {
        int maxDiff = 0;
        int minIndex = 0, sellIndex = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i=1;i<a.length;i++) {
            int diff = a[i] - a[minIndex];
            if (diff > maxDiff) {
                maxDiff = diff;
                sellIndex = i;
                map.put(minIndex, sellIndex);
            }
            if (a[i] < a[minIndex]) {
                minIndex = i;
                maxDiff = 0;
            }
        }
        
        for (Entry<Integer, Integer> e:map.entrySet()) {
            System.out.println(e.getKey() + "  " + e.getValue());
        }
    }

	private static void printMaxProfit2(int[] a) {
		int n = a.length;
		int maxDiff = 0, l = 0, r = 0;
		for (int i=1;i<n;i++) {
			int diff = a[i] - a[l];
			if (diff > maxDiff) {
				maxDiff = diff;
				r = i;
			} else if (a[i] < a[i-1]) {
				if (maxDiff > 0) System.out.println(l + "  " + r);
				l = i;
				r = i;
				maxDiff = 0;
			}
		}
		
		if (l != r) System.out.println(l + "  " + r);
	}
    
}
