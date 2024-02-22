package com.example.codingchallenge.service;

import com.example.codingchallenge.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(UUID productId);

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(UUID productId);
}
