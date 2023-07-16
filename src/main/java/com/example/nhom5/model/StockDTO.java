package com.example.nhom5.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockDTO {
    private int stockId;
    private String productName;
    private String colorName;
    private String sizeName;
    private int quantityStock;
    private double priceStock;

    public StockDTO(int stockId, String productName, String colorName, String sizeName, int quantityStock, double priceStock) {
        this.stockId = stockId;
        this.productName = productName;
        this.colorName = colorName;
        this.sizeName = sizeName;
        this.quantityStock = quantityStock;
        this.priceStock = priceStock;
    }
}
