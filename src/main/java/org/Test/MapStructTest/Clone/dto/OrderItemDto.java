package org.Test.MapStructTest.Clone.dto;

import lombok.Data;

/**
 * @author Sjaak Derksen
 */
@Data
public class OrderItemDto {

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