package com.example.nhom5.service;

import com.example.nhom5.domain.Product;

public interface ProductService {
    public Product addProduct(Product product);
    public Product findProductById(int productId);

}
