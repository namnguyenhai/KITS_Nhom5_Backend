package com.example.nhom5.repository;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.model.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
    @Query("SELECT s FROM Stock s WHERE s.product.productId = ?1 AND s.size.sizeName = ?2 AND s.color.colorName = ?3")
    public  Stock findStock(int productId,String sizeName,String colorName);

        @Query(value = "select new com.example.nhom5.model.StockDTO(stock.stockId,stock.quantityStock,stock.priceStock,stock.product.productId,stock.color.colorName,stock.size.sizeName) from Stock stock " +
                "inner join Product products " +
                "on stock.product.productId= products.productId " +
                "inner join Color colors " +
                "on stock.color.colorName = colors.colorName " +
                "inner join Size sz " +
                "on stock.size.sizeName = sz.sizeName")
        List<StockDTO> getAllStock();

        @Query(value = "SELECT st.stock_id as stockId, st.color_id as colorId, st.size_id as sizeId, st.price_stock as priceStock, st.quantity_stock as quantityStock\n" +
                "FROM stock as st\n" +
                "WHERE st.product_id = (:productId) AND st.color_id LIKE (:colorId) AND st.size_id LIKE (:sizeIdStock)", nativeQuery = true)
        List<Map<String,Object>> getStockByColorSizeProductId(@Param("productId") int productId, @Param("colorId") String colorId, @Param("sizeIdStock") String sizeIdStock);
        @Query(value = "SELECT stock.stock_id as stockId,product.product_id as productId,product.product_name as productName,stock.quantity_stock as quantityStock,stock.price_stock as priceStock,stock.color_id as colorId,stock.size_id as sizeId FROM stock \n" +
                "INNER JOIN product ON product.product_id = stock.product_id\n" +
                "WHERE product.product_id = (:id)", nativeQuery = true)
        List<Map<String, Object>> findAllByProductId(@Param("id") int id);
}
