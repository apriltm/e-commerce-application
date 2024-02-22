package com.example.codingchallenge.service;

import com.example.codingchallenge.dto.OrderItemDto;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    OrderItemDto createOrderItem(OrderItemDto orderItemDto);

    List<OrderItemDto> getAllOrderItems();

    OrderItemDto getOrderItemById(UUID orderItemId);

    OrderItemDto updateOrderItem(OrderItemDto orderItemDto);

    void deleteOrderItem(UUID orderItemId);
}
