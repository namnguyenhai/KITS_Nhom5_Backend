package com.example.nhom5.service.impl;

import com.example.nhom5.converter.UserConverter;
import com.example.nhom5.domain.User;
import com.example.nhom5.dto.UserDto;
import com.example.nhom5.repository.UserRepository;
import com.example.nhom5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    // BCryptPasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;

        // this.passwordEncoder=new BCryptPasswordEncoder();
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserDto>res=new ArrayList<UserDto>();
        List<User>entities=userRepository.findAll();
        for(User u:entities){
            res.add(userConverter.toDTo(u));
        }
        return res;
    }

    @Override
    public User addUser(User user) {
        user.setRole("user");
        // String encodedPassword=passwordEncoder.encode(user.getPassWorld());
        // user.setPassWorld(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(int userId) {
        User user=userRepository.findById(userId);
        userRepository.delete(user);

    }
}
