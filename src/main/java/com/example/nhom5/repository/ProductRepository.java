package com.example.nhom5.repository;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Product findById(int productId);
    @Query(value = "SELECT stock.product_id as productId,two.product_name as productName, two.brand as brand, two.description as description, two.categoryid as categoryName,GROUP_CONCAT(stock.price_stock) as priceStock,GROUP_CONCAT(stock.quantity_stock) as quantityStock,GROUP_CONCAT(stock.size_id) as sizeName,GROUP_CONCAT(stock.color_id) as colorName,two.ims as urlImage\n" +
            "FROM stock\n" +
            "INNER JOIN ((SELECT product.product_id,product.product_name,product.brand,product.description,product.categoryid,GROUP_CONCAT(product_image.url_image) as ims FROM product INNER JOIN product_image ON product.product_id = product_image.product_id GROUP BY product.product_id)) as two\n" +
            "ON stock.product_id = two.product_id\n" +
            "GROUP BY stock.product_id",nativeQuery = true)
    List<Map<String,Object>> getAllProductAndStock();
    //,pro.product_name,pro.brand,pro.description,cate.category_name,st.quantity_stock,st.price_stock
    @Query(value = "SELECT product.product_id as productId, product.product_name as productName, product.brand as brand, product.categoryid as category, Max(product_image.url_image) as image\n" +
            "FROM product\n" +
            "INNER JOIN product_image\n" +
            "ON product.product_id = product_image.product_id\n" +
            "GROUP BY product.product_id,product.product_name,product.brand, product.categoryid",nativeQuery = true)
    List<Map<String,Object>> getAllProductsInfo();
    @Query(value = "SELECT stock.product_id as productId,two.product_name as productName, two.brand as brand, two.description as description, two.categoryid as categoryName,GROUP_CONCAT(stock.price_stock) as priceStock,GROUP_CONCAT(stock.quantity_stock) as quantityStock,GROUP_CONCAT(stock.size_id) as sizeName,GROUP_CONCAT(stock.color_id) as colorName,two.ims as urlImage\n" +
            "FROM stock\n" +
            "INNER JOIN ((SELECT product.product_id,product.product_name,product.brand,product.description,product.categoryid,GROUP_CONCAT(product_image.url_image) as ims  FROM product INNER JOIN product_image ON product.product_id = product_image.product_id GROUP BY product.product_id)) as two\n" +
            "ON stock.product_id = two.product_id\n" +
            "WHERE two.product_name LIKE (:name)\n" +
            "GROUP BY stock.product_id",nativeQuery = true)
    List<Map<String,Object>> findProductByName(@Param("name") String name);


    @Query(value ="SELECT stock.product_id as productId,two.product_name as productName, two.brand as brand, two.description as description, two.categoryid as categoryName,GROUP_CONCAT(stock.price_stock) as priceStock,GROUP_CONCAT(stock.quantity_stock) as quantityStock,GROUP_CONCAT(stock.size_id) as sizeName,GROUP_CONCAT(stock.color_id) as colorName,two.ims as urlImage\n" +
            "FROM stock\n" +
            "INNER JOIN ((SELECT product.product_id,product.product_name,product.brand,product.description,product.categoryid,GROUP_CONCAT(product_image.url_image) as ims FROM product INNER JOIN product_image ON product.product_id = product_image.product_id GROUP BY product.product_id)) as two\n" +
            "ON stock.product_id = two.product_id\n" +
            "WHERE stock.product_id = (:productID)\n" +
            "GROUP BY stock.product_id" ,nativeQuery = true)
//,pro.product_name,pro.brand,pro.description,cate.category_name
    List<Map<String,Object>> findProductById(@Param("productID") int productID);


    @Query(value = "SELECT DISTINCT pro.brand as brand\n" +
            "FROM product as pro",nativeQuery = true)

    List<Map<String,Object>> getProductBrand();

    @Query(value = "SELECT stock.product_id as productId,two.product_name as productName, two.brand as brand, two.description as description, two.categoryid as categoryName,GROUP_CONCAT(stock.price_stock) as priceStock,GROUP_CONCAT(stock.quantity_stock) as quantityStock,GROUP_CONCAT(stock.size_id) as sizeName,GROUP_CONCAT(stock.color_id) as colorName,two.ims as urlImage\n" +
            "FROM stock\n" +
            "INNER JOIN ((SELECT product.product_id,product.product_name,product.brand,product.description,product.categoryid,GROUP_CONCAT(product_image.url_image) as ims  FROM product INNER JOIN product_image ON product.product_id = product_image.product_id GROUP BY product.product_id)) as two\n" +
            "ON stock.product_id = two.product_id\n" +
            "WHERE two.brand LIKE (:brandProduct) AND stock.size_id LIKE (:sizeProduct) AND stock.color_id LIKE (:colorProduct) AND (stock.price_stock BETWEEN (:minPrice) AND (:maxPrice))\n" +
            "GROUP BY stock.product_id",nativeQuery = true)
    List<Map<String,Object>> filterProduct(@Param("brandProduct") String brandProduct, @Param("sizeProduct") String sizeProduct, @Param("colorProduct") String colorProduct, @Param("minPrice") double minPrice,@Param("maxPrice") double maxPrice );

    @Query(value = "SELECT ordered_details.product_id as productId, imgs.product_name as productName,imgs.brand as brands, imgs.categoryid as category, imgs.ccimg as image,SUM(ordered_details.quantity_order) as numberOrder\n" +
            "FROM ordered_details INNER JOIN (SELECT product.product_id, product.product_name,product.brand,product.categoryid, GROUP_CONCAT(product_image.url_image) as ccimg FROM product INNER JOIN product_image ON product.product_id = product_image.product_id GROUP BY product.product_id) as imgs\n" +
            "ON ordered_details.product_id = imgs.product_id\n" +
            "GROUP BY imgs.product_id\n" +
            "ORDER BY SUM(ordered_details.quantity_order) DESC\n" +
            "LIMIT 5",nativeQuery = true )
    List<Map<String,Object>> getBestSeller();




}