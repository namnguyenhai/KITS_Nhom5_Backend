package com.example.nhom5.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockDTO {
    private int stockId;
    private int quantityStock;
    private double priceStock;
    private int productId;
    private String colorName;
    private String sizeName;


    public StockDTO(int stockId, int quantityStock, double priceStock, int productId, String colorName, String sizeName) {
        this.stockId = stockId;
        this.quantityStock = quantityStock;
        this.priceStock = priceStock;
        this.productId = productId;
        this.colorName = colorName;
        this.sizeName = sizeName;
    }
}
