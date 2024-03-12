/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.Test.MapStructTest.Mapper.uses;

import org.Test.MapStructTest.Mapper.uses.dto.Customer;
import org.Test.MapStructTest.Mapper.uses.dto.CustomerDto;
import org.Test.MapStructTest.Mapper.uses.dto.OrderItem;
import org.Test.MapStructTest.Mapper.uses.dto.OrderItemDto;
import org.Test.MapStructTest.Mapper.uses.mapper.CustomerMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 *
 * @author Filip Hrisafov
 */
public class CustomerMapperTest {

    @Test
    public void testMapEntityToDto() {
        Long a = Long.valueOf(1);
        Long b = Long.valueOf(1);
        System.out.println(a==b); // true
    }

    @Test
    public void testMapDtoToEntity() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.id = 10L;
        customerDto.customerName = "Filip";
        OrderItemDto order1 = new OrderItemDto();
        order1.name = "Table";
        order1.quantity = 2L;
        customerDto.orders = new ArrayList<>( Collections.singleton( order1 ) );

        Customer customer = CustomerMapper.MAPPER.toCustomer( customerDto );
        System.out.println("customerDto = " + customerDto);
        System.out.println("customer = " + customer);
        //customerDto = CustomerDto(id=10, customerName=Filip, orders=[OrderItemDto(name=Table, quantity=2)])
        //customer = Customer(id=10, name=Filip, orderItems=[OrderItem(name=Table, quantity=2)])
        System.out.println(customerDto.customerName == customer.getName()); // true
        System.out.println(customerDto.id == customer.getId()); // true

        System.out.println(customer.getOrderItems().contains(customerDto.getOrders().get(0))); // false

//        System.out.println(customerDto.orders == customer.getOrderItems());

        assertThat( customer.getId() ).isEqualTo( 10 );
        assertThat( customer.getName() ).isEqualTo( "Filip" );
        assertThat( customer.getOrderItems() )
            .extracting( "name", "quantity" )
            .containsExactly( tuple( "Table", 2L ) );
    }

    @Test
    public void testEntityDtoToDto() {

        Customer customer = new Customer();
        customer.setId( 10L );
        customer.setName( "Filip" );
        OrderItem order1 = new OrderItem();
        order1.setName( "Table" );
        order1.setQuantity( 2L );
        customer.setOrderItems( Collections.singleton( order1 ) );

        CustomerDto customerDto = CustomerMapper.MAPPER.fromCustomer( customer );

        System.out.println(customer.getOrderItems().contains(customerDto.getOrders().get(0))); // false


        assertThat( customerDto.id ).isEqualTo( 10 );
        assertThat( customerDto.customerName ).isEqualTo( "Filip" );
        assertThat( customerDto.orders )
            .extracting( "name", "quantity" )
            .containsExactly( tuple( "Table", 2L ) );
    }
}
