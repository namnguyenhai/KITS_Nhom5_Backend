package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.ProductImage;
import com.example.nhom5.repository.ProductImageRepository;
import com.example.nhom5.service.ProductImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public ProductImage addProductImage(ProductImage productImage) {
        return productImageRepository.saveAndFlush(productImage);
    }

    @Override
    public List<ProductImage> addListProductImages(List<ProductImage> productImages) {
        return productImageRepository.saveAllAndFlush(productImages);
    }
}
