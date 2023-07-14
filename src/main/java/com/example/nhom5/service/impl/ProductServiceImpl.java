package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;
import com.example.nhom5.model.ProductMultiTableDTO;
import com.example.nhom5.repository.ProductRepository;
import com.example.nhom5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        return productRepository.findAllProductTable();
    }

    @Override
    public List<ProductMultiTableDTO> getMultipleTableProduct() {
        return productRepository.findProductInfoFromMulTable();
    }


}
