package com.example.codingchallenge.repository;

import com.example.codingchallenge.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> {

}
