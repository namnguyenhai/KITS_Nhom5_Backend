package com.example.nhom5.repository;

import com.example.nhom5.domain.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepository extends JpaRepository <Ordered,Integer> {
}
