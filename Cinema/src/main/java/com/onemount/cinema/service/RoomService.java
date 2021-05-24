package com.onemount.cinema.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.onemount.cinema.model.Room;
import com.onemount.cinema.repository.CinemaRepository;
import com.onemount.cinema.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public void generateRoom() {
       
    }
}
