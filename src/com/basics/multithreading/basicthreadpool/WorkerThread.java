package com.basics.multithreading.basicthreadpool;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {

	private BlockingQueue<Runnable> taskQueue;
	private Boolean isStopped;
	
	public WorkerThread(BlockingQueue<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void run() {
		while (!isStopped) {
			try {
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