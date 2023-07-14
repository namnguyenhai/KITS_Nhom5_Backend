package com.example.nhom5.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private int productId;

    private String productName;
    private String brand;
    private String description;

    public ProductDTO(int productId, String productName, String brand, String description) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.description = description;
    }
}
