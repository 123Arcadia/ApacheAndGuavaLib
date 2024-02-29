package org.Test.Apache_commons_langs.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

public class ArrayUtilsTest {

    /**
     * boolean[] add(final boolean[] array, final boolean element)
     * byte[] add(final byte[] array, final byte element)
     * char[] add(final char[] array, final char element)
     * double[] add(final double[] array, final double element)
     * float[] add(final float[] array, final float element)
     * int[] add(final int[] array, final int element)
     * long[] add(final long[] array, final long element)
     * short[] add(final short[] array, final short element)
     * T[] add(final T[] array, final T element)
     * 复制给定数组并在新数组的末尾添加给定元素。<strong>返回新数组</strong>，原数组不变。（数组也能像List一样操作）
     * 如果输入数组是{@code null}，则返回一个新的单元素数组，其组件类型与元素相同。
     * <p>
     * ArrayUtils.add(null, null) = IllegalArgumentException
     * ArrayUtils.add(null, true) = [true]
     * ArrayUtils.add([1], 0)  = [1, 0]
     * ArrayUtils.add(['1', '0'], '1') = ['1', '0', '1']
     * ArrayUtils.add(["a"], null) = ["a", null]
     *
     * 重点： 返回新数组
     */
    @Test
    public void add() {
        Integer[] integersArray = {};
        System.out.println("integersArray = " + Arrays.toString(integersArray));
        //integersArray = []
        System.out.println(ArrayUtils.toString(integersArray));
        //{}

//        System.out.println("[integersArray[0]] = " +integersArray[0]);
//        java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
        Integer[] addArray = ArrayUtils.add(integersArray, 100);
        addArray = ArrayUtils.add(addArray, 100);

        //{}
        System.out.println(ArrayUtils.toString(integersArray));
        //{100,100}
        System.out.println(ArrayUtils.toString(addArray));

        ArrayUtils.add(integersArray, null);
        System.out.println("addArray = " + ArrayUtils.toString(integersArray));
        //addArray = {}
        ArrayUtils.add(integersArray, null);
        System.out.println("addArray = " + ArrayUtils.toString(integersArray));
        //addArray = {}
        boolean[] nullAddTrue = ArrayUtils.add(null, true);
        System.out.println("ArrayUtils.toString(nullAddTrue) = " + ArrayUtils.toString(nullAddTrue));

        int[] inrArrAddNum = ArrayUtils.add((int[]) null, 0);
        System.out.println(Arrays.toString(inrArrAddNum));
        //[0]
    }

    /**
     * boolean[] addAll(final boolean[] array1, final boolean... array2)
     * byte[] addAll(final byte[] array1, final byte... array2)
     * char[] addAll(final char[] array1, final char... array2)
     * double[] addAll(final double[] array1, final double... array2)
     * float[] addAll(final float[] array1, final float... array2)
     * int[] addAll(final int[] array1, final int... array2)
     * long[] addAll(final long[] array1, final long... array2)
     * short[] addAll(final short[] array1, final short... array2)
     * T[] addAll(final T[] array1, final T... array2)
     * 将给定数组的所有元素添加到新数组中，新数组包含array1的所有元素，以及array2的所有元素。返回新数组，原数组不变。
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     *
     * 原数组不变。
     */
    @Test
    public void addAll() {
        Integer[] integers1 = {100, 200, 300};
        Integer[] integers2 = {1, 2, 3, 4, 5};

        Integer[] addAll = ArrayUtils.addAll(integers1, integers2);

        //{100,200,300}
        System.out.println(ArrayUtils.toString(integers1));
        //{100,200,300,1,2,3,4,5}
        System.out.println(ArrayUtils.toString(addAll));

    }

