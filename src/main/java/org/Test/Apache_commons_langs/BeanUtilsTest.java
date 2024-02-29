package org.Test.Apache_commons_langs;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import org.Test.Apache_commons_langs.pojo.Department;
import org.Test.pojo.Person;
import org.Test.pojo.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Apache Commons BeanUtils 反射和内审工具库
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/13 17:16
 */
public class BeanUtilsTest {

    private static Logger logger = LoggerFactory.getLogger(BeanUtilsTest.class);

    /**
     * 这是一种浅拷贝
     *
     * Object cloneBean(final Object bean)：基于属性的 getter和 setter 克隆 bean，即使 bean类本身没有实现 Cloneable。
     * 查看 BeanUtilsBean#cloneBean 源码就可以看出，底层通过反射重新 newInstance 一个新对象，
     * 然后  PropertyUtilspBean.copyProperties 赋值就对象的属性到新对象中.
     */
    public void testCloneBean() {
        try {
            Person person = new Person(9281, "华福", new Date(), false, null);
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            Department department = new Department(1000, "开发部", personList);

            Department cloneBean = (Department) BeanUtils.cloneBean(department);
            cloneBean.setName("华安");
            //输出：Person{id=9281, name='华福', birthday=2020-04-14T08:34:05.296, isMarry=false, price=null}
            System.out.println(person);
            //输出：Department{id=1000, name='华安', personList=[Person{id=9281, name='华福', birthday=2020-04-14T08:34:05.296, isMarry=false, price=null}], dataMap=null}
            System.out.println(cloneBean);
            //true

            System.out.println(person == cloneBean.getPersonList().get(0));
            person.setName("reSetName");
            System.out.println(person);
            //Person(id=9281, name=reSetName, birthday=Wed Feb 14 01:07:14 CST 2024, marry=false, salary=null)
            System.out.println(cloneBean.getPersonList().get(0));
            //Person(id=9281, name=reSetName, birthday=Wed Feb 14 01:07:14 CST 2024, marry=false, salary=null)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testClone() {
        logger.error("[异步开启]: " + AsyncLoggerContextSelector.isSelected());
        testCloneBean();
    }

    /**
     * 拷贝只会在第一层深拷贝
     *
     * copyProperties(final Object dest, final Object orig)
     * 对于属性名称相同的所有情况，将属性值从源 orig 复制到 dest.
     * 常用于做对象克隆，或者将 Map 对象的属性值赋值给 java bean.
     */
    @Test
    public void testCopyProperties() {
        try {
            Person person = new Person(9528, "华福", new Date(), false, null);
            Person person_clone = new Person();
            BeanUtils.copyProperties(person_clone, person);
            System.out.println(person_clone);
            //Person(id=9528, name=华福, birthday=Wed Feb 14 01:10:38 CST 2024, marry=false, salary=null)
            System.out.println(person_clone == person);
            //false
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * copyProperty(final Object bean, final String name, final Object value)
     * 将指定的属性值复制到指定的目标bean，执行所需的任何类型转换.
     * 将 value 值赋值给 bean 的 name 属性，自动进行类型转换，意思是即使 name 是 flout 类型，传入 "667.99" 也能自动转换.
     * * name: 属性名称（可以嵌套/索引/映射/组合），name 属性不存在时，不会抛异常。
     */
    @Test
    public void testCopyProperty() {
        try {
            Person person = new Person(1000, "华福", DateUtil.parseDate("2022-12-13"), false, null);
            // Person 无
            BeanUtils.copyProperty(person, "price", "8899.89");
            BeanUtils.copyProperty(person, "name", 8899.89F);
            BeanUtils.copyProperty(person, "id", 8899);
            BeanUtils.copyProperty(person, "marry", null);
            BeanUtils.copyProperty(person, "birthday",  DateUtil.parseDate("2021-12-13").getTime());
            // Person 无
            BeanUtils.copyProperty(person, "x",  "11");
            System.out.println(person);
//            Person(id=8899, name=8899.89, birthday=Mon Dec 13 00:00:00 CST 2021, marry=null, salary=null)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * setProperty(final Object bean, final String name, final Object value)：设置对象的属性值, bean 可以是 Java Bean，也可以是 Map 对象。
     * 1、设置指定的属性值，根据需要执行类型转换以符合目标属性的类型.
     * 2、自动进行类型转换，属性不存在时，不影响。
     * String getProperty(final Object bean, final String name)：获取对象的属性值.
     * 1、综合了其它所有 getXxxProperty 的功能.
     * 2、如果 bean 是 POJO 对象，当其中没有对应的 name 属性时，则报错：java.lang.NoSuchMethodException: Unknown property 'name2'
     * 3、如果 bean 是普通的 map 对象，当没有 name 属性时，则返回 null.
     */
    @Test
    public void testSetProperty() {
        try {
            Department department = new Department(2001, "大数据研发中心");
            Map<String, Object> dataMap = new HashMap<>(8);
            dataMap.put("code", 200);
            dataMap.put("message", "success");
            dataMap.put("update_time", new Date());

            BeanUtils.setProperty(department, "dataMap", dataMap);
            BeanUtils.setProperty(department, "id", "120");
            BeanUtils.setProperty(department, "name", 119);
            BeanUtils.setProperty(department, "x", 119);
            // Department{id=120, name='119', personList=null, dataMap={update_time=Mon Dec 12 21:20:20 CST 2022, code=200, message=success}}
            System.out.println(department);
            //输出：大数据研发中心
            System.out.println("name=" + BeanUtils.getProperty(department, "name"));
            //map 嵌套类型，可以使用 属性名(key) 获取 map 属性中指定 key 的值， 输出：200
            //如果 list、数组等类型，则可以使用 属性名[索引] 的方式获取
            System.out.println(BeanUtils.getProperty(department, "dataMap(code)"));

            //Tue Dec 07 14:37:43 CST 2021
            System.out.println("update_time=" + BeanUtils.getProperty(dataMap, "update_time"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 泛型与反射
     * setProperty(final Object bean, final String name, final Object value)：设置对象的属性值.
     * * 1、bean 可以是 Java Bean 对象，也可以是 Map 对象，Map 对象时自动设置为 name-value 键值对
     *
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getOne(Class<T> tClass) throws Exception {
        //先通过类型获取到对象
        T obj = ReflectUtil.newInstanceIfPossible(tClass);
        //为对象设置属性，比如这是数据是查询数据库来的
        BeanUtils.setProperty(obj, "id", "P1000F");
        BeanUtils.setProperty(obj, "name", "张三");
        BeanUtils.setProperty(obj, "birthday", LocalDateTime.now());
        BeanUtils.setProperty(obj, "marry", true);
        BeanUtils.setProperty(obj, "price", 8895.65F);
        return obj;
    }

    @Test
    public void testGetOne() throws Exception {
        Map<String, Object> one = getOne(Map.class);
        //{birthday=2021-12-09T18:34:05.008, price=8895.65, name=张三, marry=true, id=P1000F}
        System.out.println(one);

        //Person{id=0, name='张三', birthday=2021-12-09T18:34:05.019, marry=true, price=8895.65}
        Person person = getOne(Person.class);
        System.out.println(person);
    }

    /**
     * Map<String, String> describe(final Object bean)
     * 将 bean 对象的属性提取到 map 中。bean 对象中只有提供了 getXxx 方法的属性才会提取，否则不提取。
     */
    public void testDescribe() {
        try {
            Department department = new Department(1002, "大数据组");
            Map<String, Object> dataMap = new HashMap<>(8);
            dataMap.put("code", 201);
            dataMap.put("message", "success");
            department.setDataMap(dataMap);
            Map<String, String> describe = BeanUtils.describe(department);
            //输出：{personList=null, dataMap={code=201, message=success}, name=大数据组, id=1002}
            System.out.println(describe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * populate(final Object bean, final Map<String, ? extends Object> properties)
     * 将 properties map 的属性值赋给 bean 中同名的属性
     */
    @Test
    public void testPopulate() {
        try {
            Map<String, Object> deptMap = new HashMap<>(8);
            deptMap.put("id", 1003);
            deptMap.put("name", "Java开发部");
            Map<String, Object> dataMap = new HashMap<>(8);
            dataMap.put("code", 202);
            dataMap.put("message", "success");
            deptMap.put("dataMap", dataMap);

            String code = BeanUtils.getProperty(dataMap, "code2");
            System.out.println("code=" + code);
            //code=null
            Department department = new Department();
            BeanUtils.populate(department, deptMap);
            //Department(id=1003, name=Java开发部, personList=null, dataMap={code=202, message=success})
            System.out.println(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * String[] getArrayProperty(final Object bean, final String name)
     * 返回指定的字符串数组。对于数组、list 等索引类型，可以使用此方法进行取值，直接返回字符串数组.
     */
    public void testArrayProperty() {
        try {
            String[] phoneArr = {"110", "120", "119"};
            User user = new User();
            BeanUtils.setProperty(user, "id", 1000);
            BeanUtils.setProperty(user, "name", "华春");
            BeanUtils.setProperty(user, "phones", phoneArr);


            //输出：User{id=1000, name='华春', phones=[110, 120, 119], address=null}
            System.out.println(user);
            String[] phones = BeanUtils.getArrayProperty(user, "phones");
            //输出：[110, 120, 119]
            System.out.println(Arrays.asList(phones));
            List<String> stringList = new ArrayList<>();
            stringList.add("ss");
            stringList.add("ll");
            stringList.add("pp");
            user.setAddress(stringList);

            //输出：[ss, ll, pp]
            String[] arrayProperty = BeanUtils.getArrayProperty(user, "address");
            System.out.println(Arrays.asList(arrayProperty));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * String getIndexedProperty(final Object bean, final String name)
     * String getIndexedProperty(final Object bean,final String name, final int index)
     * 1、获取索引类型属性的值，如数组类型、List 类型 等.
     * 2、name 是数学值，可以使用 "propertyName[i]" 格式直接指定索引
     * 3、index ：索引.
     * 4、注意索引属性对象必须存在，否则空指针异常；索引不能越界，否则索引越界异常.
     */
    @Test
    public void testIndexedProperty() {
        try {
            String[] phoneArr = {"111", "122", "119"};
            User user1 = new User();
            BeanUtils.setProperty(user1, "id", 1001);
            BeanUtils.setProperty(user1, "name", "华东");
            BeanUtils.setProperty(user1, "phones", phoneArr);

            String phone = BeanUtils.getIndexedProperty(user1, "phones[1]");
            String address = BeanUtils.getIndexedProperty(user1, "phones", 2);
            //输出：122,119
            System.out.println(phone + "," + address);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * PropertyUtilsTest.testMappedProperty()
     * PropertyUtilsTest.testNestedProperty()
     * PropertyUtilsTest.testSimpleProperty()
     */
    @Test
    public void testOrher() {
        //String getMappedProperty(final Object bean, final String name)
        //String getMappedProperty(final Object bean,final String name, final String key)
        //String getNestedProperty(final Object bean, final String name)
        //String getSimpleProperty(final Object bean, final String name)
        //
    }

    /**
     * 处理嵌套属性: https://blog.csdn.net/qq_21484461/article/details/134129489
     * 有时，JavaBean中的属性可以是其他JavaBean对象。BeanUtils允许你处理嵌套属性，即在一个JavaBean中的属性是另一个JavaBean对象。
     *
     * 获取嵌套属性
     * 要获取嵌套属性的值，你可以使用点号.来访问属性的子属性。
     */

}
