package com.basics.multithreading.basicthreadpool;

public class Task implements Runnable {
	
	@Override
	public void run() {
		try {
			Thread.sleep(4000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
