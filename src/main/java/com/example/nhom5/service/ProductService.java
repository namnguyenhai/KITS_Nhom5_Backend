package com.example.nhom5.service;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {
    public Product addProduct(Product product);
    public Product findProductById(int productId);


    //    public List<ProductDTO> getAllProducts();
    public List<Map<String,Object>> getAllProductsAndStocks();
    public  List<Map<String,Object>> getAllProducts();
    public String deleteProduct(int id);

    public List<Map<String,Object>> findProductByID(int productID);
    public List<Map<String,Object>> findProductByName(String name);
    public List<Map<String,Object>> getAllProductBrand();

    public List<Map<String,Object>> filterProductAttribute(String brand, String size, String color,Double minPrice ,Double maxPrice);
    public List<Map<String,Object>> getBestSellersProduct();
}