    /**
     * 为什么不用System.copy()
     *
     * boolean[] clone(final boolean[] array): 支持操作8种基本数据类型
     * T[] clone(final T[] array): 克隆数组，如果参数是 null，则返回 null。
     */
    @Test
    public void cloneArray() {
        Integer[] integers1 = {100, 200, 300};
        Integer[] clone = ArrayUtils.clone(integers1);
        Integer[] add = ArrayUtils.add(clone, 2000);

        //{100,200,300}
        System.out.println(ArrayUtils.toString(integers1));
        //{100,200,300,2000}
        System.out.println(ArrayUtils.toString(add));
    }

    /**
     * int getLength(final Object array):返回指定数组的长度。如果输入数组是{@code null}，则返回{@code 0}。
     * int hashCode(final Object array)：获取 hash code。
     * ArrayUtils.getLength(null)            = 0
     * ArrayUtils.getLength([])              = 0
     * ArrayUtils.getLength([null])          = 1
     * ArrayUtils.getLength([true, false])   = 2
     * ArrayUtils.getLength([1, 2, 3])       = 3
     * ArrayUtils.getLength(["a", "b", "c"]) = 3
     */
    @Test
    public void hashCodeArray() {
        String[] strings = {"100", "100X", "300P"};
        //3
        System.out.println(ArrayUtils.getLength(strings));
        //124771894
        System.out.println(ArrayUtils.hashCode(strings));

        System.out.println("Array.getLength(strings) = " + Array.getLength(strings));
        //Array.getLength(strings) = 3
    }

    /**
     * boolean isSameType(final Object array1, final Object array2): 检查两个数组的元素是否为同一类型。
     * <p>
     * 检查两个数组的长度是否相同，将空数组视为长度0
     * boolean isSameLength(final boolean[] array1, final boolean[] array2): 支持操作8种基本数据类型
     * boolean isSameLength(final Object[] array1, final Object[] array2)
     * * array1 第一个数组，可以是 null
     * * array2 第二个数组，可以是 null
     * * 如果数组长度匹配，则返回true，将null视为空数组
     */
    @Test
    public void isSameType() {
        String[] strings1 = {"100", "100X", "300P"};
        String[] strings2 = {"20", "10", "50", "78", "52"};
        Integer[] integers = {1, 2, 3, 4, 5};

        //false
        System.out.println(ArrayUtils.isSameType(strings1, integers));
        //true
        System.out.println(ArrayUtils.isSameType(strings1, strings2));
        //true
        System.out.println(ArrayUtils.isSameLength(strings2, integers));
    }

    /**
     * boolean isSorted(final boolean[] array): 支持操作8种基本数据类型
     * boolean isSorted(final T[] array): 检查提供的数组是否按自然顺序排序。如果 array 为null或者长度小于2，则恒返回 true.
     */
    @Test
    public void isSorted() {
        String[] strings1 = {"aa", "bb", "c"};
        Integer[] integers = {1, 2, 13, 4, 5};
        //true
        System.out.println(ArrayUtils.isSorted(strings1));
        //false
        System.out.println(ArrayUtils.isSorted(integers));
    }

    /**
     * 反转给定数组的顺序,当 array 是null，则不会有任何效果。
     * 在给定范围内[startIndexInclusive,endIndexExclusive]反转给定数组的顺序。
     * reverse(final double[] array) ：支持操作8种基本数据类型
     * reverse(final double[] array, final int startIndexInclusive, final int endIndexExclusive)
     * reverse(final Object[] array)
     */
    @Test
    public void reverse() {
        Integer[] integers = {1, 2, 13, 4, 5};
        ArrayUtils.reverse(integers);
        //{5,4,13,2,1}
        System.out.println(ArrayUtils.toString(integers));
    }


    /**
     * 改变给定数组的顺序
     * shift(final boolean[] array, final int offset)：支持操作8种基本数据类型
     * shift(final Object[] array, int startIndexInclusive, int endIndexExclusive, int offset)
     * f
     * offset：旋转元素的偏移量，如果偏移量大于要旋转的元素数，则有效偏移量等于要旋转的元素数的模。
     */
    @Test
    public void shift() {
        Integer[] integers = {1, 2, 13, 4, 5};
        ArrayUtils.shift(integers, 2);
        //{4,5,1,2,13}
        System.out.println(ArrayUtils.toString(integers));
        ArrayUtils.shift(integers, 4);
        //{5,1,2,13,4}
        System.out.println(ArrayUtils.toString(integers));
    }

