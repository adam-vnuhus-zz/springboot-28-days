package com.onemount.onecinema.repository;

import com.onemount.onecinema.model.Cinema;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
  public Cinema findByNameContaining(String name);
}
