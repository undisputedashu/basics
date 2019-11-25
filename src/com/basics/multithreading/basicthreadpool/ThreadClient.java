package com.basics.multithreading.basicthreadpool;

public class ThreadClient {
	
	public static void main(String[] args) throws Exception {
		ThreadPool threadPool = new ThreadPool(3,1);
		threadPool.execute(new Task());
		threadPool.execute(new Task());
		threadPool.execute(new Task());
	}
	
}