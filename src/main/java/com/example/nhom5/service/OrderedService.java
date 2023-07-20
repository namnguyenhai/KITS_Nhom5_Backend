package com.example.nhom5.service;
import com.example.nhom5.domain.Ordered;
import com.example.nhom5.model.OrderedDto;

import java.util.List;

public interface OrderedService {
    public List<OrderedDto> getAllOrdered();
    public Ordered save(Ordered order);


}
