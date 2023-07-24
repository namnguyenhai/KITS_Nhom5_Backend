package com.example.nhom5.controller.authentication;

import com.example.nhom5.domain.User;
import com.example.nhom5.model.RegisterResponseDto;
import com.example.nhom5.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/logout")
public class Logout {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Logout() {
    }

    @PostMapping
    public ResponseEntity<RegisterResponseDto> logout(@RequestHeader("Authorization") String token
                                                   ) {
        // user entity
        User user = userService.findByToken(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new RegisterResponseDto("User logged out", "", "", "USER_LOGGED_OUT", "", user.getUserId())
            );
        } else {
            // if user exists
            // 1. update token
            userService.updateTokenById(null, user.getUserId());
            // 2. Set cookie
            Cookie cookie = new Cookie("token", "");
            cookie.setMaxAge(0);
           // response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RegisterResponseDto("Logout Successfully", "", "", "", "", user.getUserId())
            );
        }
    }
}
