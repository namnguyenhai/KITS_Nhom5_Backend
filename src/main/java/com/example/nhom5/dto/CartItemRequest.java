package com.example.nhom5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemRequest {
    private String productName;
    private int quantity;
    private double price;
    private String colorName;
    private String sizeName;
}
