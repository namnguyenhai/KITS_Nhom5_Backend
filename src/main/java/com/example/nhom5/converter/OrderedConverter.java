package com.example.nhom5.converter;

import com.example.nhom5.domain.Ordered;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.dto.OrderedDetailDto;
import com.example.nhom5.dto.OrderedDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderedConverter {
    @Autowired
    ModelMapper modelMapper;
    public Ordered toEntity(OrderedDto dto){
        Ordered entity=modelMapper.map(dto,Ordered.class);
        return  entity;
    }
    public OrderedDto toDto(Ordered entity){
        OrderedDto dto=modelMapper.map(entity,OrderedDto.class);
        return  dto;

    }
}
