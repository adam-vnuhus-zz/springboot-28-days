package com.onemount.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onemount.cinema.enums.FilmStatus;
import com.onemount.cinema.enums.Language;
import com.onemount.cinema.enums.Rated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity(name = "film")
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String thumbnail;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "running_time")
    private int runningTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "rated")
    private Rated rated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FilmStatus status;

    public Film(String title, String description, String thumbnail, Date releaseDate, int runningTime,
            Language language, Rated rated, FilmStatus status) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.language = language;
        this.rated = rated;
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private FilmDirector filmDirector;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "cast", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @JsonManagedReference
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "film_genre", 
        joinColumns = @JoinColumn(name = "film_id"), 
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonManagedReference
    private List<Genre> genres = new ArrayList<>();

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getFilms().remove(this);
    }

    public void addActor(Actor actor) {
        actors.add(actor);
        actor.getFilms().add(this);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getFilms().add(this);
    }
}
