package com.example.codingchallenge.mapper;

import com.example.codingchallenge.dto.OrderItemDto;
import com.example.codingchallenge.entity.OrderItem;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-22T02:41:20-0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setId( orderItem.getId() );
        if ( orderItem.getPrice() != null ) {
            orderItemDto.setPrice( orderItem.getPrice() );
        }
        orderItemDto.setQuantity( orderItem.getQuantity() );

        return orderItemDto;
    }

    @Override
    public OrderItem mapToOrderItem(OrderItemDto orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setId( orderItemDto.getId() );
        orderItem.setPrice( orderItemDto.getPrice() );
        orderItem.setQuantity( orderItemDto.getQuantity() );

        return orderItem;
    }
}
