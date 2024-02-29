package org.Test.guava01;

import cn.hutool.core.util.RandomUtil;
import com.google.common.util.concurrent.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Guava的ListenableFuture顾名思义就是可以监听的Future，是对java原生Future的扩展增强。<p>
 * 我们知道Future表示一个异步计算任务，当任务完成时可以得到计算结果。如果我们希望一旦计算完成就拿到结果展示给用户或者做另外的计算，<p>
 * 就必须使用另一个线程不断的查询计算状态。这样做，代码复杂，而且效率低下。<p>
 * 使用Guava ListenableFuture可以帮我们检测Future是否完成了，不需要再通过get()方法苦苦等待异步的计算结果，<p>
 * 如果完成就自动调用回调函数，这样可以减少并发程序的复杂度。<p>
 * <p>
 * ListenableFuture是一个接口，它从jdk的Future接口继承，添加了void addListener(Runnable listener, Executor executor)方法。<p>
 */
public class ListenableFutureTest {

    /**
     * 不太稳定
     * @throws InterruptedException
     */
    @Test
    public void listenableFutureTest1() throws InterruptedException {
        // 原生线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 通过MoreExecutors类的静态方法listeningDecorator方法初始化一个ListeningExecutorService实例(侦听执行程序服务)
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        // ListeningExecutorService实例的submit方法即可初始化ListenableFuture对象(委托执行器，用于将来监听执行结果)。
        final ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 200;
                int randomInt = RandomUtil.randomInt(500, 3000);
                //模拟等待
                System.out.println("支线任务开始，预计执行{}毫秒."+ randomInt);
                TimeUnit.MILLISECONDS.sleep(randomInt);
                System.out.println("支线任务完成，准备返回结果{}"+ result);
                // System.out.println(1 / 0);
                return result;
            }
        });

        // 添加回调方法监听异步执行的结果(如果没有监听，则子线程发生异常后并不会打印异常信息)
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {

            // 子线程异步执行成功结束后触发
            @Override
            public void onSuccess(@Nullable Integer result) {
                System.out.println("支线任务执行完成，返回结果：" + result);
            }

            // 子线程异步执行异常后触发
            @Override
            public void onFailure(Throwable t) {
                System.out.println("支线任务执行失败：" + ExceptionUtils.getStackTrace(t));
            }
        }, listeningExecutorService);


        TimeUnit.MILLISECONDS.sleep(5000);
        System.out.println("Fined!");
    }

}
