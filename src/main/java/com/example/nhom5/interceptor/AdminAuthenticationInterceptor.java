package com.example.nhom5.interceptor;
import com.example.nhom5.domain.User;
import com.example.nhom5.model.RegisterResponseDto;
import com.example.nhom5.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PRE HANDLER.........");
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Loại bỏ phần "Bearer " để lấy token thôi

            System.out.println("TOKEN: " + token);
            User user = userService.findByToken(token);
            System.out.println("USERRR: " + user);
            if (user != null && user.getRole().equals("Admin")) {
                return true;
            } else {
                response.setContentType("application/json");
                RegisterResponseDto res = new RegisterResponseDto("User not found", "",
                        "", "USER_NOT_FOUND", "", user != null ? user.getUserId() : 0);
                String json = new ObjectMapper().writeValueAsString(res);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.addHeader("Access-Control-Allow-Origin","*");
                response.addHeader("Access-Control-Allow-Methods","*");
                response.addHeader("Access-Control-Allow-Headers","authorization,content-type");
                response.getWriter().print(json);
                response.flushBuffer();
                return false;
            }
        } else {
            response.setContentType("application/json");
            RegisterResponseDto res = new RegisterResponseDto("Authorization token not found", "",
                    "", "AUTH_TOKEN_NOT_FOUND", "", 0);
            String json = new ObjectMapper().writeValueAsString(res);
            response.setStatus(HttpStatus.OK.value());
            response.addHeader("Access-Control-Allow-Origin","*");
            response.addHeader("Access-Control-Allow-Methods","*");
            response.addHeader("Access-Control-Allow-Headers","authorization,content-type");
            response.getWriter().print(json);
            response.flushBuffer();
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
    }
}
