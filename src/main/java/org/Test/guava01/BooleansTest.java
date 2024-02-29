package org.Test.guava01;

import com.google.common.primitives.Booleans;
import org.junit.Test;

import java.util.List;
import java.util.StringJoiner;

public class BooleansTest {



    /**
     * List<Boolean> asList(boolean... backingArray)：将数组转为列表
     * boolean[] toArray(Collection<Boolean> collection)：将集合转为数组
     */
    @Test
    public void test1() {
        List<Boolean> booleanList = Booleans.asList(false, true, true, false);
        //[false, true, true, false]
        System.out.println(booleanList);
        //[false, true, true, false]

        boolean[] booleans = Booleans.toArray(booleanList);
        //false,true
        System.out.println(booleans[0] + "," + booleans[2]);
        //false,true
    }

    /**
     * int compare(boolean a, boolean b)：比较布尔值，底层使用 (a == b) ? 0 : (a ? 1 : -1)
     *
     * 0: 相等
     */
    @Test
    public void test2() {
        System.out.println(Booleans.compare(true, true));
        //0
        System.out.println(Booleans.compare(true, false));
        //1
        System.out.println(Booleans.compare(false, false));
        //0
        System.out.println(Booleans.compare(false, true));
        //-1
    }

    /**
     * boolean[] concat(boolean[]... arrays) :将多个数组组合成一个
     * boolean contains(boolean[] array, boolean target) :检查数组中是否包含目标元素
     */
    @Test
    public void test3() {
        boolean[] booleans1 = new boolean[]{false, false, true, false};
        boolean[] booleans2 = new boolean[]{false, true, true, true};

        boolean[] concat = Booleans.concat(booleans1, booleans2);
        //[false, false, true, false, false, true, true, true]
        System.out.println(Booleans.asList(concat));

        //true
        System.out.println(Booleans.contains(booleans1, true));
    }

    /**
     * int countTrue(boolean... values)：统计数组中 true 的个数
     */
    @Test
    public void test4() {
        boolean[] booleans2 = new boolean[]{false, true, true, true};
        System.out.println("true 个数为：" + Booleans.countTrue(booleans2));
        //3
        System.out.println("false 个数为：" + (booleans2.length - Booleans.countTrue(booleans2)));
        //1
    }

    /**
     * int indexOf(boolean[] array, boolean target)：数组中第一次出现 target 的索引
     * int lastIndexOf(boolean[] array, boolean target): 数组中最后一次出现 target 的索引
     */
    @Test
    public void test5() {
        boolean[] booleans2 = new boolean[]{false, true, true, true};
        //1
        System.out.println(Booleans.indexOf(booleans2, true));
        //3
        System.out.println(Booleans.lastIndexOf(booleans2, true));
    }

    /**
     * String join(String separator, boolean... array): 使用字符串将数组中的元素连接起来
     * void reverse(boolean[] array) ：对数组中的元素位置进行反转
     * void reverse(boolean[] array, int fromIndex, int toIndex)：对数组中的指定范围内的元素位置进行反转
     */
    @Test
    public void test6() {
        boolean[] booleans2 = new boolean[]{false, true, true, true};
        // 不如用StringJoiner或者String.join
        String join = "(" + Booleans.join(",", booleans2) + ")";
        System.out.println(join);
        //(false,true,true,true)

        Booleans.reverse(booleans2,0,3);
        System.out.println(Booleans.asList(booleans2));
        //[true, true, false, true]

        StringJoiner boolJoiner = new StringJoiner(",","(",")");
        for (boolean b : booleans2) {
            boolJoiner.add(String.valueOf(b));
        }
        System.out.println("boolJoiner = " + boolJoiner);
        //boolJoiner = (true,true,false,true)
    }
}
