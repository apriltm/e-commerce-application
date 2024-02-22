package com.example.codingchallenge.service.impl;

import com.example.codingchallenge.dto.ProductDto;
import com.example.codingchallenge.entity.Product;
import com.example.codingchallenge.exception.ProductNotFoundException;
import com.example.codingchallenge.mapper.ProductMapper;
import com.example.codingchallenge.repository.ProductRepository;
import com.example.codingchallenge.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product product = ProductMapper.MAPPER.mapToProduct(productDto);

        Product savedProduct = this.productRepository.save(product);

        ProductDto savedProductDto = ProductMapper.MAPPER.mapToProductDto(savedProduct);

        return savedProductDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = this.productRepository.findAll();

        return products
                .stream()
                .map((product) -> ProductMapper.MAPPER.mapToProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(UUID productId) {

        Product product = this.productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Invalid product id: " + productId));

        return ProductMapper.MAPPER.mapToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        UUID productId = productDto.getId();

        Product existingProduct = this.productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Invalid product id: " + productId));

        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setImageUrl(productDto.getImageUrl());
        existingProduct.setQuantity(productDto.getQuantity());

        Product updatedProduct = this.productRepository.save(existingProduct);

        return ProductMapper.MAPPER.mapToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(UUID productId) {
        this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Invalid product id: " + productId));

        this.productRepository.deleteById(productId);
    }
}
