package com.example.nhom5.repository;

import com.example.nhom5.domain.Color;
import com.example.nhom5.model.ColorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color,Integer> {
    @Query(value = "select new com.example.nhom5.model.ColorDTO(color.colorName) from Color color")
    List<ColorDTO> getAllColor();
}
