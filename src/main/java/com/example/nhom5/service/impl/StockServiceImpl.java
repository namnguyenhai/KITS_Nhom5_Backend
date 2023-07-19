package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.repository.StockRepository;
import com.example.nhom5.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;
    @Override
    public Stock findStock(int productId,String sizeName,String colorName) {
        return stockRepository.findStock( productId,sizeName,colorName);
    }
}
