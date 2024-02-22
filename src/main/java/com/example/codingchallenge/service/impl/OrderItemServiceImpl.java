package com.example.codingchallenge.service.impl;

import com.example.codingchallenge.dto.OrderDto;
import com.example.codingchallenge.dto.OrderItemDto;
import com.example.codingchallenge.dto.ProductDto;
import com.example.codingchallenge.entity.Order;
import com.example.codingchallenge.entity.OrderItem;
import com.example.codingchallenge.entity.Product;
import com.example.codingchallenge.exception.OrderItemNotFoundException;
import com.example.codingchallenge.mapper.OrderItemMapper;
import com.example.codingchallenge.mapper.OrderMapper;
import com.example.codingchallenge.mapper.ProductMapper;
import com.example.codingchallenge.repository.OrderItemRepository;
import com.example.codingchallenge.service.OrderItemService;
import com.example.codingchallenge.service.OrderService;
import com.example.codingchallenge.service.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    //@Autowired
    private final OrderItemRepository orderItemRepository;

    //@Autowired
    private final ProductService productService;

    private final OrderService orderService;

    public OrderItemServiceImpl(@Lazy OrderItemRepository orderItemRepository, @Lazy ProductService productService, @Lazy OrderService orderService) {
        super();
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.orderService = orderService;
    }


    @Override
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
       OrderItem orderItem = OrderItemMapper.MAPPER.mapToOrderItem(orderItemDto);

       OrderItem savedOrderItem = this.orderItemRepository.save(orderItem);

       OrderItemDto savedOrderItemDto = OrderItemMapper.MAPPER.mapToOrderItemDto(savedOrderItem);

       return savedOrderItemDto;
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = this.orderItemRepository.findAll();

        return orderItems
                .stream()
                .map((orderItem) -> OrderItemMapper.MAPPER.mapToOrderItemDto(orderItem))
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto getOrderItemById(UUID orderItemId) {
        OrderItem orderItem = this.orderItemRepository
                .findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("Invalid order item id: " + orderItemId));

        return OrderItemMapper.MAPPER.mapToOrderItemDto(orderItem);
    }

    @Override
    public OrderItemDto updateOrderItem(OrderItemDto orderItemDto) {
        UUID orderItemId = orderItemDto.getId();

        OrderItem existingOrderItem = this.orderItemRepository
                .findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("Invalid order item id: " + orderItemId));


        UUID productId = existingOrderItem.getProduct().getId();

        ProductDto existingProductDto = this.productService.getProductById(productId);
        ProductDto updateExistingProductDto = this.productService.updateProduct(existingProductDto);
        Product existingProduct = ProductMapper.MAPPER.mapToProduct(existingProductDto);

        existingOrderItem.setProduct(existingProduct);

        existingOrderItem.setQuantity(orderItemDto.getQuantity());

        OrderItem updatedOrderItem = this.orderItemRepository.save(existingOrderItem);

        return OrderItemMapper.MAPPER.mapToOrderItemDto(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(UUID orderItemId) {
        this.orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("Invalid order item id: " + orderItemId));

        this.orderItemRepository.deleteById(orderItemId);
    }
}
