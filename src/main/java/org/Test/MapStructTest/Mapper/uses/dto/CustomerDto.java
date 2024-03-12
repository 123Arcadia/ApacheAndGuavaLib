/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.Test.MapStructTest.Mapper.uses.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Filip Hrisafov
 */
@Data
public class CustomerDto {

    public Long id;
    public String customerName;
    public List<OrderItemDto> orders;
}
