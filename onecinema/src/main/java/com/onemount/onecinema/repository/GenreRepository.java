package com.onemount.onecinema.repository;

import com.onemount.onecinema.model.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long>{
  public Genre findByName(String name);
}
