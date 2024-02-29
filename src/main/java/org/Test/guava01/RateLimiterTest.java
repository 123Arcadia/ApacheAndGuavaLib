package org.Test.guava01;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * RateLimiter 限速器/限流器
 *
 * final RateLimiter rateLimiter = RateLimiter.create(2.0); // rate is "2 permits per second"
 *     void submitTasks(List<Runnable> tasks, Executor executor) {
 *         for (Runnable task : tasks) {
 *             rateLimiter.acquire(); // may wait
 *             executor.execute(task);
 *         }
 *     }
 */
public class RateLimiterTest {


    /**
     * 一部分像是信号量Semaphore，但是可以调整速率
     */
    @Test
    public void testRateLimiter1() {
        RateLimiter rateLimiter = RateLimiter.create(1);

        Instant start = Instant.now();
        for (int i = 0, max = 10; i < max; i++) {
            int finalI = i;
            new Thread(() -> {
                // boolean tryAcquire = rateLimiter.tryAcquire();
                Thread.currentThread().setName("线程" + finalI);
                String threadName = Thread.currentThread().getName();
                try {
                    boolean tryAcquire = rateLimiter.tryAcquire(Duration.ofSeconds(2));
                    if (tryAcquire) {
                        System.out.println(finalI +", 获得许可证，执行任务，当前速率：" + rateLimiter.getRate() + "/s ------" + threadName);
                    } else {
                        System.out.println(finalI +", 未获得许可证，无法执行任务，当前速率：" + rateLimiter.getRate() + "/s -------" + threadName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Instant end = Instant.now();
        System.out.println("耗时:" + Duration.between(start, end).toMillis()  + "ms");
    }


    @Test
    public void testRateLimiter2() {
        RateLimiter rateLimiter = RateLimiter.create(1);
        for (int i = 0, max = 10; i < max; i++) {
            int finalI = i;
            new Thread(() -> {
                double acquire = rateLimiter.acquire();
                System.out.println(finalI + ", 获得许可证，执行任务，当前速率："+rateLimiter.getRate()+"/s 等待时间: "+acquire);
            }).start();
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 2023-11-04 16:57:34.480 ==> [Thread-1] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 0.0
        // 2023-11-04 16:57:35.417 ==> [Thread-2] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 0.193155
        // 2023-11-04 16:57:36.417 ==> [Thread-3] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 0.609643
        // 2023-11-04 16:57:37.416 ==> [Thread-4] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 1.251649
        // 2023-11-04 16:57:38.415 ==> [Thread-5] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 1.908182
        // 2023-11-04 16:57:39.416 ==> [Thread-6] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 2.365081
        // 2023-11-04 16:57:40.416 ==> [Thread-7] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 3.319103
        // 2023-11-04 16:57:41.416 ==> [Thread-8] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 3.349201
        // 2023-11-04 16:57:42.416 ==> [Thread-9] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 3.391127
        // 2023-11-04 16:57:43.417 ==> [Thread-10] ==> INFO  com.wmx.guava.RateLimiterTest - 获得许可证，执行任务，当前速率：1.0/s 等待时间 3.91561

    }

}
