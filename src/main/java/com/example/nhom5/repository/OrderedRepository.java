package com.example.nhom5.repository;

import com.example.nhom5.domain.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderedRepository extends JpaRepository <Ordered,Integer> {
//    HaiNam code
    @Query(value = "SELECT COUNT(order_id) as numberorder, SUM(total_price) as totalpricemonth, MONTH(order_date) as month FROM ordereds\n" +
            "GROUP BY MONTH(order_date)",nativeQuery = true)
    List<Map<String,Object>> statisticsOrderMonth();

    @Query(value = "SELECT  SUM(total_price) as total_price_order FROM ordereds\n", nativeQuery = true)
    List<Map<String,Object>> getSumOrder();

    @Query(value = "SELECT ordereds.order_id as orderid,users.first_name as username, ordereds.status as status,ordereds.total_price as totalprice, DATE(ordereds.order_date) as DateOrder\n" +
            "FROM ordereds INNER JOIN users\n" +
            "ON ordereds.user_id = users.user_id",nativeQuery = true)
    List<Map<String,Object>> getOrderWithUserName();
}
