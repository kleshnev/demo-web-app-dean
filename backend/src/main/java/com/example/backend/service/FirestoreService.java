package com.example.backend.service;

import com.example.backend.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    private final Firestore firestore;
    private final CollectionReference usersCollection;

    public FirestoreService(Firestore firestore) {
        this.firestore = firestore;
        this.usersCollection = firestore.collection("users");
    }

    public boolean isUserExists(String username) {
        Query query = usersCollection.whereEqualTo("username", username).limit(1);
        try {
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            return !querySnapshot.get().isEmpty();
        } catch (InterruptedException | ExecutionException e) {
            // Handle the exception
            return false;
        }
    }

    public void addUser(User user) {
        usersCollection.add(user);
    }

    public User getUserByUsername(String username) {
        Query query = usersCollection.whereEqualTo("username", username).limit(1);
        try {
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
                return document.toObject(User.class);
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            // Handle the exception
            return null;
        }
    }
}
