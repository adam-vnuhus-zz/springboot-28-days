package com.onemount.cinema.repository;

import java.util.Optional;

import com.onemount.cinema.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    // Optional<Genre> findByName(String name);
}
