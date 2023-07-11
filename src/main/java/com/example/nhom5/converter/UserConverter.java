package com.example.nhom5.converter;

import com.example.nhom5.domain.User;
import com.example.nhom5.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
   @Autowired
    ModelMapper modelMapper;
    public User toEntity(UserDto dto) {
        User entity = modelMapper.map(dto,User.class);
        return entity;

    }
    public UserDto toDTo(User entity) {
        UserDto dto = modelMapper.map(entity,UserDto.class);
        return dto;

    }

}
