package com.example.nhom5.service.impl;

import com.example.nhom5.converter.OrderedDetailConverter;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.domain.User;
import com.example.nhom5.dto.OrderedDetailDto;
import com.example.nhom5.dto.UserDto;
import com.example.nhom5.repository.OrderedDetailRepository;
import com.example.nhom5.service.OrderedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class OrderedDetailServiceImpl implements OrderedDetailService {
    @Autowired
    private OrderedDetailRepository orderedDetailRepository;
    @Autowired
    private OrderedDetailConverter orderedDetailConverter;
    @Override
    public OrderedDetail addOrderedDetaiḷ(OrderedDetail orderedDetail) {
        return orderedDetailRepository.save(orderedDetail) ;
    }

    @Override
    public List<OrderedDetailDto> getAllOrderedDetail() {

        List<OrderedDetailDto>res=new ArrayList<OrderedDetailDto>();
        List<OrderedDetail>entities=orderedDetailRepository.findAll();
        for(OrderedDetail order :entities){
            res.add(orderedDetailConverter.toDto(order));
        }
        return res;
     }
    }

