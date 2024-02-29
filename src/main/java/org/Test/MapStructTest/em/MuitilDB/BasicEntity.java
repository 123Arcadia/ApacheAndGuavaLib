package org.Test.MapStructTest.em.MuitilDB;

import lombok.Data;

import java.util.Date;

@Data
public class BasicEntity {

    // 补充Son有二SOnDto中没有的Date类型
    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private int _ROW;
}
