package org.Test.hu_tools.Converter;

import cn.hutool.core.convert.Convert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class test01 {
    /**
     * 常用的办法是先整成String，然后调用XXX.parseXXX方法，还要承受转换失败的风险，不得不加一层try catch，
     */

    /**
     * 转换为字符串
     */
    @Test
    public void test01() {
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);

        long[] b = {1,2,3,4,5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);

    }

    /**
     * 转换为日期对象: 还是用 DateUtils.parse(str)
     */
    @Test
    public void test02() {
//        String dateStr = "2019-01-01";
//        String dateStr = "2019/01/01 13:56:00";
        //结果: date = Tue Jan 01 13:56:00 CST 2019

        // 如果是错误的
        String dateStr = "20129/01/01 13:56:70";
        //结果: date = null
        //date为
        Date date = Convert.toDate(dateStr);
        System.out.println("date = " + date);
    }

    @Test
    public void test03() {
        String dateStr = "2019-01-01";
        //结果: date =
        LocalDateTime start = LocalDateTime.of(2022, 11, 07, 13, 56, 02);
        LocalDateTime end = LocalDateTime.of(2023, 7, 07, 13, 56, 02);

        Duration between = Duration.between(start, end);
        System.out.println("between = " + between.toDays()); //between = 242
    }
}
