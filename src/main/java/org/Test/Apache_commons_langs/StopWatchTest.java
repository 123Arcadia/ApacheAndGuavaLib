package org.Test.Apache_commons_langs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Apache commons lang3 秒表 API StopWatch，用于计时，此类不是线程安全的
 */
@Slf4j
public class StopWatchTest {

    /**
     * 秒表使用入门
     *
     * 除非第一次使用，否则start()之前都需要reset()
     *
     * @throws InterruptedException
     */
    @Test
    public void testStopWatch1() throws InterruptedException {
        StopWatch stopwatch = StopWatch.createStarted();

        int nextInt = new SecureRandom().nextInt(4000);
        //2354
        System.out.println(nextInt);
        Thread.sleep(nextInt);

        stopwatch.stop();
        //方法执行结束，耗时：00:00:02.895
        System.out.println("方法执行结束，耗时：" + stopwatch.toString());
        System.out.println(stopwatch.getTime(TimeUnit.MILLISECONDS));
    }

    /**
     * 使用秒表统计大方法中各个子方法的执行时间
     *
     * @throws InterruptedException
     */
    @Test
    public void testStopWatch2() throws InterruptedException {
//        StopWatch stopwatch_total = StopWatch.createStarted();
        StopWatch stopwatch_total = StopWatch.createStarted();
        StopWatch stopwatch = StopWatch.createStarted();
        // 这里已经秒表开始了start()
        List<Long> res = new ArrayList<>();

        SecureRandom secureRandom = new SecureRandom();

        int nextInt = secureRandom.nextInt(2000);
        System.out.println("任务1：" + nextInt);
        Thread.sleep(nextInt);
        System.out.println("任务1耗时" + stopwatch.toString());
        res.add(stopwatch.getTime(TimeUnit.MILLISECONDS));
        stopwatch.reset();
        stopwatch.start();

        nextInt = secureRandom.nextInt(2000);
        System.out.println("任务2：" + nextInt);
        Thread.sleep(nextInt);
        System.out.println("任务2耗时" + stopwatch.toString());
        res.add(stopwatch.getTime(TimeUnit.MILLISECONDS));

        stopwatch.reset();
        stopwatch.start();

        nextInt = secureRandom.nextInt(2000);
        System.out.println("任务3：" + nextInt);
        Thread.sleep(nextInt);
        System.out.println("任务3耗时" + stopwatch.toString());
        res.add(stopwatch.getTime(TimeUnit.MILLISECONDS));

        stopwatch.reset();
        stopwatch.start();

        nextInt = secureRandom.nextInt(2000);
        System.out.println("任务4：" + nextInt);
        Thread.sleep(nextInt);
        System.out.println("任务4耗时" + stopwatch.toString());
        res.add(stopwatch.getTime(TimeUnit.MILLISECONDS));

        stopwatch.stop();

        System.out.println("res = " + res);

        stopwatch_total.stop();
        System.out.println("方法总耗时：" + stopwatch_total.toString());
        //任务1耗时00:00:00.908
        //任务2：1029
        //任务2耗时00:00:01.029
        //任务3：2
        //任务3耗时00:00:00.002
        //任务4：1963
        //任务4耗时00:00:01.966
        //方法总耗时：00:00:03.916
    }

    /**
     * 记录多个分段时间
     * @throws InterruptedException
     */
    @Test
    public void testStopWatch_split() throws  Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 第一段业务逻辑，一顿执行猛如虎，一看代码睡3秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        stopWatch.split();
        log.error("第一段耗时：{}毫秒",stopWatch.getSplitTime());

        // 第二段业务逻辑，再睡2秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stopWatch.split();
        log.error("第二段耗时：{}毫秒",stopWatch.getSplitTime());

        // 第三段业务逻辑，这次睡4秒
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        stopWatch.start();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        stopWatch.stop();
        log.error("zcw耗时：{}毫秒",stopWatch.getTime(TimeUnit.MILLISECONDS));


        log.error("总耗时：{}毫秒",stopWatch.getTime());
    }

    @Test
    public void testStopWatch3() throws InterruptedException {
        StopWatch sw = StopWatch.createStarted();
        Thread.sleep(1000);
//        sw.stop();
        System.out.println(sw.getTime(TimeUnit.MILLISECONDS));
        sw.reset();
        Thread.sleep(1000);
        System.out.println(sw.getTime(TimeUnit.MILLISECONDS));
//        sw.start();
        sw.reset();
        sw.start();
        Thread.sleep(1000);
        sw.stop();
        System.out.println(sw.getTime(TimeUnit.MILLISECONDS) + ", " +sw.isStopped());
        sw.suspend();

    }

    /**
     * 也不能连续stop()两次
     * @throws InterruptedException
     */
    @Test
    public void testStopWatch4() throws InterruptedException {
        StopWatch sw = StopWatch.createStarted();
        Thread.sleep(1000);
        sw.stop();
        sw.stop();
        System.out.println(sw.getTime(TimeUnit.MILLISECONDS));
    }

}
