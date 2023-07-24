package com.example.backend.controller;

import com.example.backend.DTO.AuthResponse;
import com.example.backend.DTO.UserRegistrationRequest;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) throws Exception {
        User newUser = userService.registerUser(request.getUsername(), request.getPassword());
        System.out.println("TRYING TO REG USER");
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/custom-login")
    public ResponseEntity<?> customLogin(@RequestBody UserRegistrationRequest authRequest) throws Exception {
        // For this example, let's assume login is successful if the username is "test" and password is "password"
        if ("test".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
            return ResponseEntity.ok(new AuthResponse("login_successful")); // Return a success response
        } else {
            throw new Exception("Invalid username or password"); // Return an error response if login fails
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRegistrationRequest authRequest){
        // You no longer need the authenticationManager and try-catch block

        final String token = jwtTokenUtil.generateToken(authRequest.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
