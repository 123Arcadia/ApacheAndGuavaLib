package org.Test.MapStructTest.em;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Person {

    String describe;

    private String id;

    private String name;

    private int age;

    private BigDecimal source;

    private double height;

    private Date createTime;

    private Company company;

    private List<House> houses;

}