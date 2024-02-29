package org.Test.Apache_commons_langs.codec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * 摘要算法
 */
public class DigestEncoders {

    /**
     * md5 摘要算法 1
     */
    @Test
    public void testMd5Digest1() {
        String message = "不见当年秦始皇";
        /**getDigest(final String algorithm) 返回的是 Java JDK 原生的 {@link MessageDigest} 信息摘要对象
         * 也可以使用 DigestUtils.getMd5Digest() 直接返回 md5 信息摘要.
         * {@link MessageDigestAlgorithms} 中定义了全部的摘要算法*/
        MessageDigest md5 = DigestUtils.getDigest(MessageDigestAlgorithms.MD5);
        //生成摘要
        byte[] digest = md5.digest(message.getBytes());
        /**encodeHexString(final byte[] data, final boolean toLowerCase)：将摘要转成可视化的 16 进制字符串.
         * data：十进制的字节数组
         * toLowerCase：结果中的字母是否转为小写，默认为 true 转为小写.*/
        String hexString = Hex.encodeHexString(digest, true);
        //091d525bf3dd0a4e6bc20d45c34f399e
        System.out.println(hexString);
    }

    /**
     * md5 摘要算法 2
     */
    @Test
    public void testDd5Digest2(){
        /** md5Hex(final String data): 对 data 进行 md5 提取摘要，并转为 16 进制。
         * 同理还会有 sha256Hex、md2Hex 等等.*/
        String message1 = "不见当年秦始皇";
        String message2 = "不见当年秦始皇，不见当年秦始皇不见当年秦始皇不见当年秦始皇不见当年秦始皇不见当年秦始皇不见当年秦始皇";

        String md5Hex1 = DigestUtils.md5Hex(message1);
        String md5Hex2 = DigestUtils.md5Hex(message2);
        //091d525bf3dd0a4e6bc20d45c34f399e
        System.out.println(md5Hex1);
        //4201179699841cef294d4b1e287fed52
        System.out.println(md5Hex2);
    }

    /**
     * 对文件提取摘要信息 1
     */
    @Test
    public void md5Digest3() {
        File file = new File("C:\\wmx\\study\\jdk+api+1.8_google.CHM");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            String sha256Hex = DigestUtils.sha256Hex(fileInputStream);
            //ddf90033af5b197d0797f8646e3a80ada465ea554872316cd63a2ff5998a7d67
            System.out.println(sha256Hex);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对文件提取摘要信息 2
     */
    @Test
    public void md5Digest4() {
        File file = new File("C:\\wmx\\study\\jdk+api+1.8_google.CHM");
        try {
            MessageDigest sha256Digest = DigestUtils.getSha256Digest();
            byte[] digest = DigestUtils.digest(sha256Digest, file);
            String encodeHexString = Hex.encodeHexString(digest);
            //ddf90033af5b197d0797f8646e3a80ada465ea554872316cd63a2ff5998a7d67
            System.out.println(encodeHexString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