    /**
     * 使用 Fisher 算法随机改变数组的元素顺序。
     * shuffle(final byte[] array)
     * T[] toArray(final T... items)
     * 创建类型安全的泛型数组。这个方法只有在提供相同类型的参数时才有意义，这样编译器才能推断出数组本身的类型。
     */
    @Test
    public void shuffle() {
        Integer[] integers = {1, 2, 13, 4, 5};
        ArrayUtils.shuffle(integers);
        //{1,4,2,13,5}
        System.out.println(ArrayUtils.toString(integers));

        ArrayUtils.shuffle(integers);
        //{1,13,4,5,2}
        System.out.println(ArrayUtils.toString(integers));
    }

    /**
     * 将包装类型转为基本类型，valueForNull 是当元素为 null 时的默认值，
     * 否则如果存在 null 元素，而又没有设置默认值，则会出现空指针异常。
     * double[] toPrimitive(final Double[] array)
     * double[] toPrimitive(final Double[] array, final double valueForNull)
     */
    @Test
    public void toPrimitive() {
        Integer[] integers = {1, 2, 13, null, 5};
        int[] ints = ArrayUtils.toPrimitive(integers, -1);
        //{1,2,13,-1,5}
        System.out.println(ArrayUtils.toString(ints));
    }

    /**
     * 将基本类型准尉包装类型的数组
     * Double[] toObject(final double[] array): 支持操作8种基本数据类型
     * <p>
     * 将数组对象转为字符串，如 {a，b}，如果 array 为 null，则返回 {}。
     * String toString(final Object array)
     * String toString(final Object array, final String stringIfNull)
     * * stringIfNull：如果数组是 null，则返回的字符串
     * <p>
     * 将整个数组中的所有元素转为字符串数组。
     * String[] toStringArray(final Object[] array)
     * String[] toStringArray(final Object[] array, final String valueForNullElements)
     */
    @Test
    public void toObject() {
        float[] floats = {1, 2, 13, 0, 5};
        Float[] toObject = ArrayUtils.toObject(floats);
        //{1.0,2.0,13.0,0.0,5.0}
        System.out.println(ArrayUtils.toString(toObject));

        Integer[] integers = {1, 2, 13, null, 5};
        String string = ArrayUtils.toString(integers);
        //{1,2,13,<null>,5}
        System.out.println(string);

        //{}
        System.out.println(ArrayUtils.toString(null, "{}"));

        String[] stringArray = ArrayUtils.toStringArray(toObject);
        //{1.0,2.0,13.0,0.0,5.0}
        System.out.println(ArrayUtils.toString(stringArray));
    }

    /**
     * boolean contains(final Object[] array, final Object objectToFind): 检查 objectToFind 是否在给定数组 array 中
     * 如果传入{@code null}数组，则该方法返回{@code false}。
     */
    @Test
    public void testContains() {
        String[] arrs = {"1", "22", "33", null};
        //true
        System.out.println(ArrayUtils.contains(arrs, "1"));
        //true
        System.out.println(ArrayUtils.contains(arrs, null));
        //false
        System.out.println(ArrayUtils.contains(arrs, "rt"));
        //false
        System.out.println(ArrayUtils.contains(null, null));
    }

