package com.onemount.cinema.repository;

import java.util.Optional;

import com.onemount.cinema.model.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    
    Optional<Room> findByName(String name);
}
