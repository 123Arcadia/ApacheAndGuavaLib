package org.Test.Apache_commons_langs.lang;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class RandomUtilsTest {
    /**
     * 随机生成一个Boolean
     */
    @Test
    public void testNextBoolean() {
        boolean b = RandomUtils.nextBoolean();
        System.out.println("b = " + b);//true
    }


    /**
     * 随机生成数值
     */
    @Test
    public void randomToNumber() {
        // 默认是[0, Integer.MAX_VALUE)
        int randInt = RandomUtils.nextInt();
        System.out.println("randInt = " + randInt);
        //randInt = 466106211
        int randIntFromTo = RandomUtils.nextInt(10, 15);
        System.out.println("randIntFromTo = " + randIntFromTo);
        //randIntFromTo = 12

        //long
        //byte
        //float


    }
}
