package com.example.nhom5.service;

import com.example.nhom5.domain.Stock;

public interface StockService {
    public Stock findStock(int productId,String sizeName,String colorName);
}
