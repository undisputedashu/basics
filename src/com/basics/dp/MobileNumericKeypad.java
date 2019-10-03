package com.basics.dp;
/**
 * https://www.geeksforgeeks.org/mobile-numeric-keypad-problem/
 * @author asasjha
 *
 */
public class MobileNumericKeypad {

//	Count for numbers of length 1: 10
//	Count for numbers of length 2: 36
//	Count for numbers of length 3: 138
//	Count for numbers of length 4: 532
//	Count for numbers of length 5: 2062
	
    public static void main(String args[]) {
    	String a[][] = {
				{ "1", "2", "3" },
				{ "4", "5", "6" },
				{ "7", "8", "9" },
				{ "*", "0", "#" }

		};
        int m = a.length;
        int n = a[0].length;
        
        int sum = 0;
        int k = 8;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                int s = cnt(a,i,j, k);
                sum = sum + s;
            }
        }
        System.out.println(sum);
        System.out.println("3d dp solution::");
        cnt3dDp(a,k);
    }

    private static void cnt3dDp(String[][] a, int K) {
    	int m = a.length, n = a[0].length;
		int t[][][] = new int[K][m][n];
		
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				t[0][i][j] = isValid(a[i][j]) ? 1 : 0;
			}
		}
		
		for (int k = 1; k < K; k++) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (!isValid(a[i][j])) {
						continue;
					}
					t[k][i][j] = t[k-1][i][j];
					if (i - 1 >= 0)
						t[k][i][j] = t[k][i][j] + t[k - 1][i - 1][j];
					if (i + 1 < m)
						t[k][i][j] = t[k][i][j] + t[k - 1][i + 1][j];
					if (j - 1 >= 0)
						t[k][i][j] = t[k][i][j] + t[k - 1][i][j - 1];
					if (j + 1 < n)
						t[k][i][j] = t[k][i][j] + t[k - 1][i][j + 1];
				}
			}
		}
		
		int sum = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sum = sum + t[K-1][i][j];
			}
		}
		
		System.out.println(sum);
    }

	private static int cnt(String a[][], int i, int j, int k) {
    	int m = a.length, n = a[0].length;
        if (i>=m || i<0 || j>=n || j<0) return 0; 
        if (!isValid(a[i][j])) return 0;
        
        if (k == 1) return 1;
        
        return cnt(a,i+1,j,k-1) +
                cnt(a,i-1,j,k-1) +
                cnt(a,i,j+1,k-1) +
                cnt(a,i,j-1,k-1) +
                cnt(a,i,j,k-1);
    }
    
    private static boolean isValid(String s) {
		return !(s.equals("*") || s.equals("#"));
	}
    
}
