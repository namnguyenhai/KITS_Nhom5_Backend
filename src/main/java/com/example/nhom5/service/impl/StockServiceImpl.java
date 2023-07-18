package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.model.StockDTO;
import com.example.nhom5.repository.StockRepository;
import com.example.nhom5.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;
    @Override
    public Stock addStock(Stock stock) {
        return stockRepository.saveAndFlush(stock);
    }

    @Override
    public List<StockDTO> getAllStocks() {
        return stockRepository.getAllStock();
    }

    @Override
    public List<Map<String, Object>> getStockByID(int id) {
        return stockRepository.findAllByStockId(id);
    }


}
