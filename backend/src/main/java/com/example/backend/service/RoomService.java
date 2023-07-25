package com.example.backend.service;

import com.example.backend.entity.Room;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class RoomService {
   // private final Firestore firestore; // Inject the Firestore instance here

    @Autowired
    public RoomService(FirestoreService firestoreService) throws IOException {
        //this.firestore = firestoreService.initializeFirestore();
    }
    public Room createRoom(String name, String description) throws InterruptedException, ExecutionException {
        Room room = new Room();
        room.setName(name);
        room.setDescription(description);

        // You can perform additional logic or validations here.

        // Save the room data to Firestore
       // ApiFuture<WriteResult> writeResult = firestore.collection("rooms").document().set(room);

        // Wait for the write operation to complete and get the result
       // writeResult.get();

        return room;
    }
}