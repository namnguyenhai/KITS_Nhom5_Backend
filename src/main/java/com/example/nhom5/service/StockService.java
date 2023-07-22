package com.example.nhom5.service;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.model.CartItem;
import com.example.nhom5.model.StockDTO;

import java.util.List;
import java.util.Map;

public interface StockService {
    public Stock addStock(Stock stock);

    public List<StockDTO>  getAllStocks();

    public List<Map<String,Object>> getStockByProductID(int id);
    public String deleteStockByID(int id);
    public List<Map<String,Object>> getStockByProductColorSize(int productId,String colorId, String sizeId);

    public List<Map<String,Object>> getStockColorsSizesBrands();
    public boolean existsStock(int productId, String color, String size);
    public Stock findStock(int productId,String sizeName,String colorName);
    public void updateStockQuantity(List<CartItem>cartItems);

}