package com.basics.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FindSumArrayUsingCallable {

	public static void main(String args[]) throws InterruptedException, ExecutionException {
		int n = 10;
		int a[] = new int[n];
		long s = 0;
		for (int i=0;i<n;i++) {
			a[i] = (int) (1 + Math.random()*10);
			System.out.println(a[i]);
			s = s + a[i];
		}
		System.out.println("Sum = " + s);
		
		//Doing in parallel way
		int processors = Runtime.getRuntime().availableProcessors();
		int size = n/processors;
		if (n%processors != 0) size++;
		//If there was I/O we could have used more threads
		List<Callable<Long>> callables = new ArrayList<Callable<Long>>();
		ExecutorService executor = Executors.newFixedThreadPool(processors);
		int l = 0;
		for (int i=0;i<processors;i++) {
			if (l>=n) break;
			Sum sum = new Sum(a, l, l+size <= n ? l+size : n);
			callables.add(sum);
			l = l+size;
		}
		
		List<Future<Long>> futs = executor.invokeAll(callables);
		long sum = 0;
		for (Future<Long> f:futs) {
			sum = sum + f.get();
		}
		System.out.println("Sum = " + sum);
		executor.shutdown();
	}
	
	private static class Sum implements Callable<Long> {
		int a[], l, r;
		private Sum(int a[], int l, int r) {
			this.a = a;
			this.l = l;
			this.r = r;
		}
		
		@Override
		public Long call() throws Exception {
			long sum = 0;
			for (int i=l;i<r;i++) sum = sum + a[i];
			return sum;
		}
		
	}
	
}