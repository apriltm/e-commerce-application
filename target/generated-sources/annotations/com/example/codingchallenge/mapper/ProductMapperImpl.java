package com.example.codingchallenge.mapper;

import com.example.codingchallenge.dto.ProductDto;
import com.example.codingchallenge.entity.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-22T02:41:19-0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto mapToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );
        productDto.setImageUrl( product.getImageUrl() );
        productDto.setQuantity( product.getQuantity() );

        return productDto;
    }

    @Override
    public Product mapToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setPrice( productDto.getPrice() );
        product.setImageUrl( productDto.getImageUrl() );
        product.setQuantity( productDto.getQuantity() );

        return product;
    }
}
