package com.onemount.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "cinema")
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // private String address;

    private String city;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cinema_id")
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        room.setCinema(this);
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        room.setCinema(null);
        rooms.remove(room);
    }

    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    // @JoinColumn(name = "cinema_id")
    // private List<Staff> staffs = new ArrayList<>();

    // @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(
    // name = "cinema_film",
    // joinColumns = @JoinColumn(name = "cinema_id"),
    // inverseJoinColumns = @JoinColumn(name = "film_id")
    // )
    // @JsonManagedReference
    // private List<Film> filmList = new ArrayList<>();

    // public void addFilm(Film film) {
    // filmList.add(film);
    // film.getCinemas().add(this);
    // }

    // public void removeFilm(Film film) {
    // filmList.remove(film);
    // film.getCinemas().remove(this);
    // }
}
