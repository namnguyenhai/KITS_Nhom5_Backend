package com.example.nhom5.service;

import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.dto.OrderedDetailDto;

import java.util.List;

public interface OrderedDetailService {
    public OrderedDetail addOrderedDetaiḷ(OrderedDetail orderedDetail);
    public List<OrderedDetailDto>getAllOrderedDetail();
}
