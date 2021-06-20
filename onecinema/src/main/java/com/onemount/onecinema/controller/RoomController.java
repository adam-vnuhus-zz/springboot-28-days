package com.onemount.onecinema.controller;

import java.util.List;

import com.onemount.onecinema.model.Room;
import com.onemount.onecinema.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
  @Autowired private RoomRepository roomRepository;

  @GetMapping("/{roomName}/{cinema}")
  public ResponseEntity<Room> findByNameAndCinema(
    @PathVariable("roomName") String roomName, 
    @PathVariable("cinema") String cinema) {
      return  ResponseEntity.ok().body(roomRepository.findByNameAndCinemaNameContaining(roomName, cinema));
      
    }

  @GetMapping("/cinema/{cinemaId}")
  public ResponseEntity<List<Room>> findByCinema(@PathVariable("cinemaId") Long cinemaId) {
    return  ResponseEntity.ok().body(roomRepository.findByCinemaId(cinemaId));
  }
  
}
