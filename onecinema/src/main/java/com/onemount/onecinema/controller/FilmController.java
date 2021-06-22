package com.onemount.onecinema.controller;

import java.util.List;

import com.onemount.onecinema.model.Film;
import com.onemount.onecinema.service.FilmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/film")
public class FilmController {
  @Autowired
  private FilmService filmService;

  @Operation(summary = "Get all list films")
  @GetMapping()
  public ResponseEntity<List<Film>> findAll() {
    return ResponseEntity.ok().body(filmService.findAll());
  }

}
