package com.basics.multithreading.basicthreadpool;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {

	private BlockingQueue<Runnable> taskQueue;
	private Boolean isStopped = false;
	
	public WorkerThread(BlockingQueue<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void run() {
		while (!isStopped) {
			try {
				System.out.println(Thread.currentThread().getName());
				Runnable task = (Runnable) taskQueue.take();
				task.run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void doStop() {
		isStopped = true;
		this.interrupt();
	}
	
}