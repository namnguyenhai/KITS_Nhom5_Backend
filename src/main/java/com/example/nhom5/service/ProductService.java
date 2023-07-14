package com.example.nhom5.service;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;
import com.example.nhom5.model.ProductMultiTableDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ProductService {
    public Product addProduct(Product product);
    public List<ProductDTO> getAllProduct();
    public List<ProductMultiTableDTO> getMultipleTableProduct();
}
