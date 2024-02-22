package com.example.codingchallenge.mapper;

import com.example.codingchallenge.dto.OrderDto;
import com.example.codingchallenge.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderDto mapToOrderDto(Order order);

    Order mapToOrder(OrderDto orderDto);
}
