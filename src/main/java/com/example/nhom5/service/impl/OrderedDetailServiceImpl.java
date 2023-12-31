package com.example.nhom5.service.impl;

import com.example.nhom5.converter.OrderedDetailConverter;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.model.OrderedDetailDto;
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
    public void addOrderedDetails(List<OrderedDetail> orderedDetails) {
        orderedDetailRepository.saveAll(orderedDetails);
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

    @Override
    public OrderedDetail findOrderedDetaiById(int orderedDetailId) {
        return orderedDetailRepository.findById(orderedDetailId);
    }
}

