package com.example.backend.service;

import com.example.backend.entity.User;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final Firestore firestore; // You need to inject Firestore here

    @Autowired
    public UserService(FirestoreService firestoreService) throws IOException {
        this.firestore = firestoreService.initializeFirestore();
    }

    public User registerUser(String username, String password) throws Exception {
        // Check if the user with the given username already exists in Firestore (you need to implement this)
        if (isUserExistsInFirestore(username)) {
            throw new Exception("User with this username already exists.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Store the password as is (no encryption for simplicity)

        // Save the user to Firestore (you need to implement this)
        saveUserToFirestore(user);
        System.out.println("User "+user.getUsername() + " created and saved");
        return user;
    }

    // Implement these methods to interact with Firestore for user registration
    private boolean isUserExistsInFirestore(String username) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("users");
        DocumentReference userDocRef = usersCollection.document(username);
        DocumentSnapshot snapshot = userDocRef.get().get();
        return snapshot.exists();
    }

    private void saveUserToFirestore(User user) {
        CollectionReference usersCollection = firestore.collection("users");
        DocumentReference userDocRef = usersCollection.document(user.getUsername());

        // Convert the User object to a Map to store in Firestore
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", user.getUsername());
        userData.put("password", user.getPassword());

        // Set the data in the Firestore document
        userDocRef.set(userData);
    }
}
