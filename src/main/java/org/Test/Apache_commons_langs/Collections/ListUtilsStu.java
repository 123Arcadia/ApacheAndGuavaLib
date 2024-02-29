package org.Test.Apache_commons_langs.Collections;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.util.*;

/**
 * ListUtils 工具类练习
 * <p>
 * 参考 {@link org.apache.commons.collections4.CollectionUtils}
 */
public class ListUtilsStu {

    /**
     * List<List<T>> partition(final List<T> list, final int size)：将集合拆分为指定大小的小集合
     *
     * partition后不可修改
     */
    @org.junit.Test
    public void testPartition() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<String> strList = Arrays.asList("a1", "b2", "c3", "d4", "e5", "f6", "g7", "h9");
        List<List<Integer>> subSets = ListUtils.partition(intList, 3);
        List<List<String>> lists = ListUtils.partition(strList, 4);
        System.out.println(subSets);
        //[[1, 2, 3], [4, 5, 6], [7, 8]]
        System.out.println(lists);
        //[[a1, b2, c3, d4], [e5, f6, g7, h9]]
    }

    /**
     * List<E> intersection(final List<? extends E> list1, final List<? extends E> list2)
     * 获取 list1 与 list2 集合的交集。list1、list2 不变.
     */
    @Test
    public void testIntersection() {
        List<String> names1 = Lists.newArrayList("1", "1", "2", "2");
        List<String> names2 = Lists.newArrayList("3", "1", "1", "1");
        List<String> names3 = Lists.newArrayList();

        // [1, 1, 2, 2] - [3, 1, 1, 1] -> [1]
        System.out.println(names1 + " - " + names2 + " -> " + ListUtils.intersection(names1, names2));
        // [1, 1, 2, 2] - [] -> []
        System.out.println(names1 + " - " + names3 + " -> " + ListUtils.intersection(names1, names3));
        // 是否有共同元素
        boolean disjoint = Collections.disjoint(names1, names2);
        System.out.println("disjoint = " + disjoint);
        // false
    }

    /**
     * List<E> removeAll(final Collection<E> coll1, final Collection<?> coll2)
     * 返回在coll1中有，coll2中没有的元素集合,包括Null
     *
     * 1、获取集合 coll1 减去集合 coll2 后的元素，原 coll1、coll2 集合内容不变。
     * 2、无论两个集合中的有多少重复元素，都会视作一个进行移除，比如 coll1 中有 a,a,a，coll2 中有 a，则结果中没有 a
     */
    @Test
    public void testRemoveAll() {
        List<String> names1 = Lists.newArrayList("马六", null, "张三", "张三", "李四", "李四", null);
        List<String> names2 = Lists.newArrayList("王五", "张三", "李四", "李四", "李四", "李四", null);
        List<String> names3 = Lists.newArrayList();

        List<String> removeAll = ListUtils.removeAll(names1, names2);
        System.out.println(names1);
        // [马六, null, 张三, 张三, 李四, 李四, null]
        System.out.println(names2);
        // [王五, 张三, 李四, 李四, 李四, 李四, null]
        System.out.println(removeAll);
        // [马六]
        System.out.println(ListUtils.removeAll(names1, names3));
        // [马六, null, 张三, 张三, 李四, 李四, null]
        System.out.println(ListUtils.removeAll(names3, names1));
        // []

        List<String> res = ListUtils.removeAll(names1, removeAll);
        System.out.println("res = " + res);
        //res = [null, 张三, 张三, 李四, 李四, null]
    }

    /**
     * static <E> List<E> subtract(final List<E> list1, final List<? extends E> list2) <p>
     * 1、从 list1 列表中减去 list2 列表中的所有元素，将结果放入新列表，原集合不变 <p>
     * 2、集合中的重复元素会按个数进行相减，比如 coll1 中有 a,a,a，coll2 中有 a，则结果中有 a,a <p>
     */
    @Test
    public void testSubtract() {
        List<String> names1 = Lists.newArrayList("马六", null, "张三", "张三", "李四", "李四", null);
        List<String> names2 = Lists.newArrayList("王五", "张三", "李四", "李四", "李四", "李四", null);
        List<String> names3 = Lists.newArrayList();

        List<String> subtract = ListUtils.subtract(names1, names2);
        System.out.println(names1);
        // [马六, null, 张三, 张三, 李四, 李四, null]
        System.out.println(names2);
        // [王五, 张三, 李四, 李四, 李四, 李四, null]
        System.out.println(subtract);
        // [马六, 张三, null]

        System.out.println(ListUtils.subtract(names3, names2));
        // []
        System.out.println(ListUtils.subtract(names2, names3));
        // [王五, 张三, 李四, 李四, 李四, 李四, null]
    }

    //static <E> List<E> union(final List<? extends E> list1, final List<? extends E> list2)
    //返回一个新列表，包含 list1、list2 的所有元素，不会过滤重复元素。
    @Test
    public void testUnion() {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        names1.add("张三");
        names1.add("李四");
        names2.add("王五");
        names2.add("张三");
        List<String> union = ListUtils.union(names1, names2);
        System.out.println(names1 + "," + names2 + "," + union);
        //[张三, 李四],[王五, 张三],[张三, 李四, 王五, 张三]
    }

    /**
     * List<E> synchronizedList(final List<E> list)
     * 返回由给定列表支持的同步列表，返回列表的迭代器上需要手动同步。
     */
    @Test
    public void testSynchronizedList() {
        List<String> names1 = new ArrayList<>();
        names1.add("张三");
        names1.add("李四");
        names1.add("王五");
        List<String> synchronizedList = ListUtils.synchronizedList(names1);
        synchronized (synchronizedList) {
            Iterator<String> iterator = synchronizedList.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + "\t");//张三   李四  王五
            }
        }
    }

    //static <E> List<E> sum(final List<? extends E> list1, final List<? extends E> list2)
    //获取 list1、list2 集合元素的并集，会过滤掉重复元素，放到新的集合中。
    @Test
    public void testSum() {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        names1.add("张三");
        names1.add("李四");
        names2.add("王五");
        names2.add("张三");
        List<String> sum = ListUtils.sum(names1, names2);
        System.out.println(names1 + "," + names2 + "," + sum);//[张三, 李四],[王五, 张三],[李四, 王五, 张三]
    }

    /**
     * 1、{@link Arrays#asList(java.lang.Object[])} 返回的是 Arrays 的内部类 {@link Arrays.ArrayList}，而并不是 java.util.ArrayList
     * 2、内部类继承关系：private static class ArrayList<E> extends AbstractList<E> extends AbstractCollection<E> implements List<E>
     * 3、Arrays 的内部类 {@link Arrays.ArrayList} 只重写了部分方法，而  add、remove 方法是没有重写的，在 AbstractList 中直接抛的异常 throw new UnsupportedOperationException();
     */
    @Test
    public void asList() {
        //一定要注意 Arrays.asList 返回的 ArrayList 并不是 java.util.ArrayList，而只是 Arrays 的一个内部类，它没有重写 add、remove 方法
        //调用就会抛出异常 UnsupportedOperationException - 不支持的操作
        List<Integer> integerList = Arrays.asList(12, 22, 32, 23);
        integerList.add(999);

    }


    /**
     * boolean isEqualList(final Collection<?> list1, final Collection<?> list2)
     * 判断两个 list 的元素是否一一对应相等。(包括顺序)
     */
    @Test
    public void testIsEqualList() {
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        names1.add("张三");
        names2.add("张三2");
        boolean equalList = ListUtils.isEqualList(names1, names2);
        System.out.println(equalList);
        //false

        List<String> list1 = Lists.newArrayList("111", "222", "222");
        List<String> list2 = Lists.newArrayList("222", "111" , "222");
        boolean isEqualTwoList = ListUtils.isEqualList(list1, list2);
        System.out.println("isEqualTwoList = " + isEqualTwoList);
        //false
    }

}
