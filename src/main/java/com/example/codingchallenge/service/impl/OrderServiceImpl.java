package com.example.codingchallenge.service.impl;

import com.example.codingchallenge.dto.OrderDto;
import com.example.codingchallenge.dto.OrderItemDto;
import com.example.codingchallenge.entity.Order;
import com.example.codingchallenge.entity.OrderItem;
import com.example.codingchallenge.exception.OrderItemNotFoundException;
import com.example.codingchallenge.exception.OrderNotFoundException;
import com.example.codingchallenge.mapper.OrderItemMapper;
import com.example.codingchallenge.mapper.OrderMapper;
import com.example.codingchallenge.repository.OrderRepository;
import com.example.codingchallenge.service.OrderItemService;
import com.example.codingchallenge.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemService orderItemService;

    public OrderServiceImpl(@Lazy OrderRepository orderRepository, @Lazy OrderItemService orderItemService) {
        super();
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = OrderMapper.MAPPER.mapToOrder(orderDto);

        Order savedOrder = this.orderRepository.save(order);

        OrderDto savedOrderDto = OrderMapper.MAPPER.mapToOrderDto(savedOrder);

        return savedOrderDto;
    }

    @Override
    public List<OrderDto> getAllOrders() {

        List<Order> orders = this.orderRepository.findAll();

        return orders
                .stream()
                .map((order) -> OrderMapper.MAPPER.mapToOrderDto(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(UUID orderId) {
        Order order = this.orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Invalid order id: " + orderId));

        return OrderMapper.MAPPER.mapToOrderDto(order);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        UUID orderId = orderDto.getId();

        Order existingOrder = this.orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Invalid order id: " + orderId));

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
            OrderItemDto existingOrderItemDto = this.orderItemService.getOrderItemById(orderId);
            OrderItemDto updateExistingOrderItemDto = this.orderItemService.updateOrderItem(existingOrderItemDto);
            OrderItem updateExistingOrderItem = OrderItemMapper.MAPPER.mapToOrderItem(existingOrderItemDto);
            orderItems.add(updateExistingOrderItem);
        }

        existingOrder.setOrderItems(orderItems);
        existingOrder.setTotalPrice(orderDto.getTotalPrice());

        Order updatedOrder = this.orderRepository.save(existingOrder);

        return OrderMapper.MAPPER.mapToOrderDto(updatedOrder);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        this.orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderItemNotFoundException("Invalid order id: " + orderId));

        this.orderRepository.deleteById(orderId);
    }
}
