package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.model.StockDTO;
import com.example.nhom5.repository.StockRepository;
import com.example.nhom5.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<Map<String, Object>> getStockByProductID(int id) {
        return stockRepository.findAllByProductId(id);
    }

    @Override
    public String deleteStockByID(int id) {
        stockRepository.deleteById(id);
        return "Stock deleted";
    }

    @Override
    public List<Map<String, Object>> getStockByProductColorSize(int productId, String colorId, String sizeId) {
        return stockRepository.getStockByColorSizeProductId(productId,colorId,sizeId);
    }

    @Override
    public List<Map<String, Object>> getStockColorsSizesBrands() {
        return stockRepository.getStockColorSizeBrand();
    }

    @Override
    public boolean existStocks(int productId, String color, String size) {
        return stockRepository.existsByProductProductIdAndColorColorNameAndSizeSizeName(productId,color,size);
    }


}
