package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Category;
import com.example.nhom5.domain.Product;
import com.example.nhom5.model.CategoryDTO;
import com.example.nhom5.repository.CategoryRepository;
import com.example.nhom5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<CategoryDTO> getAllCategorys() {
        return categoryRepository.getAllCategory();
    }
}
