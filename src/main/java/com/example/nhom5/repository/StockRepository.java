package com.example.nhom5.repository;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.model.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
    @Query(value = "select new com.example.nhom5.model.StockDTO(stock.product.productId,products.productName,colors.colorName,sz.sizeName,stock.quantityStock,stock.priceStock) from Stock stock " +
            "inner join Product products " +
            "on stock.product.productId= products.productId " +
            "inner join Color colors " +
            "on stock.color.colorName = colors.colorName " +
            "inner join Size sz " +
            "on stock.size.sizeName = sz.sizeName")
    List<StockDTO> getAllStock();
}
