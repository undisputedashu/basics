package com.basics.arrays;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/stock-buy-and-sell/0
 *
 * @author asasjha
 */
public class StockSellMultiple {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int a[] = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            maxProfit(a, n);
        }

        in.close();
    }

    private static void maxProfit(int[] a, int n) {
        if (n <= 1) {
            System.out.println(0);
            return;
        }

        int s = 0, e = 1;
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        while (e < n) {
            if (a[e] > a[s]) {
                while (e + 1 < n && a[e] < a[e + 1]) e++;
                map.put(s, e);
                s = e + 1;
            } else s = e;
            e++;
        }

        if (map.isEmpty()) System.out.println("No Profit");
        else {
            StringBuilder sb = new StringBuilder();
            for (Entry<Integer, Integer> en : map.entrySet()) {
                sb.append("(").append(en.getKey()).append(" ").append(en.getValue()).append(")").append(" ");
            }
            System.out.println(sb);
        }

    }

    private static ArrayList<ArrayList<Integer>> stockBuySell(int a[], int n) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        int s = 0, e = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > a[e]) e = i;
            if (a[i] < a[e] || i == n - 1) {
                if (a[e] > a[s]) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(s);
                    list.add(e);
                    lists.add(list);
                }
                s = e = i;
            }
        }
        return lists;
    }

}
