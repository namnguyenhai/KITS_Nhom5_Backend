package com.example.nhom5.converter;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.dto.OrderedDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderedDetailConverter {
    @Autowired
    ModelMapper modelMapper;

    public OrderedDetail toEntity(OrderedDetailDto dto){
        OrderedDetail entity=modelMapper.map(dto,OrderedDetail.class);
        return  entity;
    }
    public OrderedDetailDto toDto(OrderedDetail entity){
        OrderedDetailDto dto=modelMapper.map(entity,OrderedDetailDto.class);
        return  dto;

    }

    }


