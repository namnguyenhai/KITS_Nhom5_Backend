package com.example.nhom5.service;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);


    public List<ProductDTO> getAllProducts();
}
