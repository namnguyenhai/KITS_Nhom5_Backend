package com.example.nhom5.repository;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select new com.example.nhom5.model.ProductDTO(products.productId,products.productName,products.brand,products.description,cate.categoryName,img.urlImage,stock.color.colorName,stock.size.sizeName,stock.quantityStock,stock.priceStock) " +
            "from Product products inner join Category cate " +
            "on products.category.categoryName = cate.categoryName " +
            "inner join ProductImage img " +
            "on products.productId = img.product.productId " +
            "inner join Stock stock " +
            "on products.productId = stock.product.productId ")
    List<ProductDTO> getAllProduct();
//    @Query(value = "SELECT new com.example.nhom5.model.ProductDTO(p.productId,p.productName,img.urlImage) from  Product p " +
//            "inner join ProductImage img on p.productId = img.product.productId")
//    List<ProductDTO> findProductInfoFromMulTable();
}
