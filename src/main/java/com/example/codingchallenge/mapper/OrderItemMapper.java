package com.example.codingchallenge.mapper;

import com.example.codingchallenge.dto.OrderItemDto;
import com.example.codingchallenge.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemMapper {
    OrderItemMapper MAPPER = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDto mapToOrderItemDto(OrderItem orderItem);

    OrderItem mapToOrderItem(OrderItemDto orderItemDto);
}
