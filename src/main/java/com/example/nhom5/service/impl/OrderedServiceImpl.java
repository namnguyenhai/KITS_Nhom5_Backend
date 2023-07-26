package com.example.nhom5.service.impl;

import com.example.nhom5.converter.OrderedConverter;
import com.example.nhom5.domain.Ordered;
import com.example.nhom5.model.OrderedDto;
import com.example.nhom5.repository.OrderedRepository;
import com.example.nhom5.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

//    HaiNam code
    @Override
    public List<Map<String, Object>> getStatisticOrderByMonth() {
        return orderedRepository.statisticsOrderMonth();
    }

    @Override
    public List<Map<String, Object>> getSumOrders() {
        return orderedRepository.getSumOrder();
    }

    @Override
    public List<Map<String, Object>> getOrdersWithUserName(String userId) {
        return orderedRepository.getOrderWithUserName(userId);
    }

    @Override
    public List<Map<String, Object>> getOrderDetailFromOrder(String orderIds) {
        return orderedRepository.getOrderDetailFromOrders(orderIds);
    }
}
