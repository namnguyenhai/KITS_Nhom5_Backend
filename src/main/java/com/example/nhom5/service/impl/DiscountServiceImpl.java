package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Discount;
import com.example.nhom5.repository.DiscountRepository;
import com.example.nhom5.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public Discount addDiscount(Discount discount) {
        return discountRepository.saveAndFlush(discount);
    }
}