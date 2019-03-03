package com.basics.arrays;

/**
 * https://www.geeksforgeeks.org/counting-inversions/
 * @author asasjha
 *
 */
public class NumberOfInversions {

    public static void main(String args[]) {
        int a[] = {2, 7, 1, 3, 5};
        int n = a.length;
        int t[] = new int[n];
        int inv = ms(a,t,0,n-1);
        System.out.println(inv);
    }

    private static int ms(int[] a, int[] t, int lb, int rb) {
        int inv = 0;
        if (lb<rb) {
            int mid = lb + (rb-lb)/2;
            inv = ms(a,t,lb,mid);
            inv+= ms(a,t,mid+1,rb);
            inv+= merge(a,t,lb,mid,rb);
        }
        return inv;
    }

    private static int merge(int[] a, int[] t, int beg, int mid, int end) {
        int lb = beg, rb = mid+1, tb = beg;
        int inv = 0;
        while (lb<=mid && rb<=end) {
            if (a[lb] <= a[rb]) t[tb++] = a[lb++];
            else {
                inv = inv + mid-lb+1;
                t[tb++] = a[rb++];
            }
        }
        while(lb<=mid) t[tb++] = a[lb++];
        while(rb<=end) t[tb++] = a[rb++];
        for (int i=beg;i<=end;i++) a[i] = t[i];
        return inv;
    }
    
}