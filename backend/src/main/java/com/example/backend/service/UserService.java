package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.exception.UserAlreadyExistsException;
import org.apache.http.auth.InvalidCredentialsException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private final FirestoreService firestoreService;

    @Autowired
    public UserService(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    public User registerUser(String username, String password) throws Exception {
        // Check if the user with the given username already exists in Firestore
        if (firestoreService.isUserExists(username)) {
            throw new UserAlreadyExistsException("User with this username already exists.");
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);

        // Save the user to Firestore
        firestoreService.addUser(user);

        return user;
    }

    public User authenticateUser(String username, String password) throws InvalidCredentialsException {
        User user = firestoreService.getUserByUsername(username);
        System.out.println("TEST! " + BCrypt.checkpw(password, user.getPassword()));
        System.out.println(user.getUsername() + user.getPassword());
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password.");
        }
        return user;
    }
}


