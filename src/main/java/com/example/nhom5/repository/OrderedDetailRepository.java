package com.example.nhom5.repository;
import com.example.nhom5.domain.OrderedDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedDetailRepository extends JpaRepository<OrderedDetail,Integer> {
    public OrderedDetail findById(int  OrderedDetailId );

}
