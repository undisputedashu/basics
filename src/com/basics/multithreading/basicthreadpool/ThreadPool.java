
package com.basics.multithreading.basicthreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue = null;
    private List<WorkerThread> threads = new ArrayList<WorkerThread>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads, int maxNoOfTasks){
        taskQueue = new ArrayBlockingQueue<Runnable>(maxNoOfTasks);

        for(int i=0; i<noOfThreads; i++){
            threads.add(new WorkerThread(taskQueue));
        }
        for(WorkerThread thread : threads){
            thread.start();
        }
    }

    public synchronized void  execute(Runnable task) throws Exception{
        if(this.isStopped) throw
            new IllegalStateException("ThreadPool is stopped");
        this.taskQueue.put(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(WorkerThread thread : threads){
           thread.doStop();
        }
    }
		
}