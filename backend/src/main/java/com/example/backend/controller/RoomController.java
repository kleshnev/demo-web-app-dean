package com.example.backend.controller;

import com.example.backend.DTO.RoomCreationRequest;
import com.example.backend.entity.Room;
import com.example.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create-room")
    public ResponseEntity<Room> createRoom(@RequestBody RoomCreationRequest request) throws ExecutionException, InterruptedException {
        Room room = roomService.createRoom(request.getName(), request.getDescription());
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }
}