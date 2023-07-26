package com.example.nhom5.repository;

import com.example.nhom5.domain.Size;
import com.example.nhom5.model.SizeDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size,Integer> {
    @Query(value = "select new com.example.nhom5.model.SizeDTO(sz.sizeName) from Size sz")
    List<SizeDTO> findALl();
}