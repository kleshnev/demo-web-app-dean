package com.example.backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication

public class BackendApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BackendApplication.class, args);
	}

	// Define a method to initialize Firestore and make it a bean
	@Bean
	public Firestore firestore() throws IOException {
		String pathToServiceAccountKey = BackendApplication.class.getResource("/serviceAccountKey.json").getPath();
		FileInputStream serviceAccount = new FileInputStream(pathToServiceAccountKey);

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://web-app-dean.firebaseio.com")
				.build();

		FirebaseApp.initializeApp(options);

		return FirestoreClient.getFirestore();
	}
}
