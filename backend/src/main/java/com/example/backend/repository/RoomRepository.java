package com.example.backend.repository;

import com.example.backend.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    // You can add custom query methods here if needed.
}