package com.example.codingchallenge.controller;


import com.example.codingchallenge.dto.OrderDto;
import com.example.codingchallenge.entity.Order;
import com.example.codingchallenge.entity.OrderItem;
import com.example.codingchallenge.entity.Product;
import com.example.codingchallenge.mapper.OrderMapper;
import com.example.codingchallenge.repository.OrderRepository;
import com.example.codingchallenge.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = mock(OrderService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(orderService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllOrders() throws Exception {
        UUID orderId = UUID.randomUUID();

        Product product = new Product(UUID.randomUUID(), "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        Order orderForOrderItemMock = new Order(UUID.randomUUID(),
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                new ArrayList<>());

        OrderItem orderItemMock = new OrderItem(UUID.randomUUID(),
                orderForOrderItemMock,
                product,
                699.99,
                1
        );

        Order orderMock = new Order(orderId,
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                List.of(orderItemMock));

        OrderDto orderDtoMock = OrderMapper.MAPPER.mapToOrderDto(orderMock);

        orderService.createOrder(orderDtoMock);

        when(orderService.getAllOrders()).thenReturn(Collections.singletonList(new OrderDto()));
        mockMvc.perform(get("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrderDto())))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(new OrderDto()))))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderById() throws Exception {

        UUID orderId = UUID.randomUUID();

        Product product = new Product(UUID.randomUUID(), "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        Order orderForOrderItemMock = new Order(UUID.randomUUID(),
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                new ArrayList<>());

        OrderItem orderItemMock = new OrderItem(UUID.randomUUID(),
                orderForOrderItemMock,
                product,
                699.99,
                1
        );

        Order orderMock = new Order(orderId,
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                List.of(orderItemMock));



        OrderDto orderDtoMock = OrderMapper.MAPPER.mapToOrderDto(orderMock);

        orderService.createOrder(orderDtoMock);
        doReturn(Optional.of(orderDtoMock)).when(orderRepository).findById(orderId);

        OrderDto orderDtoMockService = orderService.getOrderById(orderId);

        when(orderService.getOrderById(orderId)).thenReturn(new OrderDto());
        mockMvc.perform(get("/api/orders/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrderDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(new OrderDto())));

    }

    @Test
    void createOrder() throws Exception {

        UUID orderId = UUID.randomUUID();

        Product product = new Product(UUID.randomUUID(), "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        Order orderForOrderItemMock = new Order(UUID.randomUUID(),
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                new ArrayList<>());

        OrderItem orderItemMock = new OrderItem(UUID.randomUUID(),
                orderForOrderItemMock,
                product,
                699.99,
                1
        );

        Order orderMock = new Order(orderId,
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                List.of(orderItemMock));

        OrderDto orderDtoMock = OrderMapper.MAPPER.mapToOrderDto(orderMock);

        orderService.createOrder(orderDtoMock);
        doReturn(Optional.of(orderDtoMock)).when(orderRepository).findById(orderId);

        OrderDto orderDtoMockService = orderService.getOrderById(orderId);

        when(orderService.createOrder(any())).thenReturn(new OrderDto());
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrderDto())))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(new OrderDto())));
    }

    @Test
    void deleteOrder() throws Exception {
        UUID orderId = UUID.randomUUID();

        Product product = new Product(UUID.randomUUID(), "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        Order orderForOrderItemMock = new Order(UUID.randomUUID(),
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                new ArrayList<>());

        OrderItem orderItemMock = new OrderItem(UUID.randomUUID(),
                orderForOrderItemMock,
                product,
                699.99,
                1
        );

        Order orderMock = new Order(orderId,
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                List.of(orderItemMock));

        OrderDto orderDtoMock = OrderMapper.MAPPER.mapToOrderDto(orderMock);

        orderService.createOrder(orderDtoMock);
        doReturn(Optional.of(orderDtoMock)).when(orderRepository).findById(orderId);

        doNothing().when(orderService).deleteOrder(any());
        mockMvc.perform(delete("/api/orders/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrderDto())))
                .andExpect(status().isOk());
    }
}
