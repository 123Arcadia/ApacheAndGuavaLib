package org.Test.hu_tools.ResourceUtilTes;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

public class ResourceUtilTest {

    @Test
    public void test01() throws Exception
    {
        // 1.获取资源文件
        String re = ResourceUtil.readStr("D:\\javaProject\\ApacheAndGuavaLib\\src\\main\\java\\org\\Test\\hu_tools\\ResourceUtilTes\\test.yaml", Charset.forName("UTF-8"));
        System.out.println("re = " + re);
        //re = name: zcwccxzcssa
        //age:
        //  15
        //
        //project:
        //  name: app
        //  start: 2022
        //  end: 2024
        String readRes2 = ResourceUtil.readStr("file:/src/main/java/org/Test/hu_tools/ResourceUtilTes/test.yaml", Charset.forName("UTF-8"));
        System.out.println("readRes2 = " + readRes2);
    }

    @Test
    public void test02() throws Exception
    {
        // 1.获取资源文件
        ClassPathResource resource = new ClassPathResource("/test.yaml");
        Properties properties = new Properties();
        properties.load(resource.getStream());

        Console.log("Properties: {}", properties);
        //Properties: {15=, name=app, start=2022, project=, end=2024, age=}
    }

    @Test
    public void test03() throws Exception
    {
        // 1.获取资源文件
//        URL resource = ResourceUtilTest.class.getResource("org/Test/hu_tools/ResourceUtilTes/test.yaml");
        URL resource = ResourceUtilTest.class.getResource("/test.yaml");
        //resource = file:/D:/javaProject/ApacheAndGuavaLib/target/classes/
        System.out.println("resource = " + resource);
    }
}
