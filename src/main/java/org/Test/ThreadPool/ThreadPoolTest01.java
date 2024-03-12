package org.Test.ThreadPool;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolTest01 {

    @Test
    public void test01() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10
                , 10
                , 30
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue(100)
                , Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy());

        threadPool.prestartAllCoreThreads();
        int activeCount = threadPool.getActiveCount();
        try {
            Thread.sleep(100*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("activeCount = " + activeCount);
        SecurityManager s = System.getSecurityManager();
       ThreadGroup group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        System.out.println("group = " + group);
        System.out.println(threadPool.getActiveCount());
        //activeCount = 8
        //group = java.lang.ThreadGroup[name=main,maxpri=10]
        //0
        System.out.println("threadPool = " + threadPool);

        threadPool.execute(() -> {
            try {
                Thread.sleep(100*3);
                System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getThreadGroup());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("===================");
        ThreadPoolExecutor threadPool2 = new ThreadPoolExecutor(10
                , 10
                , 30
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue(100)
                , Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy());
        threadPool2.execute(() -> {
            try {
                Thread.sleep(100*3);
                System.out.println(Thread.currentThread().getName()+ "<," + Thread.currentThread().getId() + "->" + Thread.currentThread().getThreadGroup() ) ;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("threadPool2 = " + threadPool2);
        try {
            Thread.sleep(1000*3);
            System.out.println(Thread.currentThread().getName()+ "<," + Thread.currentThread().getId() + "->" + Thread.currentThread().getThreadGroup() ) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // poolNumber: 线程池数量, 后面是该线程池的线程数量(不是该线程id)
        //pool-1-thread-1,java.lang.ThreadGroup[name=main,maxpri=10]
        //pool-2-thread-1,java.lang.ThreadGroup[name=main,maxpri=10]


    }
}
