package com.onemount.onecinema;

import com.onemount.onecinema.service.BookingService;
import com.onemount.onecinema.service.CinemaService;
import com.onemount.onecinema.service.EventService;
import com.onemount.onecinema.service.FilmService;
import com.onemount.onecinema.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner{
  @Autowired private FilmService filmService;

  @Autowired private CinemaService cinemaService;

  @Autowired private RoomService roomService;

  @Autowired private EventService eventService;

  @Autowired private BookingService bookingService;
  
  @Override
  public void run(String... args) throws Exception {
    cinemaService.generateCinema();
    filmService.generateSampleFilms();
    roomService.generateRooms();
    eventService.generateEvents();
    bookingService.generateBookings();
  }
}
