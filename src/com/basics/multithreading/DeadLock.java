package com.basics.multithreading;


public class DeadLock {
	
	public static void main(String args[]) {
		String r1 = "abc", r2 = "bcd";
		Thread t1 = new Thread(new T1(r1, r2), "T1-td");
		Thread t2 = new Thread(new T2(r1, r2), "T2-td");
		t1.start();
		t2.start();
	}
	
	private static class T1 implements Runnable {
		String r1,r2;
		private T1(String r1, String r2) {
			this.r1 = r1;
			this.r2 = r2;
		}
		
		@Override
		public void run() {
			synchronized (r1) {
				System.out.println("Acquired lock on r1 by: " + Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (r2) {
					System.out.println("Acquired lock on r2 by: " + Thread.currentThread().getName());
				}
			}
		}
		
	}
	
	private static class T2 implements Runnable {
		String r1,r2;
		private T2(String r1, String r2) {
			this.r1 = r1;
			this.r2 = r2;
		}
		
		@Override
		public void run() {
			synchronized (r2) {
				System.out.println("Acquired lock on r2 by: " + Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (r1) {
					System.out.println("Acquired lock on r1 by: " + Thread.currentThread().getName());
				}
			}
		}
		
	}
}