package org.Test.Apache_commons_langs.lang;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class DateUtilsTest {
    @Test
    public void testDateUtils() {
        Date date1 = new Date();
        Date date2 = new Date();
        //是否相同日期
        System.out.println(DateUtils.isSameDay(date1, date2));
        //true

        //是否相同时间
        System.out.println(DateUtils.isSameInstant(date1, date2));
        //true

        //时间范围迭代器
        // 一周以周日为起始，从 now 这周的周日生成一周
        Date now = new Date();
        Iterator<Calendar> iterator1 = DateUtils.iterator(now, DateUtils.RANGE_WEEK_SUNDAY);
        while(iterator1.hasNext()) {
            Calendar next = iterator1.next();
            System.out.println(new Date(next.getTime().getTime()));
            //Sun Feb 11 00:00:00 CST 2024
            //Mon Feb 12 00:00:00 CST 2024
            //Tue Feb 13 00:00:00 CST 2024
            //Wed Feb 14 00:00:00 CST 2024
            //Thu Feb 15 00:00:00 CST 2024
            //Fri Feb 16 00:00:00 CST 2024
            //Sat Feb 17 00:00:00 CST 2024
        }

        try {
            Date date = DateUtils.parseDate("2024-02-16 10:31:33", "yyyy-MM--dd HH:mm:ss");
            System.out.println("date = " + date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
