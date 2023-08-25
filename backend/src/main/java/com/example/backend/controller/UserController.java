package com.example.backend.controller;

import com.example.backend.DTO.AuthResponse;
import com.example.backend.DTO.UserRegistrationRequest;
import com.example.backend.entity.User;
import com.example.backend.exception.BadCredentialsException;
import com.example.backend.exception.UserAlreadyExistsException;
import com.example.backend.service.UserService;
import com.example.backend.utils.JwtTokenUtil;
import org.apache.http.auth.InvalidCredentialsException;
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
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) throws Exception {
        try {
            User newUser = userService.registerUser(request.getUsername(), request.getPassword());
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User with this username already exists.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("INTERNAL ERROR" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRegistrationRequest authRequest) {
        try {

            User user = userService.authenticateUser(authRequest.getUsername(), authRequest.getPassword());
            String token = jwtTokenUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (InvalidCredentialsException e) {
            System.out.println("invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        } catch (Exception e) {
            System.out.println("ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed.");
        }
    }
}