    /**
     * 查找数组中给定值的索引，如果找不到或者 array为 null，则返回 -1，索引从0开始, startIndex：起始搜索位置
     * int indexOf(final boolean[] array, final boolean valueToFind)
     * int indexOf(final boolean[] array, final boolean valueToFind, int startIndex)
     * 支持操作8种基本数据类型
     * int indexOf(final Object[] array, final Object objectToFind)
     * int indexOf(final Object[] array, final Object objectToFind, int startIndex)
     */
    @Test
    public void testIndexOf() {
        String[] toArray = ArrayUtils.toArray("中国", "美国", "英国", "韩国");
        //3
        System.out.println(ArrayUtils.indexOf(toArray, "韩国"));
        //-1
        System.out.println(ArrayUtils.indexOf(toArray, "法国"));
    }

    /**
     * 在数组中查找给定值的最后一个索引。如果找不到，或者 array 为 null，则返回-1
     * int lastIndexOf(final boolean[] array, final boolean valueToFind)
     * int lastIndexOf(final Object[] array, final Object objectToFind)
     * 支持操作8种基本数据类型
     * int lastIndexOf(final boolean[] array, final boolean valueToFind, int startIndex)
     */
    @Test
    public void testLastIndexOf() {
        String[] toArray = ArrayUtils.toArray("中国", "美国", "英国", "韩国");
        //3
        System.out.println(ArrayUtils.lastIndexOf(toArray, "韩国"));
        //-1
        System.out.println(ArrayUtils.lastIndexOf(toArray, "法国"));
    }

    /**
     * 将元素插入给定索引处的数组中(从零开始)，返回一个新数组，源数组不变，如果 index < 0 or index > array.length} 则异常
     * boolean[] insert(final int index, final boolean[] array, final boolean... values)
     * 支持操作8种基本数据类型
     * T[] insert(final int index, final T[] array, final T... values)
     * <p>
     * * ArrayUtils.insert(index, null, null)      = null
     * * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * * ArrayUtils.insert(index, null, values)    = null
     */
    @Test
    public void testInsert() {
        String[] toArray = ArrayUtils.toArray("中国", "美国", "英国", "韩国");
        String[] insert = ArrayUtils.insert(0, toArray, "赵国", "齐国");

        //{中国,美国,英国,韩国}
        System.out.println(ArrayUtils.toString(toArray));
        //{赵国,齐国,中国,美国,英国,韩国}
        System.out.println(ArrayUtils.toString(insert));
    }

    /**
     * 返回在给定索引处是否可以安全地访问给定数组，即指定的索引是否有效。
     * boolean isArrayIndexValid(T[] array, int index)
     */
    @Test
    public void testIsArrayIndexValid() {
        String[] toArray = ArrayUtils.toArray("中国", "美国", "英国", "韩国");

        //true
        System.out.println(ArrayUtils.isArrayIndexValid(toArray, 3));
        //false
        System.out.println(ArrayUtils.isArrayIndexValid(toArray, 4));
    }

    /**
     * 检查对象数组是否为空，null 也是空。
     * boolean isEmpty(final boolean[] array)：支持操作8种基本数据类型
     * boolean isEmpty(final Object[] array)
     * boolean isNotEmpty(final boolean[] array)：支持操作8种基本数据类型
     * boolean isNotEmpty(final T[] array)
     */
    @Test
    public void testIsEmpty() {
        String[] toArray = ArrayUtils.toArray("中国", "美国", "英国", "韩国");

        //false
        System.out.println(ArrayUtils.isEmpty(toArray));
        //true
        System.out.println(ArrayUtils.isNotEmpty(toArray));
    }

    /**
     * 如果 array 为 null 或者为空，则返回一个空数组，否则返回数组本身。
     * float[] nullToEmpty(final float[] array): 支持操作8种基本数据类型
     * Object[] nullToEmpty(final Object[] array)
     * T[] nullToEmpty(final T[] array, final Class type)
     */
    @Test
    public void testNullToEmpty() {
        String[] toArray = new String[0];
        Object[] objects = null;

        String[] nullToEmpty1 = ArrayUtils.nullToEmpty(toArray);
        Object[] nullToEmpty2 = ArrayUtils.nullToEmpty(objects);

        //{}
        System.out.println(ArrayUtils.toString(nullToEmpty1));
        //{}
        System.out.println(ArrayUtils.toString(nullToEmpty2));
    }

