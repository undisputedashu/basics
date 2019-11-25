package com.basics.multithreading;

import java.util.ArrayList;

public class ProducerConsumer {

	public static void main(String args[]) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Thread p = new Thread(new Producer(list, 5), "Producer");
		Thread c = new Thread(new Consumer(list), "Consumer");

		p.start();
		c.start();
	}

	private static class Producer implements Runnable {

		private ArrayList<Integer> list;
		int max;

		Producer(ArrayList<Integer> list, int max) {
			this.list = list;
			this.max = max;
		}

		public void run() {
			synchronized (list) {
				while (true) {
						try {
							if (this.list.size() >= max)
							list.wait();
							list.add(list.size() + 1);
							System.out.println(list.size() + "  " + Thread.currentThread().getName());
							list.notify();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}

			}
		}
	}

	private static class Consumer implements Runnable {

		private ArrayList<Integer> list;

		Consumer(ArrayList<Integer> list) {
			this.list = list;
		}

		public void run() {
			synchronized (list) {
				while (true) {
						try {
							if (list.size() <= 0)
							list.wait();
							list.remove(0);
							System.out.println(list.size() + "  " + Thread.currentThread().getName());
							list.notify();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
				}
					
			}
		}
	}

}