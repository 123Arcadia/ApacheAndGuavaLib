package org.Test.MapStructTest.Clone.dto;

import lombok.Data;

/**
 * @author Sjaak Derksen
 */
@Data
public class OrderItemKeyDto {

    private long stockNumber;

    public long getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(long stockNumber) {
        this.stockNumber = stockNumber;
    }
}
