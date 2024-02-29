package org.Test.Apache_commons_langs.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtilsTest {

    /**
     * boolean isNumeric(final CharSequence cs)：检查 cs 是否只包含 Unicode 数字，小数点不是 Unicode 数字，返回 false
     * 该方法不允许前导符号，无论是正号还是负号
     * * StringUtils.isNumeric(null)   = false
     * * StringUtils.isNumeric("")     = false
     * * StringUtils.isNumeric("  ")   = false
     * * StringUtils.isNumeric("123")  = true
     * * StringUtils.isNumeric("\u0967\u0968\u0969")  = true
     * * StringUtils.isNumeric("12 3") = false
     * * StringUtils.isNumeric("ab2c") = false
     * * StringUtils.isNumeric("12-3") = false
     * * StringUtils.isNumeric("12.3") = false
     * * StringUtils.isNumeric("-123") = false
     * * StringUtils.isNumeric("+123") = false
     */
    @Test
    public void testIsNumeric() {
        System.out.println(StringUtils.isNumeric("00389238920823"));//true
    }

    /**
     * void testSubstring()：从指定的字符串中获取子字符串以避免异常,负数可用于从字符串结尾开始/结束
     * * <pre>
     *  * StringUtils.substring(null, *, *)    = null
     *  * StringUtils.substring("", * ,  *)    = "";
     *  * StringUtils.substring("abc", 0, 2)   = "ab"
     *  * StringUtils.substring("abc", 2, 0)   = ""
     *  * StringUtils.substring("abc", 2, 4)   = "c"
     *  * StringUtils.substring("abc", 4, 6)   = ""
     *  * StringUtils.substring("abc", 2, 2)   = ""
     *  * StringUtils.substring("abc", -2, -1) = "b"
     *  * StringUtils.substring("abc", -4, 2)  = "ab"
     *  * </pre>
     */
    @Test
    public void testSubstring() {
        System.out.println(StringUtils.substring("430000", 0, 2));
        System.out.println(StringUtils.substring("4", 0, 2));
        System.out.println(StringUtils.substring("", 0, 2));
        System.out.println(StringUtils.substring(null, 0, 2));
    }

    /**
     * String abbreviate(final String str, final int maxWidth)：
     * 对 str 字符串进行省略号缩写，maxWidth 表示长度，必须大于等于 4
     */
    @Test
    public void abbreviate() {
        System.out.println(StringUtils.abbreviate("abcdefg", 7));//= "abcdefg"
        System.out.println(StringUtils.abbreviate("abcdefg", 8));//= "abcdefg"
        System.out.println(StringUtils.abbreviate("abcdefg", 4));//= "a..."
        System.out.println(StringUtils.abbreviate("a", 3));// = a..
        System.out.println(StringUtils.abbreviate("abcdefg", 3));// = IllegalArgumentException, Minimum abbreviation width is 4
    }

    /**
     * int compare(final String str1, final String str2)：按字典顺序比较两个字符串，不忽略大小写差异，null 值小于非 null值，两个 null 值视为相等.
     * int compare(final String str1, final String str2, final boolean nullIsLess)：
     * 按字典顺序比较两个字符串，不忽略大小写差异，null 值小于非 null值，两个 null 值视为相等, nullIsLess 指示 null 值是否小于非null值
     * int compareIgnoreCase(final String str1, final String str2) ：按字典顺序比较两个字符串，忽略大小写差异，null 值小于非 null值，两个 null 值视为相等.
     * int compareIgnoreCase(final String str1, final String str2, final boolean nullIsLess)
     * * StringUtils.compareIgnoreCase(null, null)   = 0
     * * StringUtils.compareIgnoreCase(null , "a")   < 0
     * * StringUtils.compareIgnoreCase("a", null)    > 0
     * * StringUtils.compareIgnoreCase("abc", "abc") = 0
     * * StringUtils.compareIgnoreCase("abc", "ABC") = 0
     * * StringUtils.compareIgnoreCase("a", "b")     < 0
     * * StringUtils.compareIgnoreCase("b", "a")     > 0
     * * StringUtils.compareIgnoreCase("a", "B")     < 0
     * * StringUtils.compareIgnoreCase("A", "b")     < 0
     * * StringUtils.compareIgnoreCase("ab", "ABC")  < 0
     */
    @Test
    public void testCompareIgnoreCase() {
        System.out.println(StringUtils.compareIgnoreCase("9", "445"));//5
        System.out.println(StringUtils.compareIgnoreCase(null, "a"));//-1
        System.out.println(StringUtils.compareIgnoreCase("abc", "ABC"));//0
        System.out.println("--------------");
        System.out.println(StringUtils.compare("abc", "ABC"));//32
        System.out.println("--------------");
        System.out.println(StringUtils.compare(null, "a", true));//-1
        System.out.println(StringUtils.compare(null, "a", false));//1
    }

    /**
     * T defaultIfBlank(final T str, final T defaultStr)：如果 str 为 null 或者空字符串，空白字符串，则返回默认值.
     * T defaultIfEmpty(final T str, final T defaultStr)：如果 str 为 null 或者空字符串，则返回默认值.
     * String defaultString(final String str, final String defaultStr)：如果 str 为 null, 则返回默认值.
     */
    @Test
    public void testDefaultIfBlank() {
        String blank1 = StringUtils.defaultIfBlank(null, "*");
        System.out.println(blank1);//*

        System.out.println("--------defaultIfBlank------");
        System.out.println(StringUtils.defaultIfBlank(null, "*"));//*
        System.out.println(StringUtils.defaultIfBlank("", "*"));//*
        System.out.println(StringUtils.defaultIfBlank(" ", "*"));//*
        System.out.println(StringUtils.defaultIfBlank("bat", "*"));//bat
        System.out.println(StringUtils.defaultIfBlank("", null));//null

        System.out.println("--------defaultIfEmpty------");
        System.out.println("" + StringUtils.defaultIfEmpty(null, "*"));//*
        System.out.println(StringUtils.defaultIfEmpty("", "*"));//*
        System.out.println(StringUtils.defaultIfEmpty(" ", "*"));//空白字符串
        System.out.println(StringUtils.defaultIfEmpty("bat", "*"));//bat
        System.out.println(StringUtils.defaultIfEmpty("", null));//null

        System.out.println("--------defaultString------");
        System.out.println(StringUtils.defaultString(null, "*"));//*
        System.out.println(StringUtils.defaultString("", "*"));//空字符串
        System.out.println(StringUtils.defaultString(" ", "*"));//空白字符串
        System.out.println(StringUtils.defaultString("bat", "*"));//bat
        System.out.println(StringUtils.defaultString("", null));//字符串
    }

    /**
     * String join(final Object[] array, final String separator)
     * String join(final Iterable<?> iterable, final String separator)
     * String join(final Iterator<?> iterator, final String separator)
     * ：对可迭代的元素使用指定字符串进行连接
     * * StringUtils.join(null, *)               = null
     * * StringUtils.join([], *)                 = ""
     * * StringUtils.join([null], *)             = ""
     * * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * * StringUtils.join(["a", "b", "c"], null) = "abc"
     * * StringUtils.join([null, "", "a"], ';')  = ";;a"
     */
    @Test
    public void testJoin() {
        List<String> list = new ArrayList();
        list.add("01001");
        list.add(null);
        list.add("01003");

        String join1 = StringUtils.join(list, ",");
        String join2 = StringUtils.join(list, "");
        System.out.println(join1);//01001,,01003
        System.out.println(join2);//0100101003
    }

    /**
     * isAlphanumeric(CharSequence cs)
     * 判断字符串是否是希腊字母与数字组成，不忽略空格
     * isAlphanumericSpace(CharSequence cs)
     * 判断字符串是否是希腊字母与数字组成，忽略空格
     */
    @Test
    public void testIsAlphanumeric() {
        System.out.println(StringUtils.isAlphanumeric("ab c"));//false
        System.out.println(StringUtils.isAlphanumeric("abC12"));//true
        System.out.println(StringUtils.isAlphanumeric("ABC"));//true
        System.out.println(StringUtils.isAlphanumeric("ABC4378"));//true
        System.out.println(StringUtils.isAlphanumeric("但是"));//true
        System.out.println(StringUtils.isAlphanumericSpace("ab c"));//true
        System.out.println(StringUtils.isAlphanumericSpace("ab cAUI988"));//true
        System.out.println(StringUtils.isAlphanumericSpace("ab cAUI988.89"));//false
        System.out.println(StringUtils.isAlphanumericSpace("发就邓"));//true
    }

    /**
     * String strip(final String str)
     * 1、从字符串的开头和结尾删除空格(空白字符串)，类似 trim(xxx)
     * String[] stripAll(final String... strs)
     * 1、批量删除，每次返回一个新数组，长度为零除外。
     * * StringUtils.stripAll(null)             = null
     * * StringUtils.stripAll([])               = []
     * * StringUtils.stripAll(["abc", "  abc"]) = ["abc", "abc"]
     * * StringUtils.stripAll(["abc  ", null])  = ["abc", null]
     */
    @Test
    public void stripTest1() {
        // null
        System.out.println(StringUtils.strip(null));
        // ""
        System.out.println(StringUtils.strip(""));
        // ""
        System.out.println(StringUtils.strip("   "));
        // "abc"
        System.out.println(StringUtils.strip("abc"));
        // "abc"
        System.out.println(StringUtils.strip("  abc"));
        // "abc"
        System.out.println(StringUtils.strip("abc  "));
        // "abc"
        System.out.println(StringUtils.strip(" abc "));
        // "ab c"
        System.out.println(StringUtils.strip(" ab c "));
        // ab c
        System.out.println(StringUtils.strip(" ab c \t\n\r  "));
        // ab c
        System.out.println(StringUtils.trim(" ab c \t\n\r  "));
    }

    /**
     * String strip(String str, final String stripChars)
     * 1、从字符串的开头和结尾删除指定的一组字符(stripChars)
     * 2、会从 str 的开头和结尾删除出现的 stripChars 字符串中的全部字符
     * * StringUtils.strip(null, *)          = null
     * * StringUtils.strip("", *)            = ""
     * * StringUtils.strip("  abcyx", "xyz") = "  abc"
     */
    @Test
    public void stripTest2() {
        // null
        System.out.println(StringUtils.strip(null, null));
        // ""
        System.out.println(StringUtils.strip("", null));
        // ""
        System.out.println(StringUtils.strip("   ", null));
        // "abc"
        System.out.println(StringUtils.strip("abc", null));
        // "abc"
        System.out.println(StringUtils.strip("  abc", null));
        // "abc"
        System.out.println(StringUtils.strip("abc  ", null));
        // "abc"
        System.out.println(StringUtils.strip(" abc ", null));
        // "ab c"
        System.out.println(StringUtils.strip(" ab c ", null));
        // "ab c"
        System.out.println(StringUtils.strip(" ab c \t\n\r  ", null));
        // "  abc"
        System.out.println(StringUtils.strip("  abcyx", "xyz"));
        // 1,2,3,4,5
        System.out.println(StringUtils.strip("[1,2,3,4,5]", "[]"));
    }

    /**
     * String stripEnd(final String str, final String stripChars)
     * 1、从字符串的结尾删除指定的一组字符(stripChars)。
     * * StringUtils.stripEnd(null, *)          = null
     * * StringUtils.stripEnd("", *)            = ""
     * * StringUtils.stripEnd("abc", "")        = "abc"
     * * StringUtils.stripEnd("abc", null)      = "abc"
     * * StringUtils.stripEnd("  abc", null)    = "  abc"
     * * StringUtils.stripEnd("abc  ", null)    = "abc"
     * * StringUtils.stripEnd(" abc ", null)    = " abc"
     * * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
     * * StringUtils.stripEnd("120.00", ".0")   = "12"
     * String stripStart(final String str, final String stripChars)
     * 1、从字符串的开头删除指定的一组字符(stripChars)
     */
    @Test
    public void stripTest3() {
        // "12"
        System.out.println(StringUtils.stripEnd("120.00", ".0"));
        // "abc"
        System.out.println(StringUtils.stripStart("yxabc  ", "xyz"));
    }


    /**
     * 字符串中字符/字符串出现次数: countMatches
     * countMatches: deleteWhitespace
     */
    @Test
    public void countTest() {
        String str = "123456789011";
        System.out.println(StringUtils.countMatches(str, "1"));
        // 3
        //字符串去除空白字符
        StringUtils.deleteWhitespace("   ab  c  "); // "abc"
//        StringUtils.deleteWhitespace(null)         = null
//        StringUtils.deleteWhitespace("")           = ""
//        StringUtils.deleteWhitespace("abc")        = "abc"
//        StringUtils.deleteWhitespace("   ab  c  ") = "abc"

        // 字符串差异
        //StringUtils.difference("abcde", "abxyz"); // "xyz"
        //StringUtils.difference("abcde", "xyz"); // "xyz"

        // 字符串是否已某个后缀结束
        StringUtils.endsWith("ABCDEF", "def"); // false
        StringUtils.endsWithAny("abcXYZ", "def", "XYZ"); // true
        StringUtils.endsWithIgnoreCase("ABCDEF", "def"); // true
        //StringUtils.endsWithAny(null, null)      = false
        //StringUtils.endsWithAny(null, new String[] {"abc"})  = false
        //StringUtils.endsWithAny("abcxyz", null)     = false
        //StringUtils.endsWithAny("abcxyz", new String[] {""}) = true
        //StringUtils.endsWithAny("abcxyz", new String[] {"xyz"}) = true
        //StringUtils.endsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
        //StringUtils.endsWithAny("abcXYZ", "def", "XYZ") = true
        //StringUtils.endsWithAny("abcXYZ", "def", "xyz") = false


        //字符串是否空，null
        // StringUtils.isEmpty(): cs == null || cs.length() == 0;
        StringUtils.isEmpty("");        // true
        StringUtils.isEmpty(" ");       // false
        StringUtils.isNotEmpty(" ");    // true

        //(StringUtils.isBlank: true if the CharSequence is null, empty or whitespace only
        System.out.println(StringUtils.isBlank("")); //true
        System.out.println(StringUtils.isBlank(" ")); //true

        //字符串 join
        char[] chars = {'a','2','b','3'};
        System.out.println(StringUtils.join(chars, '-'));  // "a--b--c"
        //StringUtils.join(null)            = null
        //StringUtils.join([])              = ""
        //StringUtils.join([null])          = ""
        //StringUtils.join(["a", "b", "c"]) = "abc"
        //StringUtils.join([null, "", "a"]) = "a"
        List<String> stringList = new ArrayList<>(Arrays.asList("aa","bb"));
        String res = StringUtils.join(stringList, "--"); // aa--bb
        System.out.println("res = " + res); //res =


    }

    /**
     * 字符串strip:
     * 类似 trim, trim 是删除前后空格，而 trip 删除空白字符
     */
    @Test
    public void stripTest4() {
        String strip = StringUtils.strip(" ab c "); // "ab c"
//        System.out.println("strip = " + strip);
        String trim = StringUtils.trim(" ab c "); // "ab c"
//        System.out.println("trim = " + trim); // "ab c"
        //StringUtils.strip(null, *)          = null
        //StringUtils.strip("", *)            = ""
        //StringUtils.strip("abc", null)      = "abc"
        //StringUtils.strip("  abc", null)    = "abc"
        //StringUtils.strip("abc  ", null)    = "abc"
        //StringUtils.strip(" abc ", null)    = "abc"
        //StringUtils.strip("  abcyx", "xyz") = "  abc"

    }


    /**
     * 字符串截断
     *     StringUtils.truncate("abcdefg", 4);  // "abcd"
     */
    @Test
    public void truncateTest() {
        String str = "abcdefg";
        System.out.println(StringUtils.truncate(str, 4)); // "abcd"
        //StringUtils.truncate(null, 0)       = null
        //StringUtils.truncate(null, 2)       = null
        //StringUtils.truncate("", 4)         = ""
        //StringUtils.truncate("abcdefg", 4)  = "abcd"
        //StringUtils.truncate("abcdefg", 6)  = "abcdef"
        //StringUtils.truncate("abcdefg", 7)  = "abcdefg"
        //StringUtils.truncate("abcdefg", 8)  = "abcdefg"
        //StringUtils.truncate("abcdefg", -1) = throws an IllegalArgumentException

    }


}
