package org.Test.MapStructTest.em.enumEm;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 枚举转换String测试
 * @author zhangchenwei
 */
@AllArgsConstructor
@NoArgsConstructor
public enum EnumTest01 {
    /**
     * 枚举
     */
    第一("第一", 1), 第二("第二", 2), 第三("第三", 3), 第四("第四", 4);

    private String name;
    private int order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

//    @Override
//    public String toString() {
//        return "EnumTest01{" +
//                "name='" + name + '\'' +
//                ", order=" + order +
//                '}';
//    }
}
