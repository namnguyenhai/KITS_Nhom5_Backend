package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Category;
import com.example.nhom5.domain.Product;
import com.example.nhom5.repository.CategoryRepository;
import com.example.nhom5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.saveAndFlush(category);
    }
}
