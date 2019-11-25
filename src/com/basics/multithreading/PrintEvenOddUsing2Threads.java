package com.basics.multithreading;

public class PrintEvenOddUsing2Threads {

	public static void main(String args[]) {
		Print print = new Print();
		Thread t1 = new Thread(new Threads(true, print));
		Thread t2 = new Thread(new Threads(false, print));
		
		t1.start();
		t2.start();
	}
	
	private static class Threads implements Runnable {
		boolean isOdd;
		Print print;
		Threads(boolean isOdd, Print print) {
			this.isOdd = isOdd;
			this.print = print;
		}
		
		public void run() {
			int m = isOdd ? 1 : 2;
			while (m<=10) {
				print.print(m, isOdd);
				m = m+2;
			}
		}
		
	}
	
	private static class Print {
		private boolean isOdd = true;
		
		public synchronized void print(int n, boolean turn) {
				try {
					if (isOdd != turn) wait();
					System.out.println(n + "  " + Thread.currentThread().getName());
					isOdd = !isOdd;
					notify();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
}