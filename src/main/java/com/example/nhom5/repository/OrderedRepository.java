package com.example.nhom5.repository;

import com.example.nhom5.domain.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
            "ON ordereds.user_id = users.user_id\n" +
            "WHERE CAST(users.user_id as CHAR) LIKE (:userId)",nativeQuery = true)
    List<Map<String,Object>> getOrderWithUserName(@Param("userId") String userId);

    @Query(value = "SELECT ord.order_id as orderId,ord.ordered_detail_id as orderDetailId,product.product_name as productName, ord.color_name as colorName, ord.quantity_order as quantityOrder, ord.unit_price as unitPrice,ord.size_name as sizeName\n" +
            "FROM product INNER JOIN (SELECT ordereds.order_id,ordered_details.product_id, ordered_details.color_name , ordered_details.quantity_order, ordered_details.unit_price, ordered_details.size_name,ordered_details.ordered_detail_id FROM ordereds INNER JOIN ordered_details ON ordereds.order_id = ordered_details.order_id) as ord \n" +
            "ON product.product_id = ord.product_id\n" +
            "WHERE CAST(ord.order_id as CHAR) LIKE (:orderIds)",nativeQuery = true)
    List<Map<String,Object>> getOrderDetailFromOrders(@Param("orderIds") String orderIds);
}
