package com.example.nhom5.repository;

import com.example.nhom5.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
    @Query("SELECT s FROM Stock s WHERE s.product.productId = :productId")
    public  Stock findStockByProductId(int productId);
}
