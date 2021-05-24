package com.onemount.cinema.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.transaction.Transactional;

import com.onemount.cinema.model.Event;
import com.onemount.cinema.repository.EventRepository;
import com.onemount.cinema.repository.FilmRepository;
import com.onemount.cinema.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public void generateEvent() {

        var fThamTuConanVienDanDo = filmRepository.findByTitle("DETECTIVE CONAN: THE SCARLET BULLET");
        var rV23Vivo = roomRepository.findByName("V23Vivo");

        if (fThamTuConanVienDanDo.isPresent() && rV23Vivo.isPresent()) {
            Event eConanVienDanDo = Event.builder()
            .film(fThamTuConanVienDanDo.get())
            .room(rV23Vivo.get())
            .price(120000)
            .startAt(LocalDateTime.parse("30/06/2021 10:15", formatter))
            .endAt(LocalDateTime.parse("30/06/2021 12:15", formatter))
            .build();
            eventRepository.save(eConanVienDanDo);
        }
    }
}
