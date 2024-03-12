package org.Test.MapStructTest.Clone;

import org.Test.MapStructTest.Clone.dto.CustomerDto;
import org.Test.MapStructTest.Clone.dto.OrderItemDto;
import org.Test.MapStructTest.Clone.dto.OrderItemKeyDto;
import org.Test.MapStructTest.Clone.mapper.Cloner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


/**
 * 沈拷贝一个对象
 */
public class testClone {
    @Test
    public void testMapDtoToEntity() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId( 10L );
        customerDto.setCustomerName("Jaques" );
        OrderItemDto order1 = new OrderItemDto();
        order1.setName ("Table" );
        order1.setQuantity( 2L );
        customerDto.setOrders( new ArrayList<>( Collections.singleton( order1 ) ) );
        OrderItemKeyDto key = new OrderItemKeyDto();
        key.setStockNumber( 5 );
        Map stock = new HashMap(  );
        stock.put( key, order1 );
        customerDto.setStock( stock );

        CustomerDto customer = Cloner.MAPPER.clone( customerDto );

        System.out.println("customerDto = " + customerDto);
        System.out.println("customer = " + customer);
        //customerDto = CustomerDto(id=10, customerName=Jaques, orders=[OrderItemDto(name=Table, quantity=2)], stock={OrderItemKeyDto(stockNumber=5)=OrderItemDto(name=Table, quantity=2)})
        //customer =    CustomerDto(id=10, customerName=Jaques, orders=[OrderItemDto(name=Table, quantity=2)], stock={OrderItemKeyDto(stockNumber=5)=OrderItemDto(name=Table, quantity=2)})

        assertThat(customerDto.getOrders().get(0)).isEqualTo(customer.getOrders().get(0)); //
        System.out.println(customerDto.getOrders().get(0) == customer.getOrders().get(0)); // 深拷贝
        //false
        System.out.println(customerDto.getStock() == customer.getStock());
        //false

        // check if cloned
        assertThat( customer.getId() ).isEqualTo( 10 );
        assertThat( customer.getCustomerName() ).isEqualTo( "Jaques" );
        assertThat( customer.getOrders() )
                .extracting( "name", "quantity" )
                .containsExactly( tuple( "Table", 2L ) );
        assertThat( customer.getStock()  ).isNotNull();
        assertThat( customer.getStock() ).hasSize( 1 );

        Map.Entry<OrderItemKeyDto, OrderItemDto> entry = customer.getStock().entrySet().iterator().next();
        assertThat( entry.getKey().getStockNumber() ).isEqualTo( 5 );
        assertThat( entry.getValue().getName() ).isEqualTo( "Table" );
        assertThat( entry.getValue().getQuantity() ).isEqualTo( 2L );

        // check mapper really created new objects
        assertThat( customer ).isNotSameAs( customerDto );
//        assertThat( customer.getOrders().get( 0 ) ).isNotEqualTo( order1 ); // 来着不等于
//        assertThat( entry.getKey() ).isNotEqualTo( key );
//        assertThat( entry.getValue() ).isNotEqualTo( order1 );
//        assertThat( entry.getValue() ).isNotEqualTo( customer.getOrders().get( 0 ) );
    }
}
