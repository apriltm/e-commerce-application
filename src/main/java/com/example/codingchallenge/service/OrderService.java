package com.example.codingchallenge.service;

import com.example.codingchallenge.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDto createOrder(OrderDto order);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(UUID orderId);

    OrderDto updateOrder(OrderDto order);

    void deleteOrder(UUID orderId);
}