    /**
     * 从指定数组中移除指定位置的元素, 返回新数组，源数据不变，所有后续元素都向左移动（从索引中减去一个）。
     * 如果输入数组是 null ，则将引发 indexOutfBoundsException，因为在这种情况下，无法指定有效的索引。
     * int[] remove(final int[] array, final int index): 支持操作8种基本数据类型
     * T[] remove(final T[] array, final int index)
     * <p>
     * ArrayUtils.remove(["a"], 0)           = []
     * ArrayUtils.remove(["a", "b"], 0)      = ["b"]
     * ArrayUtils.remove(["a", "b"], 1)      = ["a"]
     * ArrayUtils.remove(["a", "b", "c"], 1) = ["a", "c"]
     * <p>
     * 删除由索引指定的多个数组元素，返回新数组，源数据不变
     * boolean[] removeAll(final boolean[] array, final int... indices)：支持操作8种基本数据类型
     * removeAll(final Object array, final int... indices)
     * <p>
     * ArrayUtils.removeAll([1], 0)             = []
     * ArrayUtils.removeAll([2, 6], 0)          = [6]
     * ArrayUtils.removeAll([2, 6], 0, 1)       = []
     * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
     * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
     * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
     */
    @Test
    public void testRemove() {
        String[] toArray = ArrayUtils.toArray("中国", "美国", "英国", "韩国");
        String[] remove = ArrayUtils.remove(toArray, 0);

        //{美国,英国,韩国}
        System.out.println(ArrayUtils.toString(remove));
        //{美国,韩国}
        System.out.println(ArrayUtils.toString(ArrayUtils.removeAll(toArray, 0, 2)));
        //{中国,美国,英国,韩国}
        System.out.println(ArrayUtils.toString(toArray));
        System.out.println(remove == toArray); //false
    }

    /**
     * 从指定数组中移除指定的所有元素，所有后续元素都向左移动（从索引中减去一个）。返回新数组，源数据不变。
     * 如果数组不包含这样的元素，则不会从数组中移除任何元素。如果输入数组是 null，则返回 null
     * int[] removeAllOccurences(final int[] array, final int element)：支持操作8种基本数据类型
     * T[] removeAllOccurences(final T[] array, final T element)
     */
    @Test
    public void removeAllOccurences() {
        String[] strings1 = {"aa", "bb", "c", "aa"};
        String[] strings = ArrayUtils.removeAllOccurences(strings1, "aa");
        //{aa,bb,c,aa}
        System.out.println(ArrayUtils.toString(strings1));
        //{bb,c}
        System.out.println(ArrayUtils.toString(strings));
    }

    /**
     * 从指定数组中删除指定元素的第一个出现。所有后续元素都向左移动（从索引中减去一个）。如果数组不包含这样的元素，则不会从数组中删除元素。返回新数组，源数数组不变。
     * float[] removeElement(final float[] array, final float element)：支持操作8种基本数据类型
     * T[] removeElement(final T[] array, final Object element)
     * <p>
     * ArrayUtils.removeElement(null, "a")            = null
     * ArrayUtils.removeElement([], "a")              = []
     * ArrayUtils.removeElement(["a"], "b")           = ["a"]
     * ArrayUtils.removeElement(["a", "b"], "a")      = ["b"]
     * ArrayUtils.removeElement(["a", "b", "a"], "a") = ["b", "a"]
     * <p>
     * 从指定数组中删除指定元素的引用(按指定数量),所有后续元素左移。返回新数组，源数数组不变。
     * 对于任何要删除的元素，如果指定的数量大于原始数组中包含的数量，则除了移除现有的匹配项外，不会发生任何更改。
     * float[] removeElements(final float[] array, final float... values): 支持操作8种基本数据类型
     * T[] removeElements(final T[] array, final T... values)
     * <p>
     * ArrayUtils.removeElements(null, "a", "b")            = null
     * ArrayUtils.removeElements([], "a", "b")              = []
     * ArrayUtils.removeElements(["a"], "b", "c")           = ["a"]
     * ArrayUtils.removeElements(["a", "b"], "a", "c")      = ["b"]
     * ArrayUtils.removeElements(["a", "b", "a"], "a")      = ["b", "a"]
     * ArrayUtils.removeElements(["a", "b", "a"], "a", "a") = ["b"]
     */
    @Test
    public void testRemoveElement() {
        String[] toArray = ArrayUtils.toArray("中国", "美国", "英国", "韩国");

        String[] removeElement = ArrayUtils.removeElement(toArray, "美国");
        //{中国,英国,韩国}
        System.out.println(ArrayUtils.toString(removeElement));

        String[] removeElements = ArrayUtils.removeElements(toArray, "美国", "韩国");
        //{中国,英国}
        System.out.println(ArrayUtils.toString(removeElements));

        //{中国,美国,英国,韩国}
        System.out.println(ArrayUtils.toString(toArray));
    }

