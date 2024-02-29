package org.Test.Apache_commons_langs.Collections;

import org.apache.commons.collections4.MapUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 注意: {@link org.apache.commons.collections4} 和 {@link org.apache.commons.collections}
 */
public class MapUtilsStu {

    /**
     * Boolean getBoolean(final Map<? super K, ?> map, final K key)
     * 获取 Map 的 Key 对应的 boolean 类型 value 值。
     *      1）map 等于null，或者 key 不存在，返回 null。
     *      2）value 为 布尔类型时，直接解析。
     *      3）value 为 number 类型时，0 表示 false，其余都表示 true
     *      4) value 为 String 类型时，"true" 表示 true，其余都表示 false
     * 其它类型的 getDouble、getFloat、getInteger、getLong、getString 等等也是同理。
     *
     * static <K> Boolean getBoolean(final Map<? super K, ?> map, final K key, final Boolean defaultValue)：
     * 当 getBoolean(final Map<? super K, ?> map, final K key) 返回 null 时，则使用默认值 defaultValue
     */
    @Test
    public void testGetxxx() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("flag", true);
        dataMap.put("status", "false");
        dataMap.put("pop", 0);
        dataMap.put("lol", 3);
        dataMap.put("hoh", "中国");
        Boolean flag = MapUtils.getBoolean(dataMap, "flag"); // true
        Boolean status = MapUtils.getBoolean(dataMap, "status"); // false
        // !!!val=0视为不存在 坑!!!!
        Boolean pop = MapUtils.getBoolean(dataMap, "pop"); // false
        Boolean lol = MapUtils.getBoolean(dataMap, "lol"); // true
        Boolean hoh = MapUtils.getBoolean(dataMap, "hoh"); // false
        Boolean xxx = MapUtils.getBoolean(dataMap, "xxx"); // null
        System.out.println(flag + "," + status + "," + pop + "," + lol + "," + hoh + "," + xxx);
        //true,false,false,true,false,null
    }

    //static <K> Map<?, ?> getMap(final Map<? super K, ?> map, final K key)：根据 key 获取子 Map
    @Test
    public void testGetMap() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> childDataMap = new HashMap<>();
        childDataMap.put("name", "张三");
        dataMap.put("user", childDataMap);
        dataMap.put("status", "200");

        Map<?, ?> user = MapUtils.getMap(dataMap, "user");
        Map<?, ?> status = MapUtils.getMap(dataMap, "status");
        String status1 = MapUtils.getString(dataMap, "status");
        System.out.println(user + "," + status + "," + status1);//{name=张三},null,200
    }

    //static boolean isEmpty(final Map<?,?> map)：判断 map 是否为 null 或者为空
    //static boolean isNotEmpty(final Map<?,?> map)：判断 map 是否不为 null 或者不为空
    @Test
    public void testIsEmpty() {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> childDataMap = new HashMap<>();
        childDataMap.put("name", "张三");
        System.out.println(MapUtils.isEmpty(dataMap) + "," + MapUtils.isEmpty(childDataMap));//true,false
        System.out.println(MapUtils.isNotEmpty(dataMap) + "," + MapUtils.isNotEmpty(childDataMap));//false,true
    }

    //static <K, V> Map<K, V> synchronizedMap(final Map<K, V> map)
    //返回一个线程安全的 map，遍历时必须手动同步。
    @Test
    public void testSynchronizedMap() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("flag", true);
        dataMap.put("status", "false");
        dataMap.put("pop", 0);
        dataMap.put("lol", 3);
        dataMap.put("hoh", "中国");

        Map<String, Object> synchronizedMap = MapUtils.synchronizedMap(dataMap);
        synchronized (synchronizedMap) {
            Set<String> keySet = synchronizedMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                System.out.print(next + "=" + synchronizedMap.get(next) + "\t");
            }
        }
    }

}
