package org.Test.Apache_commons_langs.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.PrintWriter;

import static org.junit.Assert.assertArrayEquals;

public class ExceptionUtilsTest {

    /**
     * ExceptionUtils 代替原生的 e.printStackTrace() 打印日志，防止在在高并发下获取异常信息阻塞，提供性能。
     *
     * e.getMessage() 只能获取异常信息，不能获取整个堆栈信息.
     * 使用 ExceptionUtils.getStackTrace(e) 则可以获取整个堆栈信息.
     */
    @Test
    public void testGetStackTrace() {

        try {
//            int a = 1 / 0;

//            String str = null;
//            boolean b = str.length() == 1;

            int[] ints = {1, 2, 3};
            System.out.println(ints[8]);
        } catch (Exception e) {
            System.out.println("ExceptionUtils.getStackTrace(e) = " + ExceptionUtils.getStackTrace(e));
            System.out.println("----------------------");
            System.out.println("ExceptionUtils.getMessage(e) = " + ExceptionUtils.getMessage(e));
            System.out.println("----------------------");
            System.out.println("ExceptionUtils.getStackFrames(e) = " + ArrayUtils.toString(ExceptionUtils.getStackFrames(e)));
            System.out.println("----------------------");
            System.out.println("ExceptionUtils.getRootCause(e) = " + ExceptionUtils.getRootCause(e));
            System.out.println("----------------------");
            System.out.println("ExceptionUtils.getRootCauseMessage(e) = " + ExceptionUtils.getRootCauseMessage(e));
            System.out.println("----------------------");
            e.printStackTrace();
        }
    }

    @Test
    public void testExceptionUtils() {
        //1. 获取异常信息
        Throwable th = new IllegalArgumentException("Base");
        String errMsg = ExceptionUtils.getMessage(th);
        System.out.println("errMsg = " + errMsg);
        //errMsg = IllegalArgumentException: Base

        //2. 获取错误的根原因
        Throwable withoutCause = new ExceptionWithoutCause();
//        Throwable nested = new NestableException(withoutCause);
//        ExceptionUtils.getRootCause(nested);


        //3. 获取栈帧消息
        String[] actual = ExceptionUtils.getStackFrames(new Throwable() {
            private static final long serialVersionUID = 1L;

            // provide static stack trace to make test stable
            @Override
            public void printStackTrace(final PrintWriter s) {
                s.write("org.apache.commons.lang3.exception.ExceptionUtilsTest$1\n" +
                        "\tat org.apache.commons.lang3.exception.ExceptionUtilsTest.testgetStackFramesGappyPath(ExceptionUtilsTest.java:706)\n" +
                        "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                        "\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)\n" +
                        "\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)\n");
            }
        });

        assertArrayEquals(new String[]{
                "org.apache.commons.lang3.exception.ExceptionUtilsTest$1",
                "\tat org.apache.commons.lang3.exception.ExceptionUtilsTest.testgetStackFramesGappyPath(ExceptionUtilsTest.java:706)",
                "\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)",
                "\tat com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)",
                "\tat com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)"
        }, actual);

    }

    private static class ExceptionWithoutCause extends Exception {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unused")
        public void getTargetException() {
            // noop
        }
    }

    private static class NestableException extends Exception {
        private static final long serialVersionUID = 1L;
        @SuppressWarnings("unused")
        public void getTargetException() {
            // noop
        }
    }

    @Test
    public void et() {
        StopWatch sw = StopWatch.createStarted();
        sw.reset();
        sw.reset();
        sw.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sw.stop();
        System.out.println(sw.getTime());



    }
}
