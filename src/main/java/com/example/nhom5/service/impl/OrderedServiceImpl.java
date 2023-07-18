package com.example.nhom5.service.impl;

import com.example.nhom5.converter.OrderedConverter;
import com.example.nhom5.domain.Ordered;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.dto.OrderedDetailDto;
import com.example.nhom5.dto.OrderedDto;
import com.example.nhom5.repository.OrderedRepository;
import com.example.nhom5.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderedServiceImpl implements OrderedService {
    @Autowired
    private OrderedRepository orderedRepository;
    @Autowired
    private OrderedConverter orderedConverter;
    @Override
    public List<OrderedDto> getAllOrdered() {
        List<OrderedDto>res=new ArrayList<OrderedDto>();
        List<Ordered>entities=orderedRepository.findAll();
        for(Ordered order :entities){
            res.add(orderedConverter.toDto(order));
        }
        return res;
    }

    @Override
    public Ordered save(Ordered order) {
      return  orderedRepository.save(order);


    }
}
