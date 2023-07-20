package com.example.backend.service;

import com.example.backend.entity.Room;
import com.example.backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(String name, String description) {
        Room room = new Room();
        room.setName(name);
        room.setDescription(description);

        // You can perform additional logic or validations here.

        return roomRepository.save(room);
    }
}