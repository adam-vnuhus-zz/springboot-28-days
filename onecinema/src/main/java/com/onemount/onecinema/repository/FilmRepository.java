package com.onemount.onecinema.repository;

import com.onemount.onecinema.model.Film;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
  Film findByTitle(String title);
}
