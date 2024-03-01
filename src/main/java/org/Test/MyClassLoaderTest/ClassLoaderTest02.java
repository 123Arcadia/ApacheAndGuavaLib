package org.Test.MyClassLoaderTest;

import org.Test.MyClassLoaderTest.Context_ClassLoader.MyClassLoader1;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest02 {

    public static void main(String[] args) {

        MyClassLoader1 diskLoader1 = new MyClassLoader1("F:\\classLoader\\text\\");
        Class cls1 = null;
        try {
//加载class文件
            cls1 = diskLoader1.loadClass("SpeakTest");
            System.out.println(cls1.getClassLoader().toString());
            //org.Test.MyClassLoaderTest.Context_ClassLoader.MyClassLoader1@506e1b77
            if (cls1 != null) {
                try {
                    Object obj = cls1.newInstance();
                    //SpeakTest1 speak = (SpeakTest1) obj;
                    //speak.speak();
                    Method method = cls1.getDeclaredMethod("speak", null);
                    //通过反射调用Test类的speak方法
                    method.invoke(obj, null);
                    //Test
                } catch (InstantiationException | IllegalAccessException
                         | NoSuchMethodException
                         | SecurityException |
                         IllegalArgumentException |
                         InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * 2. 子线程的ContextClassLoader是AppClassLoader。
         * 3. AppClassLoader加载不了父线程当中已经加载的SpeakTest.class内容
         */
        MyClassLoader1 diskLoader = new MyClassLoader1("F:\\classLoader");
        System.out.println("Thread " + Thread.currentThread().getName() + " classloader: " + Thread.currentThread().getContextClassLoader().toString());
        //Thread main classloader: jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b
        new Thread(new Runnable() {

            @Override
            public void run() {
                /**
                 * 这里取默认AppClassLoader
                 */
                System.out.println("Thread " + Thread.currentThread().getName() + " classloader: " + Thread.currentThread().getContextClassLoader().toString());
                //Thread Thread-0 classloader: jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b

                try {
                    //加载class文件
                    //  Thread.currentThread().setContextClassLoader(diskLoader);
                    //Class c = diskLoader.loadClass("com.frank.test.SpeakTest");
                    ClassLoader cl = Thread.currentThread().getContextClassLoader();
                    Class c = cl.loadClass("SpeakTest");
                    // Class c = Class.forName("com.frank.test.SpeakTest");
                    System.out.println(c.getClassLoader().toString());
                    if (c != null) {
                        try {
                            Object obj = c.newInstance();
                            //SpeakTest1 speak = (SpeakTest1) obj;
                            //speak.speak();
                            Method method = c.getDeclaredMethod("speak", null);
                            //通过反射调用Test类的say方法
                            method.invoke(obj, null);
                        } catch (InstantiationException | IllegalAccessException
                                 | NoSuchMethodException
                                 | SecurityException |
                                 IllegalArgumentException |
                                 InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 在子线程开头处加上这么一句内容: Thread.currentThread().setContextClassLoader(diskLoader1);
     * 1
     * 1
     */
    @Test
    public void test01() {

        MyClassLoader1 diskLoader1 = new MyClassLoader1("F:\\classLoader\\text\\");
        Class cls1 = null;
        try {
//加载class文件
            cls1 = diskLoader1.loadClass("SpeakTest");
            System.out.println(cls1.getClassLoader().toString());
            //org.Test.MyClassLoaderTest.Context_ClassLoader.MyClassLoader1@506e1b77
            if (cls1 != null) {
                try {
                    Object obj = cls1.newInstance();
                    //SpeakTest1 speak = (SpeakTest1) obj;
                    //speak.speak();
                    Method method = cls1.getDeclaredMethod("speak", null);
                    //通过反射调用Test类的speak方法
                    method.invoke(obj, null);
                    //Test
                } catch (InstantiationException | IllegalAccessException
                         | NoSuchMethodException
                         | SecurityException |
                         IllegalArgumentException |
                         InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * 2. 子线程的ContextClassLoader是AppClassLoader。
         * 3. AppClassLoader加载不了父线程当中已经加载的SpeakTest.class内容
         */
        MyClassLoader1 diskLoader = new MyClassLoader1("F:\\classLoader");
        System.out.println("Thread " + Thread.currentThread().getName() + " classloader: " + Thread.currentThread().getContextClassLoader().toString());
        //Thread main classloader: jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b
        new Thread(new Runnable() {

            @Override
            public void run() {
                Thread.currentThread().setContextClassLoader(diskLoader1);
                System.out.println("Thread " + Thread.currentThread().getName() + " classloader: " + Thread.currentThread().getContextClassLoader().toString());
                //Thread Thread-0 classloader: jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b

                try {
                    //加载class文件
                    //  Thread.currentThread().setContextClassLoader(diskLoader);
                    //Class c = diskLoader.loadClass("com.frank.test.SpeakTest");
                    ClassLoader cl = Thread.currentThread().getContextClassLoader();
                    Class c = cl.loadClass("SpeakTest");
                    // Class c = Class.forName("com.frank.test.SpeakTest");
                    System.out.println(c.getClassLoader().toString());
                    if (c != null) {
                        try {
                            Object obj = c.newInstance();
                            //SpeakTest1 speak = (SpeakTest1) obj;
                            //speak.speak();
                            Method method = c.getDeclaredMethod("speak", null);
                            //通过反射调用Test类的say方法
                            method.invoke(obj, null);
                        } catch (InstantiationException | IllegalAccessException
                                 | NoSuchMethodException
                                 | SecurityException |
                                 IllegalArgumentException |
                                 InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // 输出:
        //MyclassLocader1: fileName=F:\classLoader\text\SpeakTest.class
        //MyclassLocader1: fileName=F:\classLoader\text\ISpeak.class
        //org.Test.MyClassLoaderTest.Context_ClassLoader.MyClassLoader1@77f03bb1
        //Test
        //Thread main classloader: jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b
        //Thread Thread-0 classloader: org.Test.MyClassLoaderTest.Context_ClassLoader.MyClassLoader1@77f03bb1
        //org.Test.MyClassLoaderTest.Context_ClassLoader.MyClassLoader1@77f03bb1
        //Test
    }


    /**
     * 改动：Thread.currentThread().setContextClassLoader(diskLoader);
     */
    @Test
    public void test02() {

        MyClassLoader1 diskLoader1 = new MyClassLoader1("F:\\classLoader\\text\\");
        Class cls1 = null;
        try {
//加载class文件
            cls1 = diskLoader1.loadClass("SpeakTest");
            System.out.println(cls1.getClassLoader().toString());
            //org.Test.MyClassLoaderTest.Context_ClassLoader.MyClassLoader1@506e1b77
            if (cls1 != null) {
                try {
                    Object obj = cls1.newInstance();
                    //SpeakTest1 speak = (SpeakTest1) obj;
                    //speak.speak();
                    Method method = cls1.getDeclaredMethod("speak", null);
                    //通过反射调用Test类的speak方法
                    method.invoke(obj, null);
                    //Test
                } catch (InstantiationException | IllegalAccessException
                         | NoSuchMethodException
                         | SecurityException |
                         IllegalArgumentException |
                         InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------------------------");
        MyClassLoader diskLoader = new MyClassLoader("F:\\classLoader\\");
        System.out.println("Thread " + Thread.currentThread().getName() + " classloader: " + Thread.currentThread().getContextClassLoader().toString());
        //Thread main classloader: jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b
        new Thread(new Runnable() {

            @Override
            public void run() {
                Thread.currentThread().setContextClassLoader(diskLoader);
                System.out.println("Thread " + Thread.currentThread().getName() + " classloader: " + Thread.currentThread().getContextClassLoader().toString());
                //Thread Thread-0 classloader: jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b

                try {
                    //加载class文件
                    //  Thread.currentThread().setContextClassLoader(diskLoader);
                    //Class c = diskLoader.loadClass("com.frank.test.SpeakTest");
                    ClassLoader cl = Thread.currentThread().getContextClassLoader();
                    Class c = cl.loadClass("SpeakTest");
                    // Class c = Class.forName("com.frank.test.SpeakTest");
                    System.out.println(c.getClassLoader().toString());
                    if (c != null) {
                        try {
                            Object obj = c.newInstance();
                            //SpeakTest1 speak = (SpeakTest1) obj;
                            //speak.speak();
                            Method method = c.getDeclaredMethod("speak", null);
                            method.invoke(obj, null);
                        } catch (InstantiationException | IllegalAccessException
                                 | NoSuchMethodException
                                 | SecurityException |
                                 IllegalArgumentException |
                                 InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // 输出:
        //MyclassLocader1: fileName=F:\classLoader\text\SpeakTest.class
        //MyclassLocader1: fileName=F:\classLoader\text\ISpeak.class
        //org.Test.MyClassLoaderTest.Context_ClassLoader.MyClassLoader1@77f03bb1
        //Test
        //------------------------------------------------------
        //Thread main classloader: jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b
        //Thread Thread-0 classloader: org.Test.MyClassLoaderTest.MyClassLoader@16c0663d
        //MyclassLocader: fileName=F:\classLoader\SpeakTest.class
        //MyclassLocader: fileName=F:\classLoader\ISpeak.class
        //org.Test.MyClassLoaderTest.MyClassLoader@16c0663d
        //I' frank

    }
}
