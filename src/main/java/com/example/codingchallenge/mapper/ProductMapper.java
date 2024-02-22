package com.example.codingchallenge.mapper;

import com.example.codingchallenge.dto.ProductDto;
import com.example.codingchallenge.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto mapToProductDto(Product product);

    Product mapToProduct(ProductDto productDto);
}
