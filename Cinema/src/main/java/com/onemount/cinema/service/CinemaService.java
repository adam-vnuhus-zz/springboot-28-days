package com.onemount.cinema.service;

import javax.transaction.Transactional;

import com.onemount.cinema.model.Room;
import com.onemount.cinema.repository.CinemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Transactional
    public void generateCinema() {
        var cinema1 = cinemaRepository.findByName("CGV Vincom Center Bà Triệu");
        var cinema2 = cinemaRepository.findByName("CGV Vivo City");
        // roomRepository.save(new Room("M02Mipec"));
        // roomRepository.save(new Room("HV11HungVuong"));
        // roomRepository.save(new Room("TS28TruongSon"));
        if (cinema1.isPresent()) {
            cinema1.get().addRoom(new Room("BT01BaTrieu"));
            cinemaRepository.save(cinema1.get());
        }
        if (cinema2.isPresent()) {
            cinema2.get().addRoom(new Room("V23Vivo"));
            cinemaRepository.save(cinema2.get());
        }
    }
}
