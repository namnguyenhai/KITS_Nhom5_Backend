package com.example.nhom5.service;
import com.example.nhom5.domain.Ordered;
import com.example.nhom5.model.OrderedDto;

import java.util.List;
import java.util.Map;

public interface OrderedService {
    public List<OrderedDto> getAllOrdered();
    public Ordered save(Ordered order);

//    HaiNam code
    public List<Map<String,Object>> getStatisticOrderByMonth();
    public List<Map<String,Object>> getSumOrders();

    public List<Map<String,Object>> getOrdersWithUserName(String userId);
    public List<Map<String,Object>> getOrderDetailFromOrder(String orderIds);
}
