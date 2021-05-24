package com.onemount.cinema;

import com.onemount.cinema.service.CinemaService;
import com.onemount.cinema.service.CustomerService;
import com.onemount.cinema.service.EventService;
import com.onemount.cinema.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private FilmService filmService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private EventService eventService;

    @Override
    public void run(String... args) throws Exception {
        filmService.generateFilm();
        customerService.generateCustomer();
        cinemaService.generateCinema();
        eventService.generateEvent();
    }

}
