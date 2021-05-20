package com.onemount.cinema.service;

import com.onemount.cinema.enums.FilmStatus;
import com.onemount.cinema.enums.Language;
import com.onemount.cinema.enums.Rated;
import com.onemount.cinema.model.Actor;
import com.onemount.cinema.model.Film;
import com.onemount.cinema.model.Genre;
import com.onemount.cinema.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class FilmService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private FilmRepository filmRepository;

    @Transactional
    public void generateFilm() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Film film1 = new Film("DETECTIVE CONAN: THE SCARLET BULLET",
                "The World Sports Games held in Tokyo, Japan, attract a lot of attention. When the launch event of the vacuum-tube super-conducting linear train with a speed of 1000km/h takes place is also a series of kidnappings occurred! The dangerous family gathered here will cause a series of world shocking events!",
                "", formatter.parse("23/04/2021"), 111, Language.VN, Rated.C13, FilmStatus.ON_THEATER);
        Film film2 = new Film("MY SPY",
                "When CIA field agent Jason Jones – or JJ (Dave Bautista) – is demoted to a light surveillance detail, he finds himself at the mercy of a sweet but determined 9-year-old girl, Sophie (Chloe Coleman), who uses her tech savviness and street smarts to find JJ’s undercover hideout near the apartment she shares with her mother. In exchange for not blowing JJ’s cover, Sophie convinces him to spend time with her and teach her to be a spy. Despite his reluctance, JJ finds he is no match for Sophie’s disarming charm, intelligence and aptitude for espionage (RE-RUN FROM MAY 5TH)",
                "", formatter.parse("14/08/2020"), 101, Language.VN, Rated.C16, FilmStatus.INCOMING);

        filmRepository.save(film1);
        filmRepository.save(film2);
    }

    public List<Film> getALl() {
        return filmRepository.findAll();
    }
}
