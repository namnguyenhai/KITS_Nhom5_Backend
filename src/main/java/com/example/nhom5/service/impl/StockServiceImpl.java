package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.model.CartItem;
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
    public Stock findStock(int productId,String sizeName,String colorName) {
        return stockRepository.findStock( productId,sizeName,colorName);
    }

    @Override
    public void updateStockQuantity(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            int productId = cartItem.getProductId();
            String sizeName = cartItem.getSizeName();
            String colorName = cartItem.getColorName();
            int quantityOrder = cartItem.getQuantity();

            Stock stock = stockRepository.findStock(productId, sizeName, colorName);
            if (stock != null) {
                int currentQuantity = stock.getQuantityStock();
                int updatedQuantity = currentQuantity - quantityOrder;

                // Cập nhật số lượng trong bảng Stock
                stock.setQuantityStock(updatedQuantity);
                stockRepository.save(stock);
            }
        }
    }
    }


