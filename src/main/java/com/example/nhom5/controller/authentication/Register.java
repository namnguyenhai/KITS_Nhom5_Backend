package com.example.nhom5.controller.authentication;

import com.example.nhom5.domain.User;
import com.example.nhom5.dto.RegisterRequestDto;
import com.example.nhom5.dto.RegisterResponseDto;
import com.example.nhom5.service.UserService;
import com.example.nhom5.utils.UtilsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/register")
public class Register {

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequest, HttpServletResponse response) {
        // user entity
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setAddress(registerRequest.getAddress());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRole(registerRequest.getRole());
        user.setImage(registerRequest.getImage());
        user.setUserName(registerRequest.getUserName());

        User foundUser = userService.findByUsername(registerRequest.getUserName());
        System.out.println("foundUser: " + foundUser);
        // Generate password
        if(foundUser == null) {
            try {
                user.setPassWorld(bCryptPasswordEncoder.encode(registerRequest.getPassWorld()));
                user.setToken(UtilsService.getRandomHexString(150));
                User result = userService.addUser(user);
                // Set cookie
                Cookie cookie = new Cookie("token", user.getToken());
                cookie.setMaxAge(3600);
                //add cookie to response
                response.addCookie(cookie);

                return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponseDto("Register Successfully", result, "",
                        ""));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponseDto("", "", e.getMessage(),
                        "UNKNOWN_ERROR"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponseDto("User already exists", "",
                    "", "USER_EXIST"));
        }
    }
}