    /**
     * 从源数组的起始与结束位置之间生成一个新数组，源数组不变。
     * 底层也是 System.arraycopy，所以后期对新数组的操作，并不会影响旧数组。
     * <p>
     * float[] subarray(final float[] array, int startIndexInclusive, int endIndexExclusive): 支持操作8种基本数据类型
     * T[] subarray(final T[] array, int startIndexInclusive, int endIndexExclusive)
     */
    @Test
    public void testSubarray() {
        String[] toArray = ArrayUtils.toArray("中国", "美国", "英国", "韩国");

        String[] subarray = ArrayUtils.subarray(toArray, 2, toArray.length);
        //{英国,韩国}
        System.out.println(ArrayUtils.toString(subarray));
    }

    /**
     * 交换给定数组中元素位置。
     * swap(final float[] array, final int offset1, final int offset2)
     * swap(final float[] array, int offset1, int offset2, int len)：支持操作8种基本数据类型
     * * array：要交换的数组，可以是{@code null}
     * * offset1：要交换的第一个元素的索引
     * * offset2：要交换的第二个元素的索引
     * * len：从给定索引开始交换的元素数
     * <ul>
     * <li>ArrayUtils.swap(["1", "2", "3"], 0, 2) -&gt; ["3", "2", "1"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3"], 0, 0) -&gt; ["1", "2", "3"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3"], 1, 0) -&gt; ["2", "1", "3"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3"], 0, 5) -&gt; ["1", "2", "3"]</li>
     * <li>ArrayUtils.swap(["1", "2", "3"], -1, 1) -&gt; ["2", "1", "3"]</li>
     * </ul>
     */
    @Test
    public void testSwap() {
        String[] toArray = ArrayUtils.toArray("中国", "美国", "英国", "韩国");

        ArrayUtils.swap(toArray, 0, toArray.length - 1);
        //{韩国,美国,英国,中国}
        System.out.println(ArrayUtils.toString(toArray));

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayUtils.swap(arr, 0, arr.length - 1);
        System.out.println(ArrayUtils.toString(arr));
        //{9,2,3,4,5,6,7,8,1}
    }

    /**
     * 将数组转为 Map 对象，此时数组必须是二位数组，第一个元素做 key，第二个元素作为 value。
     * Map toMap(final Object[] array)
     ** Map colorMap = ArrayUtils.toMap(new String[][] {
     * * {"RED", "#FF0000"},
     * * {"GREEN", "#00FF00"},
     * * {"BLUE", "#0000FF"}});
     */
    @Test
    public void testToMap() {
        String[][] toArray = {
                {"RED", "#FF0000"},
                {"GREEN", "#00FF00"},
                {"BLUE", "#0000FF"}
        };
        Map<Object, Object> colorMap = ArrayUtils.toMap(toArray);
        System.out.println(colorMap);
    }


}
