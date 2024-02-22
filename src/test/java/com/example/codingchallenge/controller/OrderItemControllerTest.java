package com.example.codingchallenge.controller;

import com.example.codingchallenge.dto.OrderItemDto;
import com.example.codingchallenge.entity.Order;
import com.example.codingchallenge.entity.OrderItem;
import com.example.codingchallenge.entity.Product;
import com.example.codingchallenge.mapper.OrderItemMapper;
import com.example.codingchallenge.repository.OrderItemRepository;
import com.example.codingchallenge.service.OrderItemService;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderItemControllerTest {
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    OrderItemService orderItemService;

    @Mock
    OrderItemRepository orderItemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderItemService = mock(OrderItemService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderItemController(orderItemService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllOrderItems() throws Exception {
        UUID orderItemId = UUID.randomUUID();
        Product product = new Product(UUID.randomUUID(), "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        Order order = new Order(UUID.randomUUID(),
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                new ArrayList<>());

        OrderItem orderItemMock = new OrderItem(orderItemId,
                order,
                product,
                699.99,
                1);

        OrderItemDto orderItemDtoMock = OrderItemMapper.MAPPER.mapToOrderItemDto(orderItemMock);

        orderItemService.createOrderItem(orderItemDtoMock);

        when(orderItemService.getAllOrderItems()).thenReturn(Collections.singletonList(new OrderItemDto()));
        mockMvc.perform(get("/api/order-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrderItemDto())))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(new OrderItemDto()))))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderItemById() throws Exception {

        UUID orderItemId = UUID.randomUUID();
        Product product = new Product(UUID.randomUUID(), "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        Order order = new Order(UUID.randomUUID(),
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                new ArrayList<>());

        OrderItem orderItemMock = new OrderItem(orderItemId,
                order,
                product,
                699.99,
                1);


        OrderItemDto orderItemDtoMock = OrderItemMapper.MAPPER.mapToOrderItemDto(orderItemMock);

        orderItemService.createOrderItem(orderItemDtoMock);
        doReturn(Optional.of(orderItemDtoMock)).when(orderItemRepository).findById(orderItemId);

        OrderItemDto orderItemDtoMockService = orderItemService.getOrderItemById(orderItemId);


        when(orderItemService.getOrderItemById(orderItemId)).thenReturn(new OrderItemDto());
        mockMvc.perform(get("/api/order-items/{id}", orderItemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrderItemDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(new OrderItemDto())));

    }

    @Test
    void createOrderItem() throws Exception {

        UUID orderItemId = UUID.randomUUID();
        Product product = new Product(UUID.randomUUID(), "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        Order order = new Order(UUID.randomUUID(),
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                new ArrayList<>());

        OrderItem orderItemMock = new OrderItem(orderItemId,
                order,
                product,
                699.99,
                1);

        OrderItemDto orderItemDtoMock = OrderItemMapper.MAPPER.mapToOrderItemDto(orderItemMock);

        orderItemService.createOrderItem(orderItemDtoMock);
        doReturn(Optional.of(orderItemDtoMock)).when(orderItemRepository).findById(orderItemId);

        OrderItemDto orderItemDtoMockService = orderItemService.getOrderItemById(orderItemId);


        when(orderItemService.createOrderItem(any())).thenReturn(new OrderItemDto());
        mockMvc.perform(post("/api/order-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrderItemDto())))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(new OrderItemDto())));
    }

    @Test
    void deleteOrderItem() throws Exception {
        UUID orderItemId = UUID.randomUUID();
        Product product = new Product(UUID.randomUUID(), "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        Order order = new Order(UUID.randomUUID(),
                LocalDate.now(),
                LocalDate.now(),
                699.99,
                new ArrayList<>());

        OrderItem orderItemMock = new OrderItem(orderItemId,
                order,
                product,
                699.99,
                1);

        OrderItemDto orderItemDtoMock = OrderItemMapper.MAPPER.mapToOrderItemDto(orderItemMock);

        orderItemService.createOrderItem(orderItemDtoMock);
        doReturn(Optional.of(orderItemDtoMock)).when(orderItemRepository).findById(orderItemId);

        OrderItemDto orderItemDtoMockService = orderItemService.getOrderItemById(orderItemId);

        doNothing().when(orderItemService).deleteOrderItem(any());
        mockMvc.perform(delete("/api/order-items/{id}", orderItemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new OrderItemDto())))
                .andExpect(status().isOk());
    }
}
