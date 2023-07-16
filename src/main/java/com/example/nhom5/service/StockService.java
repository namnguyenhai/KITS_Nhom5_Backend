package com.example.nhom5.service;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.model.StockDTO;

import java.util.List;

public interface StockService {
    public Stock addStock(Stock stock);

    public List<StockDTO>  getAllStocks();
}
