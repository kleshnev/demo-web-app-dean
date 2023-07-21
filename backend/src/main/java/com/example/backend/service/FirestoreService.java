package com.example.backend.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirestoreService {

    @Value("classpath:serviceAccountKey.json")
    private Resource keyFile;

    public Firestore initializeFirestore() throws IOException {
        FirestoreOptions options = FirestoreOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(keyFile.getInputStream()))
                .build();
        return options.getService();
    }

    // Add other Firestore-related methods here as needed

}

// ... rest of the service code
