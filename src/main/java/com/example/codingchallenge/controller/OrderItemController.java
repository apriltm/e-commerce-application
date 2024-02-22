package com.example.codingchallenge.controller;

import com.example.codingchallenge.dto.OrderItemDto;
import com.example.codingchallenge.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order-items")
@AllArgsConstructor
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    @Operation(summary = "Get list of all order items")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        return ResponseEntity.ok().body(orderItemService.getAllOrderItems());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an order item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(orderItemService.getOrderItemById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new order item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        var createdOrderItem = orderItemService.createOrderItem(orderItemDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOrderItem.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdOrderItem);
    }

    @PutMapping
    @Operation(summary = "Update an existing order item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<OrderItemDto> updateOrderItem(@RequestBody OrderItemDto orderItemDto) {
        var updatedOrderItem = orderItemService.updateOrderItem(orderItemDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedOrderItem.getId())
                .toUri();

        return ResponseEntity.created(uri).body(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<String> deleteOrderItem(@PathVariable UUID id) {
        orderItemService.deleteOrderItem(id);

        return ResponseEntity.ok().body("Order item " + id + " successfully deleted");
    }

}