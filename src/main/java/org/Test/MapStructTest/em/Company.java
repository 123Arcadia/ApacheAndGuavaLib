package org.Test.MapStructTest.em;


import lombok.Data;

import java.util.Date;

@Data
public class Company {
    public String name;
    public String address;
    public Integer numberOfPeople;
    public Date createDate;
    public String createBy;
}