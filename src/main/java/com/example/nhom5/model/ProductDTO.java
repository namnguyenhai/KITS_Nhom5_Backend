package com.example.nhom5.model;

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
    private String categoryName;
    private String urlImage;
    private String colorName;
    private String sizeName;
    private int quantityStock;
    private double priceStock;

    public ProductDTO(int productId, String productName, String brand, String description, String categoryName, String urlImage, String colorName, String sizeName, int quantityStock, double priceStock) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.description = description;
        this.categoryName = categoryName;
        this.urlImage = urlImage;
        this.colorName = colorName;
        this.sizeName = sizeName;
        this.quantityStock = quantityStock;
        this.priceStock = priceStock;
    }

    public ProductDTO(int productId, String productName, String brand, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.categoryName = categoryName;
    }
}
