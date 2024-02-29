package org.Test.MapStructTest.em;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyDTO {
    public String name;
    public String address;
    public Integer numbers;
    public Date currentDate;
    public String leader;
}