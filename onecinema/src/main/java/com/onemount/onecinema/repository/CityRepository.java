package com.onemount.onecinema.repository;

import com.onemount.onecinema.model.City;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
  public City findByName(String name);
}
