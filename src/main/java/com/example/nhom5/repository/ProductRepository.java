package com.example.nhom5.repository;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;
import com.example.nhom5.model.ProductMultiTableDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    @Query(value = "SELECT new com.example.nhom5.model.ProductDTO(p.productId,p.productName,p.brand,p.description) from Product p")
    List<ProductDTO> findAllProductTable();


//        public final static String  getAllProduct = "SELECT pro.product_id,pro.product_name, img.url_image, st.quantity_stock, st.price_stock\n" +
//            "FROM product as pro \n" +
//            "INNER JOIN product_image as img\n" +
//            "ON pro.product_id = img.product_id\n" +
//            "INNER JOIN stock as st\n" +
//            "ON pro.product_id = st.product_id ";
    @Query(value = "SELECT new com.example.nhom5.model.ProductMultiTableDTO(p.productId,p.productName,img.urlImage) from  Product p " +
            "inner join ProductImage img on p.productId = img.product.productId")
    List<ProductMultiTableDTO> findProductInfoFromMulTable();
}
