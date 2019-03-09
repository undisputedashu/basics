package com.basics.dp;
/**
 * https://www.geeksforgeeks.org/mobile-numeric-keypad-problem/
 * @author asasjha
 *
 */
public class MobileNumericKeypad {

    public static void main(String args[]) {
        int a[][] = {
                        {1,2,3},
                        {4,5,6},
                        {7,8,9},
                        {-1,0,-1}
                    };
        int m = a.length;
        int n = a[0].length;
        
        int sum = 0;
        int k = 2;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                int s = cnt(a,i,j,m,n, k-1);
                sum = sum + s;
            }
        }
        System.out.println(sum);
    }

    private static int cnt(int a[][], int i, int j, int m, int n, int k) {
        if (i>=m || i<0 || j>=n || j<0) return 0; 
        if (a[i][j] < 0) return 0;
        if (k == 0) return 1;
        
        
        return cnt(a,i+1,j,m,n,k-1) +
                cnt(a,i-1,j,m,n,k-1) +
                cnt(a,i,j+1,m,n,k-1) +
                cnt(a,i,j-1,m,n,k-1) +
                cnt(a,i,j,m,n,k-1);
    }
    
}
