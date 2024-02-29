package org.Test.Apache_commons_langs.Collections;

import org.apache.commons.collections4.SetUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetUtilsTest {

    /**
     * SetView<E> difference(final Set<? extends E> a, final Set<? extends E> b)
     * 1: 集合 a 中的元素减去集合 b 中的元素.
     * 2: 集合 a, b 不能为 null,否则报错.
     */
    @Test
    public void testDifference() {
        Set<String> set1 = SetUtils.hashSet("1", "2", "3");
        Set<String> set2 = SetUtils.hashSet("3", "4");
        Set<String> set3 = new HashSet<>(8);

        SetUtils.SetView<String> setView = SetUtils.difference(set1, set2);
        // [1, 2, 3] - [3, 4] -> [1, 2]
        System.out.println(set1 + " - " + set2 + " -> " + setView.toSet());
        // [3, 4] - [1, 2, 3] -> [4]
        System.out.println(set2 + " - " + set1 + " -> " + SetUtils.difference(set2, set1));
        // [3, 4] - [] -> [3, 4]
        System.out.println(set2 + " - " + set3 + " -> " + SetUtils.difference(set2, set3));
        // [] - [3, 4] -> []
        System.out.println(set3 + " - " + set2 + " -> " + SetUtils.difference(set3, set2));

        Set<String> toSet = setView.toSet();
        toSet.add("a");
        // [1, a, 2]
        System.out.println(toSet);
    }

    /**
     * SetView<E> disjunction(final Set<? extends E> a, final Set<? extends E> b)
     * 1: 返回包含集合 a 和 b 中不是另一个集合成员的所有元素。
     * 2: 相当 union(difference(a，b)，difference(b，a))。
     * 3: 返回 a 中有且 b 中没有的元素 加上 b 中有且 a 中没有的元素之和.
     * 4: 集合 a, b 不能为 null,否则报错.
     */
    @Test
    public void testDisjunction() {
        Set<String> set1 = SetUtils.hashSet("1", "2", "3");
        Set<String> set2 = SetUtils.hashSet("3", "4");
        Set<String> set3 = new HashSet<>(8);

        SetUtils.SetView<String> setView = SetUtils.disjunction(set1, set2);
        // [1, 2, 3] - [3, 4] -> [4, 1, 2]
        System.out.println(set1 + " - " + set2 + " -> " + setView.toSet());
        // [3, 4] - [1, 2, 3] -> [4, 1, 2]
        System.out.println(set2 + " - " + set1 + " -> " + SetUtils.disjunction(set2, set1));
        // [3, 4] - [] -> [3, 4]
        System.out.println(set2 + " - " + set3 + " -> " + SetUtils.disjunction(set2, set3));
        // [] - [3, 4] -> [3, 4]
        System.out.println(set3 + " - " + set2 + " -> " + SetUtils.disjunction(set3, set2));

        Set<String> toSet = setView.toSet();
        toSet.add("a");
        // [1, a, 2, 4]
        System.out.println(toSet);
    }

    /**
     * SetView<E> intersection(final Set<? extends E> a, final Set<? extends E> b)
     * 1: 获取集合 a,b 元素的交集.
     * 2: 集合 a, b 不能为 null,否则报错.
     */
    @Test
    public void testIntersection() {
        Set<String> set1 = SetUtils.hashSet("1", "2", "3");
        Set<String> set2 = SetUtils.hashSet("3", "4");
        Set<String> set3 = new HashSet<>(8);

        SetUtils.SetView<String> setView = SetUtils.intersection(set1, set2);
        // [1, 2, 3] - [3, 4] -> [3]
        System.out.println(set1 + " - " + set2 + " -> " + setView.toSet());
        // [3, 4] - [1, 2, 3] -> [3]
        System.out.println(set2 + " - " + set1 + " -> " + SetUtils.intersection(set2, set1));
        // [3, 4] - [] -> []
        System.out.println(set2 + " - " + set3 + " -> " + SetUtils.intersection(set2, set3));
        // [] - [3, 4] -> []
        System.out.println(set3 + " - " + set2 + " -> " + SetUtils.intersection(set3, set2));

        Set<String> toSet = setView.toSet();
        toSet.add("a");
        // [a, 3]
        System.out.println(toSet);
    }

    /**
     * SetView<E> union(final Set<? extends E> a, final Set<? extends E> b)
     * 1: 获取集合 a,b 元素的并集.
     * 2: 集合 a, b 不能为 null,否则报错.
     */
    @Test
    public void testUnion() {
        Set<String> set1 = SetUtils.hashSet("1", "2", "3");
        Set<String> set2 = SetUtils.hashSet("3", "4");
        Set<String> set3 = new HashSet<>(8);

        SetUtils.SetView<String> setView = SetUtils.union(set1, set2);
        // [1, 2, 3] - [3, 4] -> [1, 2, 3] - [3, 4] -> [1, 2, 3, 4]
        System.out.println(set1 + " - " + set2 + " -> " + setView.toSet());
        // [3, 4] - [1, 2, 3] -> [3, 4, 1, 2]
        System.out.println(set2 + " - " + set1 + " -> " + SetUtils.union(set2, set1));
        // [3, 4] - [] -> [3, 4]
        System.out.println(set2 + " - " + set3 + " -> " + SetUtils.union(set2, set3));
        // [] - [3, 4] -> [3, 4]
        System.out.println(set3 + " - " + set2 + " -> " + SetUtils.union(set3, set2));

        Set<String> toSet = setView.toSet();
        toSet.add("a");
        // [1, a, 2, 3, 4]
        System.out.println(toSet);
    }

    /**
     * boolean isEqualSet(final Collection<?> set1, final Collection<?> set2)
     * 检查集合 a, b 是否相等.
     */
    @Test
    public void testIsEqualSet() {
        Set<String> set1 = SetUtils.hashSet("1", "2", "3");
        Set<String> set2 = SetUtils.hashSet("3", "4");
        Set<String> set3 = SetUtils.hashSet("3", "1", "2");
        Set<String> set4 = null;
        Set<String> set5 = SetUtils.hashSet();

        // false
        System.out.println(SetUtils.isEqualSet(set1, set2));
        // true
        System.out.println(SetUtils.isEqualSet(set1, set3));
        // false
        System.out.println(SetUtils.isEqualSet(set4, set5));
    }

    /**
     * Set<E> orderedSet(final Set<E> set)
     * 1: 获取给定集合支持的有序集合, set 不能为 null.
     */
    @Test
    public void testOrderedSet() {
        Set<String> set1 = SetUtils.hashSet("3", "1", "2", "6");
        Set<String> set2 = SetUtils.hashSet("a", "d", "b", "c");
        Set<String> set3 = SetUtils.hashSet("3", "1", "2", "6", "a", "d", "b", "c");

        // [1, 2, 3, 6]
        System.out.println(SetUtils.orderedSet(set1));
        // [a, b, c, d]
        System.out.println(SetUtils.orderedSet(set2));
        // [1, a, 2, b, 3, c, d, 6]
        System.out.println(SetUtils.orderedSet(set3));
    }

    /**
     * Set<E> unmodifiableSet(final Set<? extends E> set)
     * 1: 将集合转为不可修改集合/只读集合
     */
    @Test
    public void testUnmodifiableSet() {
        Set<String> set1 = SetUtils.hashSet();

        Set<String> unmodifiableSet = SetUtils.unmodifiableSet(set1);
        // []
        System.out.println(unmodifiableSet);
        // java.lang.UnsupportedOperationException
        unmodifiableSet.add("a");
    }
}