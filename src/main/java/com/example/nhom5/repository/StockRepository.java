package com.example.nhom5.repository;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.model.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Query(value = "select new com.example.nhom5.model.StockDTO(stock.stockId,stock.quantityStock,stock.priceStock,stock.product.productId,stock.color.colorName,stock.size.sizeName) from Stock stock " +
            "inner join Product products " +
            "on stock.product.productId= products.productId " +
            "inner join Color colors " +
            "on stock.color.colorName = colors.colorName " +
            "inner join Size sz " +
            "on stock.size.sizeName = sz.sizeName")
    List<StockDTO> getAllStock();


    @Query(value = "SELECT GROUP_CONCAT(DISTINCT stock.color_id) as colorId, GROUP_CONCAT(DISTINCT product.brand) as brand,GROUP_CONCAT(DISTINCT stock.size_id) as sizeId\n" +
            "FROM stock INNER JOIN product \n" +
            "ON stock.product_id = product.product_id",nativeQuery = true)
    List<Map<String,Object>> getStockColorSizeBrand();

    @Query(value = "SELECT st.stock_id as stockId, st.color_id as colorId, st.size_id as sizeId, st.price_stock as priceStock, st.quantity_stock as quantityStock\n" +
            "FROM stock as st\n" +
            "WHERE st.product_id = (:productId) AND st.color_id LIKE (:colorId) AND st.size_id LIKE (:sizeIdStock)", nativeQuery = true)
    List<Map<String,Object>> getStockByColorSizeProductId(@Param("productId") int productId, @Param("colorId") String colorId, @Param("sizeIdStock") String sizeIdStock);
    @Query(value = "SELECT stock.stock_id as stockId,product.product_id as productId,product.product_name as productName,stock.quantity_stock as quantityStock,stock.price_stock as priceStock,stock.color_id as colorId,stock.size_id as sizeId FROM stock \n" +
            "INNER JOIN product ON product.product_id = stock.product_id\n" +
            "WHERE product.product_id = (:id)", nativeQuery = true)
    List<Map<String, Object>> findAllByProductId(@Param("id") int id);


//    @Query(value = "select COUNT(stock.stock_id)  AS stockId FROM stock WHERE stock.product_id = (:productId) AND stock.color_id LIKE (:colorId) AND stock.size_id LIKE (:sizeId)",nativeQuery = true)
//    List<Map<String,Object>> existStock(@Param("productId") int productId, @Param("colorId") String colorId, @Param("sizeId") String sizeId);
    Boolean existsByProductProductIdAndColorColorNameAndSizeSizeName(int productId,String color,String size);
}
