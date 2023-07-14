package com.example.nhom5.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductMultiTableDTO {
    private int productId;
    private String productName;
    private String urlImage;
//    private int quantityStock;
//    private double priceStock;

    public ProductMultiTableDTO(int productId, String productName, String urlImage) {
        this.productId = productId;
        this.productName = productName;
        this.urlImage = urlImage;
//        this.quantityStock = quantityStock;
//        this.priceStock = priceStock;
    }
}
