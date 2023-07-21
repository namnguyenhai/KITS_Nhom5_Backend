package com.example.nhom5.service;

import com.example.nhom5.domain.Stock;
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
}
