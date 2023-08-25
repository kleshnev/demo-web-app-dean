package com.example.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtTokenInterceptor(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Extract the token from the request
        String authorizationHeader = request.getHeader("Authorization");
        String token;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // Validate the token
        if (!jwtTokenUtil.validateToken(token)) {
            // Token is not valid
            // Handle the case where the token is not valid
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // Token is valid, continue with the request
        System.out.println("TOKEN IS VALID!");
        return true;
    }
}
