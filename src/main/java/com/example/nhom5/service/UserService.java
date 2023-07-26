package com.example.nhom5.service;

import com.example.nhom5.domain.User;
import com.example.nhom5.model.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<UserDto>getAllUser();
    public User addUser(User user);
    public User updateUser(User user);
    public User findUserById(int userId);
    public void deleteUser(int userId);
    public User findByToken(String token);
    public int updateTokenById(String token, int id);
   public  User findByUsername(String username);
   public User findByEmail(String email);

//   HaiNam code
    public List<Map<String,Object>> getUserById(int id);

}
