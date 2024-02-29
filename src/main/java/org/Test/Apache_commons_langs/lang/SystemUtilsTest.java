package org.Test.Apache_commons_langs.lang;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

public class SystemUtilsTest {

    @Test
    public void testSystemUtils() {
        //获取环境变量
        //SystemUtils.getEnvironmentVariable("PATH", "default_value");
        System.out.println(org.apache.commons.lang3.SystemUtils.getEnvironmentVariable("PATH", "default_value"));
        //获取 Hostname
        //SystemUtils.getHostName();
        System.out.println(org.apache.commons.lang3.SystemUtils.getHostName());
        //LAPTOP-89JUTCMV

        //获取 JavaHome
        //SystemUtils.getJavaHome();
        System.out.println(org.apache.commons.lang3.SystemUtils.getJavaHome());
        //D:\jdk-17.0.4+8
        //获取 UserHome
        //SystemUtils.getUserHome();
        System.out.println(org.apache.commons.lang3.SystemUtils.getUserHome());
        //C:\Users\zhangchenwei

        //获取操作系统版本
        //SystemUtils.OS_VERSION
        System.out.println(org.apache.commons.lang3.SystemUtils.OS_VERSION);
        //10.0

        //获取操作系统名字
        //SystemUtils.OS_NAME
        System.out.println(org.apache.commons.lang3.SystemUtils.OS_NAME);
        //Windows 11
        System.out.println(org.apache.commons.lang3.SystemUtils.OS_ARCH);
        //amd64

        //获取 java 版本
        //SystemUtils.JAVA_VERSION
        System.out.println(org.apache.commons.lang3.SystemUtils.getUserDir());
        //D:\javaProject\ApacheAndGuavaLib
        System.out.println(SystemUtils.USER_HOME);
        //C:\Users\zhangchenwei
        System.out.println(SystemUtils.USER_DIR);
        //D:\javaProject\ApacheAndGuavaLib

        System.out.println(org.apache.commons.lang3.SystemUtils.JAVA_VM_INFO);
        //mixed mode, sharing

        System.out.println(org.apache.commons.lang3.SystemUtils.JAVA_RUNTIME_NAME);
        //OpenJDK Runtime Environment

        System.out.println(org.apache.commons.lang3.SystemUtils.FILE_ENCODING);
        //UTF-8

        System.out.println(org.apache.commons.lang3.SystemUtils.JAVA_CLASS_PATH);
        //D:\javaIDEA\IntelliJ IDEA 2022.2.1\lib\idea_rt.jar;D:\javaIDEA\IntelliJ IDEA 2022.2.1\plugins\junit\lib\junit5-rt.jar;D:\javaIDEA\IntelliJ IDEA 2022.2.1\plugins\junit\lib\junit-rt.jar;D:\javaProject\ApacheAndGuavaLib\target\classes;D:\CSProject\.m2\repository\junit\junit\4.12\junit-4.12.jar;D:\CSProject\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;D:\CSProject\.m2\repository\com\google\guava\guava\33.0.0-jre\guava-33.0.0-jre.jar;D:\CSProject\.m2\repository\com\google\guava\failureaccess\1.0.2\failureaccess-1.0.2.jar;D:\CSProject\.m2\repository\com\google\guava\listenablefuture\9999.0-empty-to-avoid-conflict-with-guava\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;D:\CSProject\.m2\repository\com\google\code\findbugs\jsr305\3.0.2\jsr305-3.0.2.jar;D:\CSProject\.m2\repository\org\checkerframework\checker-qual\3.41.0\checker-qual-3.41.0.jar;D:\CSProject\.m2\repository\com\google\errorprone\error_prone_annotations\2.23.0\error_prone_annotations-2.23.0.jar;D:\CSProject\.m2\repository\com\google\j2objc\j2objc-annotations\2.8\j2objc-annotations-2.8.jar;D:\CSProject\.m2\repository\commons-beanutils\commons-beanutils\1.9.4\commons-beanutils-1.9.4.jar;D:\CSProject\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;D:\CSProject\.m2\repository\commons-collections\commons-collections\3.2.2\commons-collections-3.2.2.jar;D:\CSProject\.m2\repository\commons-codec\commons-codec\1.14\commons-codec-1.14.jar;D:\CSProject\.m2\repository\org\apache\commons\commons-lang3\3.9\commons-lang3-3.9.jar;D:\CSProject\.m2\repository\org\apache\commons\commons-collections4\4.4\commons-collections4-4.4.jar;D:\CSProject\.m2\repository\commons-io\commons-io\2.11.0\commons-io-2.11.0.jar;D:\CSProject\.m2\repository\org\apache\logging\log4j\log4j-core\2.11.1\log4j-core-2.11.1.jar;D:\CSProject\.m2\repository\org\apache\logging\log4j\log4j-api\2.11.1\log4j-api-2.11.1.jar;D:\CSProject\.m2\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;D:\CSProject\.m2\repository\org\apache\logging\log4j\log4j-slf4j-impl\2.10.0\log4j-slf4j-impl-2.10.0.jar;D:\CSProject\.m2\repository\com\lmax\disruptor\3.3.7\disruptor-3.3.7.jar;D:\CSProject\.m2\repository\org\projectlombok\lombok\1.18.22\lombok-1.18.22.jar;D:\CSProject\.m2\repository\cn\hutool\hutool-all\5.8.16\hutool-all-5.8.16.jar
        System.out.println(org.apache.commons.lang3.SystemUtils.JAVA_CLASS_VERSION);
        //61.0

    }
}
