package com.example.nhom5.repository;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;
import com.example.nhom5.model.TestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

//    @Query(value = "select new  com.example.nhom5.model.ProductDTO(products.productId,products.productName,products.brand,products.description,cate.categoryName,img.urlImage,stock.color.colorName,stock.size.sizeName,stock.quantityStock,stock.priceStock) " +
//            "from Product products inner join Category cate " +
//            "on products.category.categoryName = cate.categoryName " +
//            "inner join ProductImage img " +
//            "on products.productId = img.product.productId " +
//            "inner join Stock stock " +
//            "on products.productId = stock.product.productId")
//    List<ProductDTO> getAllProduct();

//    @Query(value = "SELECT pro.product_id as productId,pro.product_name as productName,pro.brand as brand,pro.description as description,cate.category_name as categoryName,st.quantity_stock as quantityStock,st.price_stock as priceStock,GROUP_CONCAT(DISTINCT img.url_image) as urlImage,GROUP_CONCAT(DISTINCT st.color_id) as colorName,GROUP_CONCAT(DISTINCT st.size_id) as sizeName\n" +
//            "FROM product as pro \n" +
//            "INNER JOIN category as cate \n" +
//            "ON pro.categoryid = cate.category_name\n" +
//            "INNER JOIN product_image as img\n" +
//            "ON pro.product_id = img.product_id\n" +
//            "INNER JOIN stock st \n" +
//            "ON pro.product_id = st.product_id\n" +
//            "GROUP BY pro.product_id\n",nativeQuery = true)
    @Query(value = "SELECT pro.product_id as productId,pro.product_name as productName,pro.brand as brand,pro.description as description,cate.category_name as categoryName,st.quantity_stock as quantityStock,st.price_stock as priceStock,GROUP_CONCAT(DISTINCT img.url_image) as urlImage,GROUP_CONCAT(DISTINCT st.color_id) as colorName,GROUP_CONCAT(DISTINCT st.size_id) as sizeName\n" +
            "            FROM product as pro\n" +
            "            INNER JOIN category as cate\n" +
            "            ON pro.categoryid = cate.category_name\n" +
            "            INNER JOIN product_image as img\n" +
            "            ON pro.product_id = img.product_id\n" +
            "            INNER JOIN stock st\n" +
            "            ON pro.product_id = st.product_id\n" +
            "            GROUP BY pro.product_id,pro.product_name,pro.brand,pro.description,cate.category_name,st.quantity_stock,st.price_stock ",nativeQuery = true)
    List<Map<String,Object>> getAllProductAndStock();
//,pro.product_name,pro.brand,pro.description,cate.category_name,st.quantity_stock,st.price_stock
    @Query(value = "SELECT pro.product_id as productId, pro.product_name as productName, pro.brand as brand, cate.category_name\n" +
            "FROM product pro \n" +
            "INNER JOIN category cate \n" +
            "ON pro.categoryid = cate.category_name\n" +
            "GROUP BY pro.product_id,pro.product_name, pro.brand, cate.category_name",nativeQuery = true)
    List<Map<String,Object>> getAllProductsInfo();
    @Query(value = "SELECT pro.product_id as productId,pro.product_name as productName,pro.brand as brand,pro.description as description,cate.category_name as categoryName,st.quantity_stock as quantityStock,st.price_stock as priceStock,GROUP_CONCAT(DISTINCT img.url_image) as urlImage,GROUP_CONCAT(DISTINCT st.color_id) as colorName,GROUP_CONCAT(DISTINCT st.size_id) as sizeName\n" +
            "FROM product as pro \n" +
            "INNER JOIN category as cate ON pro.categoryid = cate.category_name\n" +
            "INNER JOIN product_image as img ON pro.product_id = img.product_id\n" +
            "INNER JOIN stock st ON pro.product_id = st.product_id\n" +
            "WHERE pro.product_name LIKE (:name)\n" +
            "GROUP BY pro.product_id,pro.product_name,pro.brand,pro.description,cate.category_name,st.quantity_stock,st.price_stock\n",nativeQuery = true)
    List<Map<String,Object>> findProductByName(@Param("name") String name);

    @Query(value = "SELECT DISTINCT pro.brand as brand\n" +
            "FROM product as pro",nativeQuery = true)

    List<Map<String,Object>> getProductBrand();

    @Query(value = "SELECT pro.product_id as productId,pro.product_name as productName,pro.brand as brand,pro.description as description,cate.category_name as categoryName,st.quantity_stock as quantityStock,st.price_stock as priceStock,GROUP_CONCAT(DISTINCT img.url_image) as urlImage,GROUP_CONCAT(DISTINCT st.color_id) as colorName,GROUP_CONCAT(DISTINCT st.size_id) as sizeName\n" +
            "FROM product as pro \n" +
            "INNER JOIN category as cate ON pro.categoryid = cate.category_name\n" +
            "INNER JOIN product_image as img ON pro.product_id = img.product_id\n" +
            "INNER JOIN stock st ON pro.product_id = st.product_id\n" +
            "WHERE pro.brand LIKE (:brandProduct) AND st.size_id LIKE (:sizeProduct) AND st.color_id LIKE (:colorProduct) AND (st.price_stock BETWEEN 0 AND (:maxPrice))\n" +
            "GROUP BY pro.product_id,pro.product_name,pro.brand,pro.description,cate.category_name,st.quantity_stock,st.price_stock\n",nativeQuery = true)
    List<Map<String,Object>> filterProduct(@Param("brandProduct") String brandProduct, @Param("sizeProduct") String sizeProduct, @Param("colorProduct") String colorProduct, @Param("maxPrice") double maxPrice );
    @Query(value = "SELECT pro.product_id,GROUP_CONCAT(product_image.url_image) as urlimg \n" +
            "FROM product as pro\n" +
            "INNER JOIN category ON pro.categoryid = category.category_name \n" +
            "INNER JOIN product_image ON product_image.product_id = pro.product_id " +
            "group by pro.product_id",nativeQuery = true)
    List<Map<String,Object>> getTest();
//    @Query(value = "SELECT new com.example.nhom5.model.ProductDTO(p.productId,p.productName,img.urlImage) from  Product p " +
//            "inner join ProductImage img on p.productId = img.product.productId")
//    List<ProductDTO> findProductInfoFromMulTable();
}
