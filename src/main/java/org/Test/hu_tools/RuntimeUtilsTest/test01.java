package org.Test.hu_tools.RuntimeUtilsTest;

import cn.hutool.core.util.RuntimeUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

public class test01 {

    @Test
    public void test01() {
        String str = RuntimeUtil.execForStr("ipconfig");
        System.out.println("str = " + str);
    }
    @Test
    public void test() {
        LocalDate s = LocalDate.parse( "2018-01-01" );
        LocalDate e = LocalDate.parse( "2025-01-01" );
        System.out.println(e.isAfter(s)); // true
        String str = "qew";
        System.out.println(StringUtils.isEmpty(str));

        Device device = new Device();
        device.setType(DeviceType.RTG);
        System.out.println(device.type.name()); // RTG
        System.out.println(device.type.message); // 场桥

        System.out.println(Arrays.toString(DeviceType.values()));
    }

    @Test
    public void test02() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("1",new ArrayList<>( Arrays.asList("1","2")));
        map.put("2", new ArrayList<>(Arrays.asList("12","22")));
        System.out.println("map.values() = " + map.values());
        for (List<String> value : map.values()) {
            for (String s : value) {
                if (s.equals("12")){
                    value.remove(s);
                }
            }
        }
        System.out.println("map.values() = " + map.values());

        StringBuilder sb = new StringBuilder(5);
        sb.append("12345");
        System.out.println(sb.capacity());
        System.out.println(sb.insert(0, "[zcw]"));
        System.out.println(sb.capacity());
    }

    @Data
    class Device {
        private String name123;
        private DeviceType type;


    }

    enum DeviceType {
        FL("重叉"),RS("空叉"),RMG("单悬臂"),RTG("场桥"),ARMG("自动轨道吊"),ES("叉车");
        private String message;

        private DeviceType() {
        }

        private DeviceType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
