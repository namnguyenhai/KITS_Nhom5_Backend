package com.example.nhom5.controller;

import com.example.nhom5.converter.UserConverter;
import com.example.nhom5.domain.User;
import com.example.nhom5.model.RegisterResponseDto;
import com.example.nhom5.model.UserDto;
import com.example.nhom5.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserConverter userConverter;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/list")
    @ResponseBody
    public List<UserDto> getListuser() {

        return userService.getAllUser();
    }

    @RequestMapping("add")
    public RegisterResponseDto add() {
        return new RegisterResponseDto();
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<User> adduser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<UserDto> getUserByToken(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }

        String jwtToken = token.substring(7); // Lấy phần token sau "Bearer "
        User user = userService.findByToken(jwtToken);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        int userId = user.getUserId();
        User userupdate = userService.findUserById(userId);
        UserDto userDto = userConverter.toDTo(userupdate);
        return ResponseEntity.ok(userDto);
    }


    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody User userDetails, @RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7); // Lấy phần token sau "Bearer "
            User user = userService.findByToken(jwtToken);
            if (user == null) {
                // Return bad request if the user is not found with the given token
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
            }
            int userId = user.getUserId();

            // Kiểm tra xem người dùng có tồn tại trong hệ thống hay không
            User existingUser = userService.findUserById(userId);
            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            // Kiểm tra nếu client cung cấp mật khẩu mới thì mới cập nhật
            if (userDetails.getPassword() != null) {
                existingUser.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
            }

            // Cập nhật thông tin người dùng
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setLastName(userDetails.getLastName());
            if (userDetails.getAddress() != null) {
                existingUser.setAddress(userDetails.getAddress());
            }
            if (userDetails.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(userDetails.getPhoneNumber());
            }
            User foundEmail = userService.findByEmail(userDetails.getEmail());
            if (foundEmail != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponseDto("Email already exists", "",
                        "", "EMAIL_EXIST", "", user.getUserId(), user.getUsername()));
            } else {
                if (userDetails.getEmail() != null) {
                    existingUser.setEmail(userDetails.getEmail());
                }
            }

            User userUpdate = userService.updateUser(existingUser);
            UserDto userDto = userConverter.toDTo(userUpdate);
            return ResponseEntity.ok(userDto);
        } else {
            // Return bad request if the token is not provided or in an invalid format
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
        }
    }


    //    HaiNam code
    @GetMapping("/getuserbyid")
    public ResponseEntity<?> find_Product_By_Id(@RequestParam int userId) {
        Map<String, Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());
        output.put("product", userService.getUserById(userId));
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
