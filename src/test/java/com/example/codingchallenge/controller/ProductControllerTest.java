package com.example.codingchallenge.controller;

import com.example.codingchallenge.dto.ProductDto;
import com.example.codingchallenge.entity.Product;
import com.example.codingchallenge.mapper.ProductMapper;
import com.example.codingchallenge.repository.ProductRepository;
import com.example.codingchallenge.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = mock(ProductService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllProducts() throws Exception {
        UUID productId = UUID.randomUUID();
        Product productMock = new Product(productId,
                "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        ProductDto productDtoMock = ProductMapper.MAPPER.mapToProductDto(productMock);
        productService.createProduct(productDtoMock);

        when(productService.getAllProducts()).thenReturn(Collections.singletonList(new ProductDto()));
        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ProductDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(new ProductDto()))));
    }

    @Test
    void getProductById() throws Exception {

        UUID productId = UUID.randomUUID();
        Product productMock = new Product(productId,
                "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        ProductDto productDtoMock = ProductMapper.MAPPER.mapToProductDto(productMock);

        productService.createProduct(productDtoMock);
        doReturn(Optional.of(productDtoMock)).when(productRepository).findById(productId);

        ProductDto productDtoMockService = productService.getProductById(productId);

        when(productService.getProductById(productId)).thenReturn(new ProductDto());
        mockMvc.perform(get("/api/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ProductDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(new ProductDto())));

    }

    @Test
    void createProduct() throws Exception {

        UUID productId = UUID.randomUUID();
        Product productMock = new Product(productId,
                "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        ProductDto productDtoMock = ProductMapper.MAPPER.mapToProductDto(productMock);

        productService.createProduct(productDtoMock);
        doReturn(Optional.of(productDtoMock)).when(productRepository).findById(productId);

        ProductDto productDtoMockService = productService.getProductById(productId);


        when(productService.createProduct(any())).thenReturn(new ProductDto());
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ProductDto())))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(new ProductDto())));
    }

    @Test
    void deleteProduct() throws Exception {
        UUID productId = UUID.randomUUID();
        Product productMock = new Product(productId,
                "ASUS ROG Ally",
                699.99,
                "https://m.media-amazon.com/images/I/612qftKBfKL._AC_UF1000,1000_QL80_.jpg",
                14);

        ProductDto productDtoMock = ProductMapper.MAPPER.mapToProductDto(productMock);

        productService.createProduct(productDtoMock);
        doReturn(Optional.of(productDtoMock)).when(productRepository).findById(productId);

        ProductDto productDtoMockService = productService.getProductById(productId);


       doNothing().when(productService).deleteProduct(any());
        mockMvc.perform(delete("/api/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ProductDto())))
                .andExpect(status().isOk());
    }
}
