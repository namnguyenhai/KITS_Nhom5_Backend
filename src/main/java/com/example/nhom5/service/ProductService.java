package com.example.nhom5.service;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;
import com.example.nhom5.model.TestDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {
    public Product addProduct(Product product);


//    public List<ProductDTO> getAllProducts();
    public List<Map<String,Object>> getAllProducts();
    public List<Map<String,Object>> getTests();
    public String deleteProduct(int id);

    public List<Map<String,Object>> findProductByName(String name);
    public List<Map<String,Object>> getAllProductBrand();

    public List<Map<String,Object>> filterProductAttribute(String brand, String size, String color, Double maxPrice);
}
