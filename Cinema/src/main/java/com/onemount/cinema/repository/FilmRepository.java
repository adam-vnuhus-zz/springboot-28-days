package com.onemount.cinema.repository;

import java.util.Optional;

import com.onemount.cinema.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    Optional<Film> findByTitle(String title);
}
