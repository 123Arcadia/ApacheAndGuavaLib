package org.Test.MapStructTest.em;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {
    private String address;
    private Integer house_pay;
}
