package com.example.codingchallenge.dto;

import com.example.codingchallenge.config.UUIDAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private UUID id;
    private double price;
    @XmlJavaTypeAdapter(UUIDAdapter.class)
    private ProductDto productDto;
    private Integer quantity;
    private double subTotal;

}
