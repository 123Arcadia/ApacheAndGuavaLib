package org.Test.MapStructTest.Mapper.uses.dto;

import lombok.Data;

/**
 * @author Filip Hrisafov
 */
@Data
public class OrderItem {

    private String name;
    private Long quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
