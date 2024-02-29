package org.Test.MapStructTest.em;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.checkerframework.checker.lock.qual.NewObject;

@Data
@AllArgsConstructor
@NewObject
public class House {
    private String address;
    private Integer price;
}
